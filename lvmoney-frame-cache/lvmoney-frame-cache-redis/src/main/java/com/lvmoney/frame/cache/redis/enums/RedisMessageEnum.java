package com.lvmoney.frame.cache.redis.enums;/**
 * 描述:
 * 包名:com.lvmoney.frame.cache.redis.enums
 * 版本信息: 版本1.0
 * 日期:2020/5/18
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/18 9:00
 */
public enum RedisMessageEnum {

    /**
     * 删除
     */
    DEL("*:del"),
    /**
     * 设置过期时间
     */
    EXPIRE("*:expire"),
    /**
     * 过期时
     */
    EXPIRED("*:expired"),
    /**
     * 新增
     */
    SAVE("*:set"),

    /**
     * 重命名
     */
    RENAME("*:rename"),

    /**
     * 全部
     */
    ALL("*"),
    ;

    private String type;

    public String getType() {
        return type;
    }

    RedisMessageEnum() {
    }

    RedisMessageEnum(String type) {
        this.type = type;
    }
}
