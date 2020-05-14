package com.zhy.frame.captcha.common.ro;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/12
 * Copyright 四川******科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateCodeRo implements Serializable {
    /**
     * 验证码流水号
     */
    private String serialNumber;
    /**
     * 验证码的值
     */
    private String value;

    private String code;

    private long expire;
}
