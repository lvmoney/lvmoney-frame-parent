package com.lvmoney.frame.blockchain.webase.weidentity.api.ao;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.weidentity.api.ao
 * 版本信息: 版本1.0
 * 日期:2021/7/6
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司 
 * @version:v1.0
 * 2021/7/6 13:35  
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCredentialPojoAo<T> implements Serializable {
    private static final long serialVersionUID = 7275381854098001974L;
    /**
     * issuer WeIdentity DID
     */
    private String issuer;
    /**
     * CPT ID
     */
    private String cptId;
    /**
     * 过期时间（使用UTC格式）
     */
    private String expirationDate;
    /**
     * claim Json结构体，与SDK直接调用的方式入参一致，下同
     */
    private T chaim;
}
