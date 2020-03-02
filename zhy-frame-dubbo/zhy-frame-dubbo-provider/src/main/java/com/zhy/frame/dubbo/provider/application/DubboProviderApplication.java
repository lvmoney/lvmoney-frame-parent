package com.zhy.frame.dubbo.provider.application;/**
 * 描述:
 * 包名:com.zhy.frame.dubbo.provider.application
 * 版本信息: 版本1.0
 * 日期:2019/12/27
 * Copyright XXXXXX科技有限公司
 */


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/12/27 14:01
 */
@SpringBootApplication(scanBasePackages = {"com.zhy.**"})
@EnableDubbo
@EnableDiscoveryClient

public class DubboProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }

}
