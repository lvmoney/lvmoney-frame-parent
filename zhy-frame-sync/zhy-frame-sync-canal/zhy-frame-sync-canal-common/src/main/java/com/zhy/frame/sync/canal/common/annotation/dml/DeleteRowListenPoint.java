package com.zhy.frame.sync.canal.common.annotation.dml;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.zhy.frame.sync.canal.common.annotation.ListenPoint;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 删除表数据操作监听器
 *
 * @Author: lvmoney
 * @data: 2019/4/17/017 8:17
 * @Version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ListenPoint(eventType = CanalEntry.EventType.DELETE)
public @interface DeleteRowListenPoint {

    /**
     * canal 指令
     */
    @AliasFor(annotation = ListenPoint.class)
    String destination() default "";


    /**
     * 数据库实例
     */
    @AliasFor(annotation = ListenPoint.class)
    String[] schema() default {};

    /**
     * 监听的表
     */
    @AliasFor(annotation = ListenPoint.class)
    String[] table() default {};

}
