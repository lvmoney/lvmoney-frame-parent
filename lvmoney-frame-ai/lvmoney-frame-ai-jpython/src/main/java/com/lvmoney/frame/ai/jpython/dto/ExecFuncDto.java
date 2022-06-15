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
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/10 9:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecFuncDto implements Serializable {

    private static final long serialVersionUID = -8568418509263702787L;
    /**
     * 执行python的路径
     */
    private String pythonPath;
    /**
     * 函数名称
     */
    private String func;
    /**
     * python方法请求参数,key值只能是string，long，integer，boolean，float
     */
    private List<PythonParamDto> param;
}
