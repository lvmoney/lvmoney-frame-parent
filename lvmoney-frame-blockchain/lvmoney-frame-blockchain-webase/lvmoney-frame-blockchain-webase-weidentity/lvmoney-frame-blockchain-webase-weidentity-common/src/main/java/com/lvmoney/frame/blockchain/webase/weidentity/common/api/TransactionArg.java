package com.lvmoney.frame.blockchain.webase.weidentity.common.api;/**
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
 * @version:v1.0 2021/6/26 14:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionArg implements Serializable {
    private static final long serialVersionUID = -6580043785380981993L;
    /**
     * 用于索引私钥的WeIdentity DID，服务器端会凭此找到所托管的私钥（非必须）
     */
    private String invokerWeId;
}
