package com.lvmoney.frame.cache.repeatsubmit.annotation;/**
 * 描述:
 * 包名:com.lvmoney.repeatsubmit.annotations
 * 版本信息: 版本1.0
 * 日期:2019/10/16
 * Copyright 四川******科技有限公司
 */


import com.lvmoney.frame.cache.repeatsubmit.enums.NoRepeatSubmitEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @describe：防止重复提交标记注解
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/10/16 17:53
 */
@Target(ElementType.METHOD) // 作用到方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时有效
public @interface NoRepeatSubmit {
    /**
     * 被允许重复提交
     */
    NoRepeatSubmitEnum required();

    /**
     * 时间
     */
    int time() default 10;
}
