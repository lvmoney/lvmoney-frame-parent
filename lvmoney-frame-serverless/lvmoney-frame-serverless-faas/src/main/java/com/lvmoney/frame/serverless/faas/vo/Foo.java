package com.lvmoney.frame.serverless.faas.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.serverless.faas.vo
 * 版本信息: 版本1.0
 * 日期:2020/5/22
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/22 11:01
 */
public class Foo {
    private String value;

    Foo() {
    }

    public String lowercase() {
        return value.toLowerCase();
    }

    public Foo(String value) {
        this.value = value;
    }

    public String uppercase() {
        return value.toUpperCase();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
