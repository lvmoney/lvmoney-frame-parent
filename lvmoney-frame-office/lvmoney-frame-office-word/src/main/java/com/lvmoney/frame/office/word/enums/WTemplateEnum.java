package com.lvmoney.frame.office.word.enums;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/2/28
 * Copyright 成都三合力通科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public enum WTemplateEnum {
    /**
     * 表格
     */
    TABLE("#"),
    /**
     * string
     */
    STRING("\u0000"),
    /**
     * 图片
     */
    PICTURE("@"),
    /**
     * 数字列
     */
    NUMBERIC("*");

    private String value;

    private WTemplateEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static WTemplateEnum getByValue(String value) {
        WTemplateEnum[] wTemplateEnums = values();
        for (WTemplateEnum wTemplateEnum : wTemplateEnums) {
            if (wTemplateEnum.value.equals(value)) {
                return wTemplateEnum;
            }
        }
        return null;
    }


}
