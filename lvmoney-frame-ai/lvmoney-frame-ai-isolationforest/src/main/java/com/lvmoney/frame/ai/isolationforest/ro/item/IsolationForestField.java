package com.lvmoney.frame.ai.isolationforest.ro.item;/**
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
public class IsolationForestField implements Serializable {
    private static final long serialVersionUID = -3377950432872383868L;
    /**
     * 预测结果字段
     */
    private String clazz;
    /**
     * 预测字段
     */
    private List<String> field;

}
