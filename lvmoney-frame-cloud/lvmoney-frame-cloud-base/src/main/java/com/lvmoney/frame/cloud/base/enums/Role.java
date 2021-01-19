package com.lvmoney.frame.cloud.base.enums;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.enums
 * 版本信息: 版本1.0
 * 日期:2019/10/18
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/10/18 14:56
 */
public enum Role {
    /**
     * Promethues集成了对Kubernetes的自动发现:node
     */
    node("node"),
    /**
     * Promethues集成了对Kubernetes的自动发现:service
     */
    service("service"),
    /**
     * Promethues集成了对Kubernetes的自动发现:pod
     */
    pod("pod"),
    /**
     * Promethues集成了对Kubernetes的自动发现:endpoints
     */
    endpoints("endpoints"),
    /**
     * Promethues集成了对Kubernetes的自动发现:ingress
     */
    ingress("ingress"),


    ;
    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
