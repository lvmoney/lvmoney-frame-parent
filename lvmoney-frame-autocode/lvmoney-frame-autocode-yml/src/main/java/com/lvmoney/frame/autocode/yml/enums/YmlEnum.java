package com.lvmoney.frame.autocode.yml.enums;/**
 * 描述:
 * 包名:com.lvmoney.frame.autocode.yml.enums
 * 版本信息: 版本1.0
 * 日期:2020/6/17
 * Copyright XXXXXX科技有限公司
 */


import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/17 19:44
 */
public enum YmlEnum {
    /**
     * dev
     */
    DEV("dev"),
    /**
     * test
     */
    TEST("test"),
    /**
     * test
     */
    UAT("uat"),
    /**
     * online
     */
    ONLINE("online"),
    ;
    private String value;

    YmlEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public static List<String> getValues() {
        List<String> result = new ArrayList();
        for (YmlEnum catalogueEnum : YmlEnum.values()) {
            result.add(catalogueEnum.getValue());
        }
        return result;
    }

}
