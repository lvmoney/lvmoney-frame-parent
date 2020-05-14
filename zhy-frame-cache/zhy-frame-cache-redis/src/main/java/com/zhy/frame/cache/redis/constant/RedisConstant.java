package com.zhy.frame.cache.redis.constant;/**
 * 描述:
 * 包名:com.zhy.frame.cache.redis.constant
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
            "if prodCount >= buyNum \n" +
            "then redis.call('decrby',prodCode,buyNum) \n" +
            "return redis.call('get',prodCode) \n" +
            "else \n" +
            "return '0'\n" +
            "end";
    /**
     * 秒杀脚本 新增
     */
    public static final String SECKILL_SCRIPT_LUA_ADD = "local buyNum = ARGV[1]\n" +
            "local prodCode = KEYS[1]  \n" +
            "local prodCount = redis.call('get',prodCode) \n" +
            "if prodCount >= buyNum \n" +
            "then redis.call('incrby',prodCode,buyNum) \n" +
            "return redis.call('get',prodCode) \n" +
            "else \n" +
            "return '0'\n" +
            "end";
    /**
     * 商品秒杀前缀
     */
    public static final String SECKILL_PRODUCT_PREFIX = "seckillProduct";
}
