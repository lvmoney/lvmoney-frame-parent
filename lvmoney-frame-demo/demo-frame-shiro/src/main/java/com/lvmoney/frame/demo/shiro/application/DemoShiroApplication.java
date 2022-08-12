package com.lvmoney.frame.demo.shiro.application;/**
 * 描述:
 * 包名:com.lvmoney.demo.shiro.application
 * 版本信息: 版本1.0
 * 日期:2020/3/4
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/4 19:48
 */
@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"})

public class DemoShiroApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoShiroApplication.class, args);
    }
}
