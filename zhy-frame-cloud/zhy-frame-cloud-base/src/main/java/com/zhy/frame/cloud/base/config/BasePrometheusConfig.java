package com.zhy.frame.cloud.base.config;/**
 * 描述:
 * 包名:com.zhy.k8s.base.handler
 * 版本信息: 版本1.0
 * 日期:2019/10/16
 * Copyright XXXXXX科技有限公司
 */


import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/10/16 16:49
 */
@Configuration
public class BasePrometheusConfig {
    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name}") String applicationName) {
        return (registry) -> registry.config().commonTags("application", applicationName);
    }


}
