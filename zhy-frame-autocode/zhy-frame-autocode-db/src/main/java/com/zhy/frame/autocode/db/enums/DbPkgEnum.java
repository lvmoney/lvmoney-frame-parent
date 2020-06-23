package com.zhy.frame.autocode.db.enums;/**
 * 描述:
 * 包名:com.zhy.frame.autocode.db.enums
 * 版本信息: 版本1.0
 * 日期:2020/6/18
 * Copyright XXXXXX科技有限公司
 */


import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/18 14:07
 */
public enum DbPkgEnum {
    /**
     * external.controller
     */
    EXTERNAL_CONTROLLER("external.controller"),
    /**
     * internal.controller
     */
    INTERNAL_CONTROLLER("internal.controller"),

    ;
    private String value;

    DbPkgEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public static List<String> getValues() {
        List<String> result = new ArrayList();
        for (DbPkgEnum dbPkgEnum : DbPkgEnum.values()) {
            result.add(dbPkgEnum.getValue());
        }
        return result;
    }

}
