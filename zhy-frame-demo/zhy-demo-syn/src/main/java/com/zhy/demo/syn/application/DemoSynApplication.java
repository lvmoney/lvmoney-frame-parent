package com.zhy.demo.syn.application;/**
 * 描述:
 * 包名:com.zhy.demo.syn.application
 * 版本信息: 版本1.0
 * 日期:2020/3/27
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/27 9:54
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.zhy.**"
})
public class DemoSynApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoSynApplication.class, args);
    }
}
