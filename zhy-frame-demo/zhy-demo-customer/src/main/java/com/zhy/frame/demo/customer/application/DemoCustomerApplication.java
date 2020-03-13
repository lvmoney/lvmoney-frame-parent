package com.zhy.frame.demo.customer.application;/**
 * 描述:
 * 包名:com.zhy.demo.customer.application
 * 版本信息: 版本1.0
 * 日期:2020/3/5
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/5 15:24
 */

@SpringCloudApplication
@ComponentScan(basePackages = {
        "com.zhy.**"
})
@EnableFeignClients(basePackages = {"com.zhy.**"})
@EnableCircuitBreaker
public class DemoCustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoCustomerApplication.class, args);
    }
}
