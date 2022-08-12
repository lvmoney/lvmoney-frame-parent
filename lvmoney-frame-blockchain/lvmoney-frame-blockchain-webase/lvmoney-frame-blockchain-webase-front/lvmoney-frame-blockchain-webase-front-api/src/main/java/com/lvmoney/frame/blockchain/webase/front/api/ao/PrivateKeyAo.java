package com.lvmoney.frame.blockchain.webase.front.api.ao;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.front.api.ao
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
public class PrivateKeyAo implements Serializable {
    /**
     * 私钥类型
     */
    private Integer type;
    /**
     * 用户名
     */
    private String userName;
    /**
     * appId
     */
    private String appId;
    /**
     * signUserId
     */
    private String signUserId;
    /**
     * 是否返回私钥，true：返回，false：不返回
     */
    private Boolean returnPrivateKey;
}
