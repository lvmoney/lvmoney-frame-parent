package com.lvmoney.frame.profile.application;/**
 * 描述:
 * 包名:com.lvmoney.demo.provider.application
 * 版本信息: 版本1.0
 * 日期:2020/3/5
 * Copyright XXXXXX科技有限公司
 */


import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/5 15:23
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.lvmoney.**"
})

@EnableEncryptableProperties
@MapperScan(basePackages = {"com.lvmoney.frame.profile.dao*"})
public class DemoProfileApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoProfileApplication.class, args);
    }
}
