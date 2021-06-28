package com.lvmoney.frame.blockchain.webase.sign.api.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.sign.api.ao
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
 * @version:v1.0 2021/6/24 11:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignResult<T> implements Serializable {
    private static final long serialVersionUID = 4375665763609651489L;
    private Integer code;
    private String message;
    private T data;
}
