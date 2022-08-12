package com.lvmoney.frame.transaction.mq.common.enums;/**
 * 描述:
 * 包名:mq.enums
 * 版本信息: 版本1.0
 * 日期:2020/11/12
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/11/12 15:55
 */
public enum StatusEnum {
    /**
     * provider 待发布
     */
    NEW("待发布"),
    /**
     * provider 已发布
     */
    PUBLISHED("已发布"),
    /**
     * customer 已收到
     */
    RECEIVED("已收到"),
    /**
     * customer 已处理
     */
    PROCESSED("已处理"),
    ;

    private String status;

    public String getStatus() {
        return status;
    }

    StatusEnum() {
    }

    StatusEnum(String status) {
        this.status = status;
    }
}
