package com.lvmoney.frame.blockchain.webase.weidentity.api.vo;/**
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
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/7/5 11:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryAuthorityIssuerVo implements Serializable {
    private static final long serialVersionUID = 9134049105767691020L;
    /**
     *
     */
    private String accValue;
    /**
     *
     */
    private String description;
    /**
     *
     */
    private Long created;
    /**
     *
     */
    private String name;
    /**
     *
     */
    private String weId;
    /**
     *
     */
    private List extraInt;
    /**
     *
     */
    private List extraStr32;
}
