package com.zhy.frame.sync.canal.common.annotation.ddl;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.zhy.frame.sync.canal.common.annotation.ListenPoint;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @describe：表结构发生变化，新增时，先判断数据库实例是否存在，不存在则创建
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ListenPoint(eventType = CanalEntry.EventType.CREATE)
public @interface CreateTableListenPoint {

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
}
