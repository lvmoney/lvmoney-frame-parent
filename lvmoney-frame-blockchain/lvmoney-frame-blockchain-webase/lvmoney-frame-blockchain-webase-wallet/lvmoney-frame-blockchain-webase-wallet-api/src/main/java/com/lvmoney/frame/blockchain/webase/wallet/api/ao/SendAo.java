package com.lvmoney.frame.blockchain.webase.wallet.api.ao;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.wallet.api.ao
 * 版本信息: 版本1.0
 * 日期:2021/6/24
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/24 20:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendAo extends Wallet {
    private static final long serialVersionUID = -6539439772300316296L;
    /**
     * 转入公钥地址
     */
    private String to;
    /**
     * 积分数量
     */
    private String value;
    /**
     * 备注
     */
    private String data;
}
