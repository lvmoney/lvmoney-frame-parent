package com.lvmoney.frame.pool.thread.application;/**
 * 描述:
 * 包名:com.lvmoney.frame.base.asyn.application
 * 版本信息: 版本1.0
 * 日期:2020/4/1
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/1 10:41
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.lvmoney.**"
})
public class AsynApplication {
    public static void main(String[] args) {
        SpringApplication.run(AsynApplication.class, args);
    }
}
