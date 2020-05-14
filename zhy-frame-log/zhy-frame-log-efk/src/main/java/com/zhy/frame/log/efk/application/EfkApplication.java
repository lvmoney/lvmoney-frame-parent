package com.zhy.frame.log.efk.application;/**
 * 描述:
 * 包名:com.zhy.frame.log.efk.application
 * 版本信息: 版本1.0
 * 日期:2020/5/6
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/6 10:15
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.zhy.**"
})
public class EfkApplication {
    public static void main(String[] args) {
        SpringApplication.run(EfkApplication.class, args);
    }
}
