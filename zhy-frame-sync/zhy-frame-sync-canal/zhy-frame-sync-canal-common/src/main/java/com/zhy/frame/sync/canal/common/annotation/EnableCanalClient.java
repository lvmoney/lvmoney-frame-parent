package com.zhy.frame.sync.canal.common.annotation;
/**
 * 描述:
 * 包名:com.zhy.common.config
 * 版本信息: 版本1.0
 * 日期:2019/2/28
 * Copyright xxxx科技有限公司
 */

import com.zhy.frame.sync.canal.common.config.CanalClientConfig;
import com.zhy.frame.sync.canal.common.properties.CanalProp;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @describe：通过注解开启canal客户端
 * @author: lvmoney/xxxx科技有限公司
 * @version:v1.0 2019/2/28 10:05
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({CanalProp.class, CanalClientConfig.class})
public @interface EnableCanalClient {
}