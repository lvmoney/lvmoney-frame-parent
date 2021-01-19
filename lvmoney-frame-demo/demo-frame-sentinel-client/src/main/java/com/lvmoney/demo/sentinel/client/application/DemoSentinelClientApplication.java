package com.lvmoney.demo.sentinel.client.application;/**
 * 描述:
 * 包名:com.lvmoney.frame.ops.sentinel.application
 * 版本信息: 版本1.0
 * 日期:2020/4/8
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/8 12:43
 */
@SpringCloudApplication
@ComponentScan(basePackages = {
        "com.lvmoney.**"
})
@EnableFeignClients(basePackages = {"com.lvmoney.**"})
public class DemoSentinelClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoSentinelClientApplication.class, args);
    }
}
