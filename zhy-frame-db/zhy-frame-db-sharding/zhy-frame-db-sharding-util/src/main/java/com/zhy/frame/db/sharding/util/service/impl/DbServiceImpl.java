package com.zhy.frame.db.sharding.util.service.impl;/**
 * 描述:
 * 包名:com.zhy.mysql.subdb.util.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/11/15
 * Copyright XXXXXX科技有限公司
 */


import com.github.dozermapper.core.Mapper;
import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.core.util.ConsistentHashVirtualNodeUtil;
import com.zhy.frame.db.sharding.util.constant.DbUtilConstant;
import com.zhy.frame.db.sharding.util.service.DbService;
import com.zhy.frame.db.sharding.util.vo.req.BatchTableReqVo;
import com.zhy.frame.db.sharding.util.vo.req.CommonReqVo;
import com.zhy.frame.db.sharding.util.vo.req.DbReqVo;
import com.zhy.frame.db.sharding.util.vo.req.TableReqVo;
import com.zhy.frame.db.sharding.util.vo.resp.BatchTableRespVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/15 16:14
 */
@Service
public class DbServiceImpl implements DbService {

    private static final String UNDERLINE = "_";
    private static final String SEMICOLON = ";";
    private static final String LINE_FEED = "\r\n";

    private static final Logger LOGGER = LoggerFactory.getLogger(DbServiceImpl.class);

    private static final String MYSQL_NOTE = "/*";
    @Autowired
    Mapper dozerMapper;

    @Override
    public List<BatchTableRespVo> createTable(BatchTableReqVo batchTableReqVo) {
        DbReqVo dbReqVo = new DbReqVo();
        dbReqVo = dozerMapper.map(batchTableReqVo, DbReqVo.class);
        String createDbSql = showDbSql(dbReqVo);
        String dbName = batchTableReqVo.getDbName();
        List<String> serverList = batchTableReqVo.getServer();
        int serverNum = serverList.size();

        TableReqVo tableReqVo = new TableReqVo();
        tableReqVo = dozerMapper.map(batchTableReqVo, TableReqVo.class);
        String createTableSql = showTableSql(tableReqVo);

        String tableName = batchTableReqVo.getTableName();
        int tableNum = batchTableReqVo.getTableNum();
        List<String> tableNameList = new ArrayList<>();
        Map<String, String> tableMap = new HashMap(BaseConstant.MAP_DEFAULT_SIZE);
        //获得创建表的sql
        for (int i = 0; i < tableNum; i++) {
            int serverNameHash = ConsistentHashVirtualNodeUtil.getHash(tableName + i);
            String itemTableName = tableName + UNDERLINE + serverNameHash;
            tableMap.put(itemTableName, createTableSql.replaceAll(tableName, itemTableName) + SEMICOLON);
            tableNameList.add(itemTableName);
        }


        List<String> serverNameList = new ArrayList<>();
        Map<String, Map<String, String>> serverMap = new HashMap(BaseConstant.MAP_DEFAULT_SIZE);
        //获得创建数据库的sql
        for (int i = 0; i < serverNum; i++) {
            int serverNameHash = ConsistentHashVirtualNodeUtil.getHash(dbName + i);
            String serverName = dbName + UNDERLINE + serverNameHash;
            Map<String, String> temp = new HashMap<>(BaseConstant.MAP_DEFAULT_SIZE);
            temp.put(serverName, createDbSql.replaceAll(dbName, serverName) + SEMICOLON);
            serverMap.put(serverList.get(i), temp);
            serverNameList.add(serverList.get(i));
        }

        List<BatchTableRespVo> result = new ArrayList();
        Map<String, BatchTableRespVo> tempMap = new HashMap(BaseConstant.MAP_DEFAULT_SIZE);
        //将获得表和数据库通过一致性hash算法结合起来
        tableNameList.stream().forEach(e -> {
            String ip = ConsistentHashVirtualNodeUtil.getServer(e, serverNameList, batchTableReqVo.getVirtualNode());
            if (tempMap.get(ip) == null) {
                BatchTableRespVo batchTableRespVo = new BatchTableRespVo();
                batchTableRespVo.setIp(ip);
                batchTableRespVo.setCreateTableSql(tableMap.get(e));
                Map<String, String> dbMap = serverMap.get(ip);
                dbMap.forEach((k, v) -> {
                    batchTableRespVo.setDbName(k);
                    batchTableRespVo.setCreateDbSql(v);
                });
                List<String> itemTableName = new ArrayList<>();
                itemTableName.add(e);
                batchTableRespVo.setTableName(itemTableName);
                tempMap.put(ip, batchTableRespVo);
            } else {
                BatchTableRespVo batchTableRespVo = tempMap.get(ip);
                String tableSql = batchTableRespVo.getCreateTableSql() + LINE_FEED + tableMap.get(e);
                batchTableRespVo.setCreateTableSql(tableSql);
                batchTableRespVo.getTableName().add(e);
            }

        });
        tempMap.forEach((k, v) -> {
            result.add(v);
        });
        return result;
    }


    @Override
    public Connection getConnection(CommonReqVo commonReqVo) {
        Connection conn = null;
        try {
            Class.forName(DbUtilConstant.MYSQL_DRIVER);
            String url = DbUtilConstant.MYSQL_URL_PREFIX + commonReqVo.getIp() + ":" + commonReqVo.getPort() + "/" + commonReqVo.getDbName() + DbUtilConstant.MYSQL_URL_SUBFFIX;
            conn = DriverManager.getConnection(url, commonReqVo.getUsername(), commonReqVo.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;

    }

    @Override
    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("close connection failure", e);
            }
        }
    }

    @Override
    public String showDbSql(DbReqVo dbReqVo) {
        CommonReqVo commonReqVo = dozerMapper.map(dbReqVo, CommonReqVo.class);
        Connection conn = getConnection(commonReqVo);
        String sql = DbUtilConstant.DB_CREATE_SHOW + dbReqVo.getDbName();
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String result = rs.getString(2);
                if (result.contains(MYSQL_NOTE)) {
                    result = result.substring(0, result.indexOf(MYSQL_NOTE));
                }
                return result + DbUtilConstant.DB_DEFAULT_CHARSET;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (!conn.isClosed()) {
                    closeConnection(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    @Override
    public String showTableSql(TableReqVo tableReqVo) {
        CommonReqVo commonReqVo = dozerMapper.map(tableReqVo, CommonReqVo.class);
        Connection conn = getConnection(commonReqVo);
        String sql = DbUtilConstant.TABLE_CREATE_SHOW + tableReqVo.getDbName() + "." + tableReqVo.getTableName();
        PreparedStatement stmt;
        try {
            stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String result = rs.getString(2);
                return result;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (!conn.isClosed()) {
                    closeConnection(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
