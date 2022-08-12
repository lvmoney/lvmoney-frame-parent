package com.lvmoney.frame.pool.disruptor.application;/**
 * 描述:
 * 包名:com.lvmoney.frame.pool.disruptor.application
 * 版本信息: 版本1.0
 * 日期:2020/4/28
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/28 11:58
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.lvmoney.**"
})
public class DisruptorApplication {
    public static void main(String[] args) {
        SpringApplication.run(DisruptorApplication.class, args);
    }
}
