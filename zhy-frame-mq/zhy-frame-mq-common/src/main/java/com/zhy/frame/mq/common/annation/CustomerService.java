package com.zhy.frame.mq.common.annation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * @describe：调用的方法注解
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月2日 上午11:53:59
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Service
public @interface CustomerService {

    String name() default "";

    // not use
    // boolean offlineAble = true;

}
