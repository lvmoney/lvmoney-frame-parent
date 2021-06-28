package com.lvmoney.frame.blockchain.webase.trans.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.trans.vo
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
 * @version:v1.0 2021/6/24 19:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransResult<T> implements Serializable {
    private static final long serialVersionUID = -3883523890254856132L;
    private Integer code;
    private String message;
    private T data;
}
