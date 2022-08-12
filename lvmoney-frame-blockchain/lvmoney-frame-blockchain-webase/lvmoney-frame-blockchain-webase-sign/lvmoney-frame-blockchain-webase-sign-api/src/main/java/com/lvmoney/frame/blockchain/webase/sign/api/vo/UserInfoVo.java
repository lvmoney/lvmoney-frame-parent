package com.lvmoney.frame.blockchain.webase.sign.api.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.sign.api.vo
 * 版本信息: 版本1.0
 * 日期:2021/6/24
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/24 11:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVo implements Serializable {
    private static final long serialVersionUID = 3117673830536168172L;
    /**
     *
     */
    private String signUserId;
    /**
     *
     */
    private String appId;
    /**
     *
     */
    private String address;
    /**
     *
     */
    private String publicKey;
    /**
     *
     */
    private String privateKey;
    /**
     *
     */
    private String description;
    /**
     *
     */
    private Integer encryptType;
}
