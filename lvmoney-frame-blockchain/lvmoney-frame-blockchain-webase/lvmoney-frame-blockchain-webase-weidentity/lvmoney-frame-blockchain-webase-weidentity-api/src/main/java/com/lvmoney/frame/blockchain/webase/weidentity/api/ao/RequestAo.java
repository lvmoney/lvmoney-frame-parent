package com.lvmoney.frame.blockchain.webase.weidentity.api.ao;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.weidentity.common.api
 * 版本信息: 版本1.0
 * 日期:2021/6/26
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/26 14:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestAo<T> implements Serializable {
    private static final long serialVersionUID = 198536650863057937L;
    /**
     * 随调用SDK方法而变的入参json字符串
     */
    private T functionArg;
    /**
     * 交易参数json字符串
     */
    private TransactionArg transactionArg;
    /**
     * 调用SDK方法名
     */
    private String functionName;
    /**
     * API版本号
     */
    private String v;
}
