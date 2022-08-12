package com.lvmoney.frame.blockchain.webase.front.api.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.front.api.vo
 * 版本信息: 版本1.0
 * 日期:2021/6/23
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/23 14:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrivateKeyVo implements Serializable {
    private static final long serialVersionUID = -231469596098942068L;
    /**
     * 用户地址
     */
    private String address;
    /**
     * 公钥
     */
    private String publicKey;
    /**
     * 私钥
     */
    private String privateKey;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户id
     */
    private String signUserId;
    /**
     * appId
     */
    private String appId;
    /**
     * 0-本地用户；1-本地随机；2-外部用户
     */
    private Integer type;
}
