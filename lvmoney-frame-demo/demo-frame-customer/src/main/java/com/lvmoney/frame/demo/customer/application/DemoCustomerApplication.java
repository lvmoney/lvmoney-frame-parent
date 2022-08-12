package com.lvmoney.frame.demo.customer.application;/**
 * 描述:
 * 包名:com.lvmoney.demo.customer.application
 * 版本信息: 版本1.0
 * 日期:2020/3/5
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/5 15:24
 */

@SpringCloudApplication
@ComponentScan(basePackages = {
        "com.lvmoney.**"
})
@EnableFeignClients(basePackages = {"com.lvmoney.**"})
@EnableCircuitBreaker
@EnableDiscoveryClient
public class DemoCustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoCustomerApplication.class, args);
    }
}
