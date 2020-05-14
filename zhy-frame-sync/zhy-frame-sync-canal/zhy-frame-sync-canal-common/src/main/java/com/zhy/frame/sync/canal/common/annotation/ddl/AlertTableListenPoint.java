package com.zhy.frame.sync.canal.common.annotation.ddl;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.zhy.frame.sync.canal.common.annotation.ListenPoint;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @describe：修改字段属性监听器
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ListenPoint(eventType = CanalEntry.EventType.ALTER)
public @interface AlertTableListenPoint {
    /**
     * canal 指令
     * default for all
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
     * default for all
     */
    @AliasFor(annotation = ListenPoint.class)
    String[] table() default {};
}
