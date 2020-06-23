package com.zhy.autocode.code.enums;/**
 * 描述:
 * 包名:com.zhy.autocode.code.enums
 * 版本信息: 版本1.0
 * 日期:2020/6/19
 * Copyright XXXXXX科技有限公司
 */


import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/19 9:24
 */
public enum ClassTypeEnum {
    /**
     * application
     */
    CLASS("class"),
    /**
     * interface
     */
    INTERFACE("interface"),
    ;
    private String value;

    ClassTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public static List<String> getValues() {
        List<String> result = new ArrayList();
        for (ClassTypeEnum classTypeEnum : ClassTypeEnum.values()) {
            result.add(classTypeEnum.getValue());
        }
        return result;
    }

}
