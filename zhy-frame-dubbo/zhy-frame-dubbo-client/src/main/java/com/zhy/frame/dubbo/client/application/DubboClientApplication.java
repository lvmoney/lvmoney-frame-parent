package com.zhy.frame.dubbo.client.application;/**
 * 描述:
 * 包名:com.zhy.frame.dubbo.client.application
 * 版本信息: 版本1.0
 * 日期:2019/12/27
 * Copyright XXXXXX科技有限公司
 */

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/12/27 17:35
 */
@SpringBootApplication(scanBasePackages = {"com.zhy.**"})
@EnableDubbo
@EnableFeignClients(basePackages = {"com.zhy.**"})
@EnableDiscoveryClient
public class DubboClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboClientApplication.class, args);
    }
}
