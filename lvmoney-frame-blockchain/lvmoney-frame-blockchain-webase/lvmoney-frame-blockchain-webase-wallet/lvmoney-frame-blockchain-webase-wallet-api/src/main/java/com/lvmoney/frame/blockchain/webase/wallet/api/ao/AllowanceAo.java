package com.lvmoney.frame.blockchain.webase.wallet.api.ao;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.wallet.api.ao
 * 版本信息: 版本1.0
 * 日期:2021/6/28
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/28 14:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllowanceAo extends Wallet {
    private static final long serialVersionUID = 7909237813851388021L;
    /**
     * 指定公钥地址
     */
    private String owner;
    /**
     * 被授权的公钥地址
     */
    private String spender;
}
