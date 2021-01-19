package com.lvmoney.frame.demo.simple.application;/**
 * 描述:
 * 包名:com.lvmoney.frame.demo.simple.application
 * 版本信息: 版本1.0
 * 日期:2020/6/28
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/28 14:55
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.lvmoney.**"
})

public class SimpleDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleDemoApplication.class, args);
    }
}
