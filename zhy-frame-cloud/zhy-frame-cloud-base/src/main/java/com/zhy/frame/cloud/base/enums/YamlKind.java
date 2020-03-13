package com.zhy.frame.cloud.base.enums;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.enums
 * 版本信息: 版本1.0
 * 日期:2019/8/19
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：yaml kind
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/19 11:16
 */
public enum YamlKind {
    /**
     * istio yml 组件类型Service
     */
    Service("Service"),
    /**
     * istio yml 组件类型Deployment
     */
    Deployment("Deployment"),
    /**
     * istio yml 组件类型Gateway
     */
    Gateway("Gateway"),
    /**
     * istio yml 组件类型VirtualService
     */
    VirtualService("VirtualService"),
    /**
     * istio yml 组件类型Ingress
     */
    Ingress("Ingress"),
    /**
     * istio yml 组件类型DestinationRule
     */
    DestinationRule("DestinationRule"),
    /**
     * istio yml 组件类型listchecker
     */
    listchecker("listchecker"),
    /**
     * istio yml 组件类型QuotaSpec
     */
    QuotaSpec("QuotaSpec"),
    /**
     * istio yml 组件类型listentry
     */
    listentry("listentry"),
    /**
     * istio yml 组件类型rule
     */
    rule("rule"),
    /**
     * istio yml 组件类型memquota
     */
    memquota("memquota"),
    /**
     * istio yml 组件类型QuotaSpecBinding
     */
    QuotaSpecBinding("QuotaSpecBinding"),
    /**
     * istio yml 组件类型quota
     */
    quota("quota"),
    /**
     * istio yml 组件类型handler
     */
    handler("handler"),
    /**
     * istio yml 组件类型instance
     */
    instance("instance");
    private String value;

    YamlKind(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
