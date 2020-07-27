package com.zhy.frame.newsql.clickhouse.sink.constant;/**
 * 描述:
 * 包名:com.zhy.frame.newsql.clickhouse.config
 * 版本信息: 版本1.0
 * 日期:2020/6/26
 * Copyright XXXXXX科技有限公司
 */

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/26 10:22
 */

public class ClickHouseSinkConstant {
    /**
     * flume 配置文件中 key host
     */
    public static final String HOST = "host";
    /**
     * flume 配置文件中 key port
     */
    public static final String PORT = "port";
    /**
     * flume 配置文件中 key batchSize
     */
    public static final String BATCH_SIZE = "batchSize";
    /**
     * flume 配置文件中 key user
     */
    public static final String USER = "user";
    /**
     * flume 配置文件中 key password
     */
    public static final String PASSWORD = "password";
    /**
     * flume 配置文件中 key database
     */
    public static final String DATABASE = "database";
    /**
     * flume 配置文件中 key intervalDate
     */
    public static final String INTERVAL_DATE = "intervalDate";
    /**
     * flume 配置文件中 key table
     */
    public static final String TABLE = "table";
    /**
     * 默认的端口
     */
    public static final String DEFAULT_PORT = "8123";
    /**
     * 默认的batchSize
     */
    public static final Integer DEFAULT_BATCH_MAX_SIZE = 10000;
    /**
     * 默认的用户
     */
    public static final String DEFAULT_USER = "default";
    /**
     * flume 配置文件中 key host
     */
    public static final String DEFAULT_PASSWORD = "";
    /**
     * jdbc前缀
     */
    public static final String CLICK_HOUSE_PREFIX = "jdbc:clickhouse://";
    /**
     * 默认并发数
     */
    public static final String MAX_PARALLEL_REPLICAS_VALUE = "4";

    /**
     * 日志分割线
     */
    public static final String LOG_SPLIT = "|";
    /**
     * 日志切割中括号正则表达式
     */
    public static final String LOG_PATTERN = "\\[(.*?)]";
    /**
     * 默认map长度
     */
    public static final int DEFAULT_MAP_SIZE = 16;
    /**
     * 时间格式
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 缓存中用来存kafka消费的时间key 用来记录最近同步时间
     */
    public static final String DEFAULT_CACHE_DATE_KAFKA = "LATEST_DATE";

    /**
     * 默认的batchSize
     */
    public static final Integer DEFAULT_CACHE_MAX_SIZE = 999999999;

    /**
     * 默认间隔时间
     */
    public static final Integer DEFAULT_INTERVAL_DATE = 8 * 1000;

    /**
     * 中国时区id
     */
    public static final String DEFAULT_ZONE_ID = "Asia/Shanghai";
}
