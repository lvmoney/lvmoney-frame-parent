package com.lvmoney.frame.cache.repeatsubmit.enums;/**
 * 描述:
 * 包名:com.lvmoney.frame.cache.repeatsubmit.enums
 * 版本信息: 版本1.0
 * 日期:2020/5/13
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/13 17:29
 */
public enum NoRepeatSubmitEnum {

    /**
     * 部分
     */
    PARTLY("PARTLY"),
    /**
     * 允许
     */
    ALLOW("ALLOW"),
    /**
     * 不允许
     */
    NOT_ALLOW("NOT_ALLOW");

    private String required;

    public String getRequired() {
        return required;
    }

    NoRepeatSubmitEnum() {
    }

    NoRepeatSubmitEnum(String required) {
        this.required = required;
    }

}
