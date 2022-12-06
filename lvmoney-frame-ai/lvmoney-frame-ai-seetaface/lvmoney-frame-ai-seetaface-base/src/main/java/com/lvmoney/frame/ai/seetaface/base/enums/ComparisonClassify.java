package com.lvmoney.frame.ai.seetaface.base.enums;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.seetaface.client.enums
 * 版本信息: 版本1.0
 * 日期:2022/2/11
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/2/11 14:36
 */
public enum ComparisonClassify {
    /**
     * 1:1
     */
    SINGLE_V_SINGLE("1v1"),
    /**
     * 1:n
     */
    SINGLE_V_MANY("1vN"),

    /**
     * 1:n
     */
    IS_FACE("isFace");

    private String value;

    ComparisonClassify(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
