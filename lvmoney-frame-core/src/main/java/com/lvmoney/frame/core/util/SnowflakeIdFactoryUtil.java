/**
 * 描述:
 * 包名:com.lvmoney.pay.util
 * 版本信息: 版本1.0
 * 日期:2018年10月9日  下午5:26:20
 * Copyright 四川******科技有限公司
 */

package com.lvmoney.frame.core.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * @describe：Twitter-snowflake 实现
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月9日 下午5:37:52
 */
public class SnowflakeIdFactoryUtil {
    private long machineId;
    private long dataCenterId;


    public SnowflakeIdFactoryUtil(long machineId, long dataCenterId) {
        this.machineId = machineId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 成员类，SnowFlakeUtil的实例对象的保存域
     */
    private static class IdGenHolder {
        private static final SnowflakeIdFactoryUtil INSTANCE = new SnowflakeIdFactoryUtil();
    }

    /**
     * 外部调用获取SnowFlakeUtil的实例对象，确保不可变
     */
    public static SnowflakeIdFactoryUtil get() {
        return IdGenHolder.INSTANCE;
    }

    /**
     * 初始化构造，无参构造有参函数，默认节点都是0
     */
    public SnowflakeIdFactoryUtil() {
        this(0L, 0L);
    }

    private Snowflake snowflake = IdUtil.createSnowflake(machineId, dataCenterId);

    public synchronized long id() {
        return snowflake.nextId();
    }

    /**
     * @return 2018年10月10日下午2:45:22
     * @describe:获得唯一key值
     * @author: lvmoney /四川******科技有限公司
     */
    public static long nextId() {
        return SnowflakeIdFactoryUtil.get().id();
    }

}
