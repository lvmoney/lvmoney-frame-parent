package com.lvmoney.frame.sync.uniformity.unique.common;/**
 * 描述:
 * 包名:com.chinapopin.platform.overall.common.constant
 * 版本信息: 版本1.0
 * 日期:2020/10/9
 * Copyright 成都XXXXXXX科技有限公司
 */


import static com.lvmoney.frame.sync.common.constant.SyncConstant.REDIS_SYNC_GROUP;

/**
 * @describe：
 * @author: lvmoney/成都XXXXXXX科技有限公司
 * @version:v1.0 2020/10/9 10:27
 */
public class UniformityUniqueConstant {
    /**
     * 唯一code新增
     */
    public static final String UNIQUE_CODE_SCRIPT_LUA_ADD =
            "local buyNum = ARGV[1]\n" +
                    "local prodCode = KEYS[1]  \n" +
                    "redis.call('incrby',prodCode,buyNum) \n" +
                    "return tostring(redis.call('get',prodCode))";
    /**
     * 全局唯一code前缀
     */
    public static final String UNIQUE_CODE_PREFIX = REDIS_SYNC_GROUP + "UNIFORMITY_UNIQUE_CODE";

    /**
     * 全局唯一code默认失效时间32h
     */
    public static final Long UNIQUE_CODE_EXPIRED = 115200L;

    /**
     * 数据推送bitmap过期时间(48小时)
     */
    public final static Long BITMAP_EXPIRED = 172800L;

    /**
     * 数据同步更新模块统一前缀
     */
    public final static String DATASYNC_PREFIX = REDIS_SYNC_GROUP + "UNIFORMITY_DATASYNC";


}
