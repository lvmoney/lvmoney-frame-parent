package com.zhy.frame.sync.canal.common.annotation.dml;/**
 * 描述:
 * 包名:com.zhy.bigdata.canal.redis.annotation.dml
 * 版本信息: 版本1.0
 * 日期:2019/7/19
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.otter.canal.protocol.CanalEntry;
import com.zhy.frame.sync.canal.common.annotation.ListenPoint;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @describe：清空表数据操作监听器
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/19 16:11
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ListenPoint(eventType = CanalEntry.EventType.TRUNCATE)
public @interface TruncateListenPoint {
    /**
     * canal 指令
     * default for all
     *
     * @return canal destination
     */
    @AliasFor(annotation = ListenPoint.class)
    String destination() default "";

    /**
     * 数据库实例
     *
     * @return canal destination
     */
    @AliasFor(annotation = ListenPoint.class)
    String[] schema() default {};

    /**
     * 监听的表
     * default for all
     *
     * @return canal destination
     */
    @AliasFor(annotation = ListenPoint.class)
    String[] table() default {};
}
