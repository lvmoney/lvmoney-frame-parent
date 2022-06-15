package com.lvmoney.frame.ai.jpython.dto;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.jpython.dto
 * 版本信息: 版本1.0
 * 日期:2022/5/10
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/10 14:56
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PythonParamDto implements Serializable {
    private static final long serialVersionUID = 5136559352250361177L;
    /**
     * 参数类型
     */
    private String type;
    /**
     * 值
     */
    private Object value;
}
