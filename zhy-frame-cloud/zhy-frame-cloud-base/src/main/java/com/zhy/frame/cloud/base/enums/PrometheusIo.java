package com.zhy.frame.cloud.base.enums;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.enums
 * 版本信息: 版本1.0
 * 日期:2019/10/18
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：meta_kubernetes prometheus.io
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/10/18 14:41
 */


public enum PrometheusIo {
    /**
     * prometheus.io/path
     */
    PATH("prometheus.io/path"),
    /**
     * prometheus.io/port
     */
    PORT("prometheus.io/port"),
    /**
     * prometheus.io/scrape
     */
    CRAPE("prometheus.io/scrape"),
    ;
    private String value;

    PrometheusIo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
