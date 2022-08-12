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

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/28 11:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendFromAo extends Wallet {
    private static final long serialVersionUID = 6737530864009512816L;
    /**
     * 转出公钥地址
     */
    private String from;
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
