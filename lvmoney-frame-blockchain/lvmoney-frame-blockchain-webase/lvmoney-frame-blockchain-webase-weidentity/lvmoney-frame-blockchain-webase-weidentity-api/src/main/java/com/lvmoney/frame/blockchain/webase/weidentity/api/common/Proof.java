package com.lvmoney.frame.blockchain.webase.weidentity.api.common;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.weidentity.api.vo.item
 * 版本信息: 版本1.0
 * 日期:2021/7/5
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.blockchain.webase.weidentity.api.vo.item.Salt;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/7/5 18:30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proof implements Serializable {
    private static final long serialVersionUID = -4701155396533849218L;
    /**
     *
     */
    private String created;
    /**
     *
     */
    private String creator;
    /**
     *
     */
    private String signature;
    /**
     *
     */
    private String type;
    /**
     *
     */
    private String signatureValue;
    /**
     *
     */
    private Salt  salt;
}
