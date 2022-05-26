package com.lvmoney.demo.uniformity.application;/**
 * 描述:
 * 包名:com.lvmoney.demo.uniformity.application
 * 版本信息: 版本1.0
 * 日期:2021/12/30
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司 
 * @version:v1.0
 * 2021/12/30 13:18  
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.lvmoney.**"
})
public class DemoUniformityApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoUniformityApplication.class, args);
    }
}
