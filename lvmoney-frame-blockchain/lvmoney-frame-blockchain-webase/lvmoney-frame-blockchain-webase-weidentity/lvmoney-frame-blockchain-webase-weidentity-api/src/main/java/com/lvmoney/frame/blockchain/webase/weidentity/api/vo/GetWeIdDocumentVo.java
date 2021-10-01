package com.lvmoney.frame.blockchain.webase.weidentity.api.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.weidentity.api.vo
 * 版本信息: 版本1.0
 * 日期:2021/7/5
 * Copyright XXXXXX科技有限公司
 */


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lvmoney.frame.blockchain.webase.weidentity.api.vo.item.Authentication;
import com.lvmoney.frame.blockchain.webase.weidentity.api.vo.item.PublicKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/7/5 9:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetWeIdDocumentVo implements Serializable {
    private static final long serialVersionUID = 2931966109577880639L;
    @JsonProperty("@context")
    private String context;
    private Long created;
    private String id;
    private List<PublicKey> publicKey;
    private List<Authentication> authentication;
    private List service;
    private String updated;
}
