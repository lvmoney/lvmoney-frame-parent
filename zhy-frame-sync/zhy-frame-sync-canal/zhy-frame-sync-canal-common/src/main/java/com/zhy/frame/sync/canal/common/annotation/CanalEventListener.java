package com.zhy.frame.sync.canal.common.annotation;
/**
 * 描述:
 * 包名:com.zhy.common.config
 * 版本信息: 版本1.0
 * 日期:2019/2/28
 * Copyright xxxx科技有限公司
 */

import org.springframework.stereotype.Component;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @describe：canal 监听器注解，继承 @Component
 * @author: lvmoney/xxxx科技有限公司
 * @version:v1.0 2019/2/28 10:05
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface CanalEventListener {
    @AliasFor(annotation = Component.class)
    String value() default "";
}
