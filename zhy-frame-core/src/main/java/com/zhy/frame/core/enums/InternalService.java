package com.zhy.frame.core.enums;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.enums
 * 版本信息: 版本1.0
 * 日期:2019/8/19
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/19 11:23
 */
public enum InternalService {
    /**
     * internal 内部服务，服务间可调用的内部服务
     */
    internal("internal"),
    /**
     * external 外部服务，c段用户可访问的服务
     */
    external("external");

    private String value;

    InternalService(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
