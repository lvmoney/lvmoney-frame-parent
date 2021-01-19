package com.lvmoney.frame.cache.redis.constant;/**
 * 描述:
 * 包名:com.lvmoney.frame.cache.redis.constant
 * 版本信息: 版本1.0
 * 日期:2020/5/3
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/3 14:24
 */
public class RedisConstant {

    /**
     * 秒杀脚本 扣减
     */
    public static final String SECKILL_SCRIPT_LUA_REDUCE = "local buyNum = ARGV[1]\n" +
            "local prodCode = KEYS[1]  \n" +
            "local prodCount = redis.call('get',prodCode) \n" +
            "if tonumber(prodCount) >= tonumber(buyNum) \n" +
            "then redis.call('decrby',prodCode,buyNum) \n" +
            "return redis.call('get',prodCode) \n" +
            "else \n" +
            "return '-1'\n" +
            "end";
    /**
     * 秒杀脚本 新增
     */
    public static final String SECKILL_SCRIPT_LUA_ADD = "local buyNum = ARGV[1]\n" +
            "local prodCode = KEYS[1]  \n" +
            "local prodCount = redis.call('get',prodCode) \n" +
            "if tonumber(buyNum) >= 0 \n" +
            "then redis.call('incrby',prodCode,buyNum) \n" +
            "return redis.call('get',prodCode) \n" +
            "else \n" +
            "return '-1'\n" +
            "end";
    /**
     * 商品秒杀前缀
     */
    public static final String SECKILL_PRODUCT_PREFIX = "seckillProduct";

    /**
     * 高频访问前缀
     */
    public static final String HOT_REQUEST_PREFIX = "hotRequestStatistics";


    /**
     * 高频访问锁
     */
    public static final String HOT_REQUEST_UPDATE_LOCK_PREFIX = "hotRequestLock";

    /**
     * 高频访问锁
     */
    public static final String HOT_REQUEST_INTERCEPTOR_LOCK_PREFIX = "hotRequestLock";
}
