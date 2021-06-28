package com.lvmoney.frame.blockchain.webase.front.api.vo.item;/**
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
 * @version:v1.0
 * 2021/6/23 15:24  
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Signature implements Serializable {
    private static final long serialVersionUID = -6641232088404374280L;
    /**
     *
     */
    private String r;
    /**
     *
     */
    private String s;
    /**
     *
     */
    private String v;
    /**
     *
     */
    private String signature;
    /**
     *
     */
    private String index;
}
