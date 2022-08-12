package com.lvmoney.demo.webservice.application;/**
 * 描述:
 * 包名:com.chinapopin.demo.webservice.application
 * 版本信息: 版本1.0
 * 日期:2021/1/21
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司 
 * @version:v1.0
 * 2021/1/21 10:25  
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.lvmoney.**"
})
public class DemoWebserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoWebserviceApplication.class, args);
    }
}
