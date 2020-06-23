package com.zhy.frame.oss.common.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;


/**
 * @describe：调用的方法注解
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月2日 上午11:53:59
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Autowired
@Qualifier
public @interface OssImpl {
    @AliasFor(annotation = Qualifier.class)
    String value() default "";
    // not use
    // boolean offlineAble = true;

}
