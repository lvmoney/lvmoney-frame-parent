package com.lvmoney.frame.ai.isolationforest.enums;/**
 * 描述:
 * 包名:com.lvmoney.frame.autocode.catalogue.enums
 * 版本信息: 版本1.0
 * 日期:2020/6/17
 * Copyright XXXXXX科技有限公司
 */


import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/6/17 14:49
 */
public enum DataClassificationEnum {
    /**
     * train，训练数据
     */
    TRAIN("train"),

    /**
     * check，验证数据
     */
    CHECK("check"),
    /**
     * test，测试数据
     */
    TEST("test"),
    /**
     * 所有集
     */
    ALL("all"),
    ;
    private String value;

    DataClassificationEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public static List<String> getValues() {
        List<String> result = new ArrayList();
        for (DataClassificationEnum catalogueEnum : DataClassificationEnum.values()) {
            result.add(catalogueEnum.getValue());
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(DataClassificationEnum.getValues());
    }

}
