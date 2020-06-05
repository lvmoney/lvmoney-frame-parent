package com.zhy.frame.dispatch.feign.config;/**
 * 描述:
 * 包名:com.zhy.frame.dispatch.feign.handler
 * 版本信息: 版本1.0
 * 日期:2020/3/13
 * Copyright XXXXXX科技有限公司
 */


import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/13 11:16
 */
public class DisableHystrix {

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }
}
