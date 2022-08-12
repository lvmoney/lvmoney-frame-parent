package com.lvmoney.frame.blockchain.webase.weidentity.api.ao;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.weidentity.api.ao
 * 版本信息: 版本1.0
 * 日期:2021/7/5
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/7/5 11:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeIdRequestAo<T> implements Serializable {
    /**
     * 随调用SDK方法而变的入参json字符串
     */
    private T functionArg;
    /**
     * 交易参数json字符串
     */
    private TransactionArg transactionArg;
}
