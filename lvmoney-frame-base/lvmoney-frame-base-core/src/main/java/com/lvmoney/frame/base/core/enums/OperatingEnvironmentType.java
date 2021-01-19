package com.lvmoney.frame.base.core.enums;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway.enums
 * 版本信息: 版本1.0
 * 日期:2019/8/20
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/20 11:58
 */
public enum OperatingEnvironmentType {
    /**
     * istio运行环境 本地开发
     */
    local("local"),
    /**
     * istio运行环境 k8s istio环境
     */
    istio("istio"),
    /**
     * nacos
     */
    nacos("nacos");
    private String value;

    OperatingEnvironmentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
