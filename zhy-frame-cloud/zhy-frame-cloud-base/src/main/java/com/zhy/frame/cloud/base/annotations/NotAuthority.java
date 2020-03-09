package com.zhy.frame.cloud.base.annotations;/**
 * 描述:
 * 包名:com.zhy.frame.cloud.base.annotations
 * 版本信息: 版本1.0
 * 日期:2020/3/7
 * Copyright XXXXXX科技有限公司
 */


import java.lang.annotation.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/7 12:14
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface NotAuthority {
    boolean required() default true;
}
