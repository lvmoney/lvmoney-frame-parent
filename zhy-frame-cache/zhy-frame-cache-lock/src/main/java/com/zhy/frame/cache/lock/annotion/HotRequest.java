package com.zhy.frame.cache.lock.annotion;/**
 * 描述:
 * 包名:com.zhy.frame.cache.lock.annotion
 * 版本信息: 版本1.0
 * 日期:2020/5/18
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.cache.lock.constant.LockConstant;

import java.lang.annotation.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/18 16:27
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface HotRequest {
    boolean required() default true;

    /**
     * 默认频率10000次/s
     *
     * @throws
     * @return: int
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/18 16:37
     */
    long threshold() default LockConstant.HOT_REQUEST_THRESHOLD;

    /**
     * 默认60秒
     *
     * @throws
     * @return: int
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/18 21:04
     */
    int section() default LockConstant.HOT_REQUEST_SECTION;

    /**
     * 默认 有效时间1800s 30分钟
     *
     * @throws
     * @return: long
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/19 18:14
     */
    long expired() default LockConstant.HOT_REQUEST_EXPIRED;
}
