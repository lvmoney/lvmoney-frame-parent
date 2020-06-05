package com.zhy.demo.rabbitmq.application;/**
 * 描述:
 * 包名:com.zhy.demo.rabbitmq.application
 * 版本信息: 版本1.0
 * 日期:2020/5/29
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/29 10:35
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.zhy.**"
})
public class RabbitmqDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitmqDemoApplication.class, args);
    }
}
