package com.lvmoney.frame.base.validator.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.base.validator.vo
 * 版本信息: 版本1.0
 * 日期:2021/12/14
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/12/14 16:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonValidatorVo implements Serializable {
    private static final long serialVersionUID = -2395590992074614309L;
    /**
     * 错误描述
     */
    private String defaultMessage;
    /**
     * 字段名称
     */
    private String field;

}
