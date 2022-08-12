package com.lvmoney.frame.newsql.clickhouse.sink.sink;/**
 * 描述:
 * 包名:com.lvmoney.frame.newsql.clickhouse.sink.sink
 * 版本信息: 版本1.0
 * 日期:2020/6/29
 * Copyright XXXXXX科技有限公司
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.clickhouse.ClickHouseConnection;
import ru.yandex.clickhouse.ClickHouseDataSource;
import ru.yandex.clickhouse.settings.ClickHouseProperties;

import java.sql.*;
import java.util.*;

import static com.lvmoney.frame.newsql.clickhouse.sink.constant.ClickHouseSinkConstant.DEFAULT_MAP_SIZE;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/29 21:04
 */
public class ClickHouseConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClickHouseConfig.class);
    private static String clickhouseAddress = "jdbc:clickhouse://10.20.128.235:8123";

    private static String clickhouseUsername = "default";

    private static String clickhousePassword = "";

    private static String clickhouseDB = "default";

    private static Integer clickhouseSocketTimeout = 6000;

    public static Connection getConn() {
        ClickHouseConnection conn = null;
        ClickHouseProperties properties = new ClickHouseProperties();
        properties.setUser(clickhouseUsername);
        properties.setPassword(clickhousePassword);
        properties.setDatabase(clickhouseDB);
        properties.setSocketTimeout(clickhouseSocketTimeout);
        ClickHouseDataSource clickHouseDataSource = new ClickHouseDataSource(clickhouseAddress, properties);
        try {
            conn = clickHouseDataSource.getConnection();
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static List<Map> exeSql(String sql) {
        Connection connection = getConn();
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sql);
            ResultSetMetaData rsmd = results.getMetaData();
            List<Map> list = new ArrayList();
            while (results.next()) {
                Map row = new HashMap(DEFAULT_MAP_SIZE);
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.put(rsmd.getColumnName(i), results.getString(rsmd.getColumnName(i)));
                }
                list.add(row);
            }
            return list;
        } catch (SQLException e) {
            LOGGER.error("ExeSql:{}", sql);
            e.printStackTrace();
        }
        return null;
    }
}
