package com.lvmoney.frame.base.validator.annotations;/**
 * 描述:
 * 包名:com.lvmoney.frame.base.validator.annotations
 * 版本信息: 版本1.0
 * 日期:2021/12/14
 * Copyright XXXXXX科技有限公司
 *
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司 
 * @version:v1.0
 * 2021/12/14 12:56  
 */

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.PARAMETER, ElementType.METHOD})
public @interface JsonValid {

    String schemaName();

    String value() default "Json数据校验失败";
}
