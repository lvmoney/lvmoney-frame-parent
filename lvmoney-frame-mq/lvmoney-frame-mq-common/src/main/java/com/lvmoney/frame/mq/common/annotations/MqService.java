package com.lvmoney.frame.mq.common.annotations;/**
 * 描述:
 * 包名:com.lvmoney.frame.cache.common.annotations
 * 版本信息: 版本1.0
 * 日期:2019/11/20
 * Copyright 四川******科技有限公司
 */


import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/11/20 11:06
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Service
public @interface MqService {
    @AliasFor(annotation = Service.class)
    String value() default "";
}
