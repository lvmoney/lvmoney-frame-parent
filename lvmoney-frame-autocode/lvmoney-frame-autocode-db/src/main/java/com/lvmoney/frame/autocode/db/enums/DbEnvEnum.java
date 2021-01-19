package com.lvmoney.frame.autocode.db.enums;/**
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
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/17 14:49
 */
public enum DbEnvEnum {
    /**
     * all
     */
    ALL("all"),
    /**
     * entity
     */
    INTERNAL("internal"),
    /**
     * feign.client
     */
    EXTERNAL("external"),
    ;
    private String value;

    DbEnvEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public static List<String> getValues() {
        List<String> result = new ArrayList();
        for (DbEnvEnum dbEnvEnum : DbEnvEnum.values()) {
            result.add(dbEnvEnum.getValue());
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(DbEnvEnum.getValues());
    }

}
