package com.lvmoney.demo.common.enums;/**
 * 描述:
 * 包名:com.lvmoney.frame.core.enums
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/19 16:25
 */
public enum TmcExceptionCodeLevel {
    /**
     * 用户错误范围
     */
    USER(700),


    ;

    private Integer value;

    TmcExceptionCodeLevel(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
