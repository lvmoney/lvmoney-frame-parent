package com.lvmoney.frame.sync.canal.common.annotation.ddl;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.lvmoney.frame.sync.canal.common.annotation.ListenPoint;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @describe：刪除表操作监听器
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/7/19 16:11
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ListenPoint(eventType = CanalEntry.EventType.ERASE)
public @interface DropTableListenPoint {
    /**
     * canal 指令
     * default for all
     *
     * @return canal destination
     * @author lvmoney
     * @time 2018/5/28 15:49
     */
    @AliasFor(annotation = ListenPoint.class)
    String destination() default "";

    /**
     * 数据库实例
     *
     * @return canal destination
     * @author lvmoney
     * @time 2018/5/28 15:49
     */
    @AliasFor(annotation = ListenPoint.class)
    String[] schema() default {};
}
