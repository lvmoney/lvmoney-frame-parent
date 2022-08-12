package com.lvmoney.frame.ai.jpython.dto;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.jpython.dto
 * 版本信息: 版本1.0
 * 日期:2022/5/13
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
 * @version:v1.0 2022/5/13 16:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecProcessDto implements Serializable {
    private static final long serialVersionUID = 6353938401413565584L;
    /**
     * 执行python的路径
     */
    private String pythonPath;
    /**
     * 入参
     */
    private List<String> input;
}
