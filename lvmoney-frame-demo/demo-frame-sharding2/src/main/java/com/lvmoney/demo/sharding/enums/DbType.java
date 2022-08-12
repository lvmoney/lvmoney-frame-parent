package com.lvmoney.demo.sharding.enums;/**
 * 描述:
 * 包名:com.lvmoney.mysql.separate.enums
 * 版本信息: 版本1.0
 * 日期:2019/9/6
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/6 18:38
 */
public enum DbType {
    /**
     * 主
     */
    MASTER("master"),
    /**
     * 从
     */
    SLAVE("slave");
    private String value;

    DbType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
