package com.zhy.frame.job.autoconfigure;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import com.zhy.frame.job.builder.XxlJobSpringExecutorBuilder;
import com.zhy.frame.job.properties.XxlJobProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @describe：job任务执行器配置类
 * @author: zhy-dev
 * @version:v1.0
 * $2019-11-19
 */

@Configuration
@EnableConfigurationProperties({XxlJobProperties.class})
@Slf4j
public class XxlJobAutoConfiguration {

    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobSpringExecutor xxlJobSpringExecutor(XxlJobProperties prop) {
        log.info(">>>>>>>>>>> xxl job config init...");
        return new XxlJobSpringExecutorBuilder()
                .withAdminAddresses(prop.getAdmin().getAdminAddresses())
                .withAppName(prop.getExecutor().getAppName())
                .withIp(prop.getExecutor().getIp())
                .withPort(prop.getExecutor().getPort())
                .withAccessToken(prop.getExecutor().getAccessToken())
                .withLogPath(prop.getExecutor().getLogPath())
                .withLogRetentionDays(prop.getExecutor().getLogRetentionDays())
                .build();
    }
}
