package com.lvmoney.frame.ai.lstm.ro.item;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.isolationforest.ro.item
 * 版本信息: 版本1.0
 * 日期:2022/5/12
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
 * @version:v1.0 2022/5/12 13:40
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LstmMultivariableField implements Serializable {
    private static final long serialVersionUID = 8642784085096626348L;
    /**
     * 预测的字段
     */
    private String predField;

    /**
     * 原始的字段
     */
    private List<String> originalField;

}
