package com.lvmoney.frame.transaction.mq.provider.dto;/**
 * 描述:
 * 包名:com.lvmoney.frame.transaction.mq.provider.dto
 * 版本信息: 版本1.0
 * 日期:2020/11/22
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/11/22 16:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto<T> implements Serializable {
    /**
     * 类型
     */
    private String type;
    /**
     * 数据
     */
    private T data;
}
