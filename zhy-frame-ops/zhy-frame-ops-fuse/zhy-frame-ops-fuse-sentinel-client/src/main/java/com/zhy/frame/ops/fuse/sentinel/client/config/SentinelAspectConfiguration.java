package com.zhy.frame.ops.fuse.sentinel.client.config;/**
 * 描述:
 * 包名:com.zhy.frame.ops.fuse.sentinel.client.config
 * 版本信息: 版本1.0
 * 日期:2020/4/10
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/10 9:43
 */
@Configuration
public class SentinelAspectConfiguration {
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}
