package com.lvmoney.frame.mq.common.annotations;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * @describe：调用的方法注解
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月2日 上午11:53:59
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Service
public @interface CustomerService {

    String name() default "";

    // not use
    // boolean offlineAble = true;

}
