package com.lvmoney.frame.ai.jpython.dto;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.jpython.dto
 * 版本信息: 版本1.0
 * 日期:2022/5/9
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/9 17:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecPyDto implements Serializable {
    private static final long serialVersionUID = -5831017978179051179L;
    /**
     * 执行python的路径
     */
    private String pythonPath;
}
