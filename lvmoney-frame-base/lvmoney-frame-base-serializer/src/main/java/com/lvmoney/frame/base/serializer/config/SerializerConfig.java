package com.lvmoney.frame.base.serializer.config;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/2/27
 * Copyright 四川******科技有限公司
 */

import com.lvmoney.frame.base.interceptor.config.AbstractInterceptorConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @describe：1、这里配置了序列化 2、如果一个系统有多个拦截器需要加入，extends  SerializerConfig把拦截器加入进来
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 * @Configuration
 */
@Configuration
public class SerializerConfig extends AbstractInterceptorConfig {
}
