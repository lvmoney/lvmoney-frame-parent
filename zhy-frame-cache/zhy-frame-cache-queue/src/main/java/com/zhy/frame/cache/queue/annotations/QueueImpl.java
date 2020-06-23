package com.zhy.frame.cache.queue.annotations;/**
 * 描述:
 * 包名:com.zhy.frame.cache.queue.annotations
 * 版本信息: 版本1.0
 * 日期:2020/5/20
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/20 17:20
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Service
public @interface QueueImpl {
    String name() default "";
}
