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
 * @version:v1.0 2022/5/9 17:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecStrDto implements Serializable {
    /**
     * python 代码字符串
     */
    private String pythonCode;
}
