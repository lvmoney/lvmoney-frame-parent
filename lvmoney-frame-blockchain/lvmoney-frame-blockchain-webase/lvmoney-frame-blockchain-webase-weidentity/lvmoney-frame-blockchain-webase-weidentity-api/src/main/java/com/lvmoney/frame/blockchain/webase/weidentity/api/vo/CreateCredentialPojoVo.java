package com.lvmoney.frame.blockchain.webase.weidentity.api.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.weidentity.api.vo
 * 版本信息: 版本1.0
 * 日期:2021/7/6
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.blockchain.webase.weidentity.api.common.Proof;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/7/6 13:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCredentialPojoVo<T> implements Serializable {
    /**
     *
     */
    private String context;
    /**
     *
     */
    private Long expirationDate;
    /**
     *
     */
    private String id;
    /**
     *
     */
    private String issuanceDate;
    /**
     *
     */
    private String issuer;
    /**
     *
     */
    private Long cptId;
    /**
     *
     */
    private T claim;
    /**
     *
     */
    private Proof proof;
    /**
     *
     */
    private List<String> type;
}
