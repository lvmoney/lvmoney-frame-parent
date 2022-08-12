package com.lvmoney.frame.blockchain.webase.front.api.ao;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.front.api.ao
 * 版本信息: 版本1.0
 * 日期:2021/6/24
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
 * @version:v1.0 2021/6/24 9:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DecodeAo implements Serializable {
    private static final long serialVersionUID = 2647253067709609100L;
    /**
     * 解析类型
     */
    private Integer decodeType;
    /**
     * 返回类型
     */
    private Integer returnType;
    /**
     * 交易输入
     */
    private String input;
    /**
     * 交易输出
     */
    private String output;
    /**
     * 合约ABI
     */
    private List abiList;
}
