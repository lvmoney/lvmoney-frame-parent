package com.lvmoney.frame.db.sharding.util.constant;/**
 * 描述:
 * 包名:com.lvmoney.mysql.subdb.util.constant
 * 版本信息: 版本1.0
 * 日期:2019/11/15
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/15 15:38
 */
public class DbUtilConstant {
    /**
     * 数据库默认字符集
     */
    public static final String DB_DEFAULT_CHARSET = "/*!40100 COLLATE 'utf8_general_ci' */";

    /**
     * sql以;结尾
     */
    public static final String SQL_END = ";";

    /**
     * mysql 基础配置库
     */
    public static final String MYSQL_DB_INFORMATION_SCHEMA = "information_schema";

    /**
     * mysql 基础配置库中，库表
     */
    public static final String MYSQL_TABLE_SCHEMA = "SCHEMATA";

    /**
     * 查看数据库的创建语句
     */
    public static final String DB_CREATE_SHOW = "show create database ";

    /**
     * 查看表的创建语句
     */
    public static final String TABLE_CREATE_SHOW = "show create table ";

    /**
     * mysql 驱动
     */
    public static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * mysql连接前缀
     */
    public static final String MYSQL_URL_PREFIX = "jdbc:mysql://";
    /**
     * mysql url 后缀
     */
    public static final String MYSQL_URL_SUBFFIX = "?useSSL=FALSE&serverTimezone=GMT%2B8";

    /**
     * 一致性hash默认虚拟id一共10000
     */
    public static final Integer DEFAULT_VIRTUAL_NODE_SUM = 10000;

    /**
     * 数据库表关系前缀
     */
    public static final String REL_DB_TABLE_PREFIX = "SHARDING:REL_DB_TABLE_PREFIX";


    /**
     * 数据库表关系前缀
     */
    public static final String REL_DB_TENANT_PREFIX = "SHARDING:REL_DB_TENANT_PREFIX";

}
