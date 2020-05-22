package com.zhy.frame.cache.lock.constant;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/8
 * Copyright 四川******科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class LockConstant {
    /**
     * 分布式锁的key
     */
    public static final String SECTION_LOCK_KEY = "sectionLock";
    /**
     * 分布式锁的key
     */
    public static final String PROD_SECTION_LOCK_KEY = "prodSectionLock";
    /**
     * 分布式锁的key
     */
    public static final String PROD_SECTION_UPDATE_LOCK_KEY = "prodSectionUpdateLock";
    /**
     * 商品锁
     */
    public static final String PROD_LOCK_KEY = "prodLock";
    /**
     * 锁的时间
     */
    public static final int LOCK_TIME = 60;
    /**
     * 默认刷新阀值
     */
    public static final long HOT_REQUEST_THRESHOLD = 10000;
    /**
     * 刷新周期，默认60秒
     */
    public static final int HOT_REQUEST_SECTION = 60;
    /**
     * 热点数据有效时间
     */
    public static final long HOT_REQUEST_EXPIRED = 1800;
    /**
     * json空值
     */
    public static final String JSON_EMPTY_VALUE = "{}";
    /**
     * 热点 cache name
     */
    public static final String HOT_REQUEST_CAFFEINE_CACHE_NAME = "hotRequest";

    /**
     * 秒杀脚本 新增
     */
    public static final String HOT_REQUEST_SCRIPT_LUA_ADD = "local node = ARGV[1]\n" +
            "local pkey = KEYS[1]  \n" +
            "local value local jvalue local counter " +
            "value= redis.call('get',pkey) " +
            "jvalue=cjson.decode(value) " +
            "counter= jvalue[node] " +
            "jvalue[node]=counter+1 " +
            "redis.call('set',pkey,cjson.encode(temp))";

}
