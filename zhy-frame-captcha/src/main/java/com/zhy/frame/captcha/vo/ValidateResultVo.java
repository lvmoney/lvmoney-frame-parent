package com.zhy.frame.captcha.vo;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/15
 * Copyright XXXXXX有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /XXXXXX有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateResultVo implements Serializable {
    /**
     * 验证码的值
     */
    private String value;
    /**
     * 验证码的图片流
     */
    private String code;
    /**
     * 全局唯一的验证码编号
     */
    private String serialNumber;
}
