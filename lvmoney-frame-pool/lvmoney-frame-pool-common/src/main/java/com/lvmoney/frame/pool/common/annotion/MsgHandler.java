package com.lvmoney.frame.pool.common.annotion;/**
 * 描述:
 * 包名:com.lvmoney.frame.pool.disruptor.annotion
 * 版本信息: 版本1.0
 * 日期:2020/4/27
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/27 21:54
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Service
public @interface MsgHandler {
    String name() default "";
}
