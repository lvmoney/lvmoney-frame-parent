package com.lvmoney.frame.ai.seetaface.base.application;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.lvmoney.**"})
public class SeetafaceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeetafaceApplication.class, args);
    }

}
