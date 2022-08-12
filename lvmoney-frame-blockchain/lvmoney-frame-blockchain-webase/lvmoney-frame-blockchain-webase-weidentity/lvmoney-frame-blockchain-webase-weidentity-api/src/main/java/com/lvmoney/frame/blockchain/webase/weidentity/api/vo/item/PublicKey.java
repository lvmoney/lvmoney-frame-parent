package com.lvmoney.frame.blockchain.webase.weidentity.api.vo.item;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.weidentity.api.vo
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
 * @version:v1.0 2021/7/5 9:49
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicKey implements Serializable {
    private static final long serialVersionUID = -6606618363237529210L;
    private String id;
    private String type;
    private String owner;
    private String publicKey;
    private Boolean revoked;
}
