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
 * @version:v1.0 2019/8/19 15:33
 */
public enum Istio {
    /**
     * ingress istio名称，用于共享
     */
    ingressgateway("ingressgateway");

    private String value;

    Istio(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
