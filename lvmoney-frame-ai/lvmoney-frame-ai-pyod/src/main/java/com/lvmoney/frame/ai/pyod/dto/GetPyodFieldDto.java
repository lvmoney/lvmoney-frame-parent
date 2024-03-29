package com.lvmoney.frame.ai.pyod.dto;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.isolationforest.dto
 * 版本信息: 版本1.0
 * 日期:2022/5/12
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/12 13:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetPyodFieldDto implements Serializable {
    private static final long serialVersionUID = 1088858384775505295L;
    /**
     * 字段key
     */
    private String pyodFieldKey;
}
