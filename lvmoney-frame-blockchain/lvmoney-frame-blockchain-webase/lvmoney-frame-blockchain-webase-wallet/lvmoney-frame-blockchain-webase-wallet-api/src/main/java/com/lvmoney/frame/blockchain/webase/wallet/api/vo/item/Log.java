package com.lvmoney.frame.blockchain.webase.wallet.api.vo.item;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.wallet.api.vo.item
 * 版本信息: 版本1.0
 * 日期:2021/6/28
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
 * @version:v1.0
 * 2021/6/28 10:30  
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log implements Serializable {
    private static final long serialVersionUID = -865585636163865491L;
    /**
     *
     */
    private String address;
    /**
     *
     */
    private List<String> topics;
    /**
     *
     */
    private String data;
    /**
     *
     */
    private String blockNumber;
}
