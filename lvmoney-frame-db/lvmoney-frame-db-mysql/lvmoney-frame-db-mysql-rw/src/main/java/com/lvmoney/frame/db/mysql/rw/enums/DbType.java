package com.lvmoney.frame.db.mysql.rw.enums;/**
 * 描述:
 * 包名:com.lvmoney.mysql.separate.enums
 * 版本信息: 版本1.0
 * 日期:2019/9/6
 * Copyright 四川******科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
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
