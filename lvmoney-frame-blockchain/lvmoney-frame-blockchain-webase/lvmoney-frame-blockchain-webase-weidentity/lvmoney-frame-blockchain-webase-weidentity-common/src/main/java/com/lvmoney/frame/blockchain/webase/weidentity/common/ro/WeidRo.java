package com.lvmoney.frame.blockchain.webase.weidentity.common.ro;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.common.ro
 * 版本信息: 版本1.0
 * 日期:2021/7/2
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/7/2 10:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeidRo implements Serializable {
    /**
     * 随机数
     */
    private String nonce;
    /**
     * 私钥
     */
    private String privateKey;
    /**
     * 公钥
     */
    private String publicKey;
}
