package com.lvmoney.frame.sync.uniformity.db.common;/**
 * 描述:
 * 包名:com.lvmoney.frame.sync.uniformity.db.common
 * 版本信息: 版本1.0
 * 日期:2022/1/5
 * Copyright XXXXXX科技有限公司
 */


import static com.lvmoney.frame.sync.common.constant.SyncConstant.REDIS_SYNC_GROUP;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/1/5 15:02
 */
public class UniformityDbConstant {
    /**
     * 一致性sharding 配置 前缀
     */
    public static final String UNIFORMITY_SHARDING_PREFIX = REDIS_SYNC_GROUP + "uniformityShardingConfig";

    /**
     * 一致性sharding 配置 前缀
     */
    public static final String UNIFORMITY_SELECT_END_PREFIX = REDIS_SYNC_GROUP + "selectEnd";
}
