package com.zhy.frame.autocode.common.enums;/**
 * 描述:
 * 包名:com.zhy.frame.autocode.catalogue.enums
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
public enum CatalogueEnum {
    /**
     * application
     */
    APPLICATION("application.application"),
    /**
     * entity
     */
    ENTITY("entity"),
    /**
     * feign.client
     */
    CLIENT("feign.client"),
    /**
     * external.dao
     */
    EXTERNAL_DAO("external.dao"),
    /**
     * external.service
     */
    EXTERNAL_SERVICE("external.service"),
    /**
     * external.service.impl
     */
    EXTERNAL_IMPL("external.service.impl"),
    /**
     * external.dto
     */
    EXTERNAL_DTO("external.dto"),
    /**
     * external.po
     */
    EXTERNAL_PO("external.po"),

    /****************华丽的分割线*****************/

    /**
     * internal.dao
     */
    INTERNAL_DAO("internal.dao"),
    /**
     * internal.service
     */
    INTERNAL_SERVICE("internal.service"),
    /**
     * internal.service.impl
     */
    INTERNAL_IMPL("internal.service.impl"),
    /**
     * internal.dto
     */
    INTERNAL_DTO("internal.dto"),
    /**
     * internal.po
     */
    INTERNAL_PO("internal.po"),
    /**
     * api.ao
     */
    AO("api.ao"),
    /**
     * api.vo
     */
    VO("api.vo"),
    /**
     * api.surface
     */
    SURFACE("api.surface"),
    /**
     * common.exception
     */
    EXCEPTION("common.exception"),
    /**
     * common.constant
     */
    CONSTANT("common.constant"),
    /**
     * common.annotations
     */
    ANNOTATION("common.annotations"),
    /**
     * apiserver.controller
     */
    APISERVER_CONTROLLER("apiserver.controller"),
    /**
     * apply.controller
     */
    APPLY_CONTROLLER("apply.controller"),
    ;
    private String value;

    CatalogueEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


    public static List<String> getValues() {
        List<String> result = new ArrayList();
        for (CatalogueEnum catalogueEnum : CatalogueEnum.values()) {
            result.add(catalogueEnum.getValue());
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(CatalogueEnum.getValues());
    }

}
