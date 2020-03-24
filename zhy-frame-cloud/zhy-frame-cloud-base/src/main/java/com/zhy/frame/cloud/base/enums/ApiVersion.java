package com.zhy.frame.cloud.base.enums;/**
 * 描述:
 * 包名:com.zhy.k8s.base.enums
 * 版本信息: 版本1.0
 * 日期:2019/8/19
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/19 11:23
 */
public enum ApiVersion {
    /**
     * apiversion extensions/v1beta1 ,k8s1.16.0后不再支持
     */
    @Deprecated
    v1beta1("extensions/v1beta1"),

    /**
     * networking.k8s.io/v1
     */
    networkingv1("networking.k8s.io/v1"),

    /**
     * apps/v1
     */
    appsv1("apps/v1"),

    /**
     * apiversion networking.istio.io/v1alpha3
     */
    v1alpha3("networking.istio.io/v1alpha3"),

    /**
     * apiversion v1
     */
    v1("v1");

    private String value;

    ApiVersion(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
