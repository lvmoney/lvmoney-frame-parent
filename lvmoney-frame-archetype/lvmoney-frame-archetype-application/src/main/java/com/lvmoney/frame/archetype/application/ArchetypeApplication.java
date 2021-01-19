package com.lvmoney.frame.archetype.application;/**
 * 描述:
 * 包名:com.lvmoney.frame.archetype.application
 * 版本信息: 版本1.0
 * 日期:2020/6/23
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/23 13:36
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.lvmoney.**"
})
public class ArchetypeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArchetypeApplication.class, args);
    }
}
