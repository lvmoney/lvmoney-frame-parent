package com.lvmoney.demo.sharding.enums;/**
 * 描述:
 * 包名:com.lvmoney.mysql.separate.enums
 * 版本信息: 版本1.0
 * 日期:2019/9/7
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/7 11:41
 */
public enum LoadBalance {
    /**
     * 从表路由规则 轮询
     */
    round_robin("round_robin"),
    /**
     * 从表路由规则 随机
     */
    random("random");
    private String value;

    LoadBalance(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
