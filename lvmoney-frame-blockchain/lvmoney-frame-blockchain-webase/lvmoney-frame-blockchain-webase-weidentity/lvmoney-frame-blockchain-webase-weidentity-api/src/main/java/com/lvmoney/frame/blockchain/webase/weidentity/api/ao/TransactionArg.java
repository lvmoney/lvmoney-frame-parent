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
 * @version:v1.0 2021/6/26 14:53
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionArg implements Serializable {
    private static final long serialVersionUID = -5913244082295584686L;
    /**
     * 用于索引私钥的WeIdentity DID，服务器端会凭此找到所托管的私钥（非必须）
     */
    private String invokerWeId;
    /**
     * 用于防止重放攻击的交易随机数
     *
     * @param null:
     * @return: null
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/30 19:07
     */
    private String nonce;
    /**
     * 交易特征值，需和第一步中返回值一致
     */
    private String data;
    /**
     * 基于Base64编码的、使用私钥签名之后的encodedTransaction签名值
     */
    private String signedMessage;

    private String blockLimit;

    private String SignType;
}
