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
 * @version:v1.0 2019/8/19 15:43
 */
public enum HttpProtocol {
    /**
     * http请求协议
     */
    HTTP("HTTP"),
    /**
     * https请求协议
     */
    HTTPS("HTTPS");
    private String value;

    HttpProtocol(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
