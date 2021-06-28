package com.lvmoney.frame.blockchain.webase.front.api.ao.item;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.front.api.ao.item
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
 * @version:v1.0
 * 2021/6/24 10:36  
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Input implements Serializable {
    private static final long serialVersionUID = 7190628066225766284L;
    private String type;
    private String name;
}
