package com.zhy.frame.db.sharding.util.service;/**
 * 描述:
 * 包名:com.zhy.mysql.subdb.service
 * 版本信息: 版本1.0
 * 日期:2019/11/15
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.db.sharding.util.vo.req.BatchTableReqVo;
import com.zhy.frame.db.sharding.util.vo.req.CommonReqVo;
import com.zhy.frame.db.sharding.util.vo.req.DbReqVo;
import com.zhy.frame.db.sharding.util.vo.req.TableReqVo;
import com.zhy.frame.db.sharding.util.vo.resp.BatchTableRespVo;

import java.sql.Connection;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/15 15:18
 */
public interface DbService {

    /**
     * 创建表
     *
     * @param batchTableReqVo:
     * @throws
     * @return: java.util.List<com.zhy.mysql.subdb.util.vo.resp.BatchTableRespVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/1/6 17:42
     */
    List<BatchTableRespVo> createTable(BatchTableReqVo batchTableReqVo);


    /**
     * 获得数据库连接
     *
     * @param commonReqVo:
     * @throws
     * @return: java.sql.Connection
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/15 17:00
     */
    Connection getConnection(CommonReqVo commonReqVo);

    /**
     * 关闭数据库连接
     *
     * @param conn:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/15 17:00
     */
    void closeConnection(Connection conn);

    /**
     * 从已有数据库中获得该数据库的创建语句
     *
     * @param dbReqVo:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/15 17:00
     */
    String showDbSql(DbReqVo dbReqVo);

    /**
     * 从已有数据库中获得获得指定表的创建语句
     *
     * @param tableReqVo:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/15 17:01
     */
    String showTableSql(TableReqVo tableReqVo);
}
