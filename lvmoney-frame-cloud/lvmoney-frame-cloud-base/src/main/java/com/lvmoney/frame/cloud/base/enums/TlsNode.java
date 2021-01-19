package com.lvmoney.frame.cloud.base.enums;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.enums
 * 版本信息: 版本1.0
 * 日期:2019/10/22
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/10/22 16:11
 */
public enum TlsNode {
    /**
     * 简单模式
     */
    SIMPLE("SIMPLE"),
    /**
     * 双向验证
     */
    MUTUAL("MUTUAL"),
    ;
    private String value;

    TlsNode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
