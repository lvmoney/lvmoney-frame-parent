package com.lvmoney.frame.log.logback.clickhouse.constant;/**
 * 描述:
 * 包名:com.lvmoney.frame.log.logback.clickhouse.constant
 * 版本信息: 版本1.0
 * 日期:2020/7/27
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/7/27 14:08
 */
public class ClickhouseConstant {
    /**
     * 默认的batchSize
     */
    public static final Integer DEFAULT_CACHE_MAX_SIZE = 999999999;

    /**
     * 默认间隔时间
     */
    public static final Integer DEFAULT_INTERVAL_DATE = 8 * 1000;

    /**
     * 缓存中用来存kafka消费的时间key 用来记录最近同步时间
     */
    public static final String DEFAULT_CACHE_DATE_KAFKA = "LATEST_DATE";

    /**
     * 中国时区id
     */
    public static final String DEFAULT_ZONE_ID = "Asia/Shanghai";

    /**
     * 默认并发数
     */
    public static final String MAX_PARALLEL_REPLICAS_VALUE = "4";

}
