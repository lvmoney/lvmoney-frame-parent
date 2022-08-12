package com.lvmoney.demo.transaction.customer.application;/**
 * 描述:
 * 包名:com.lvmoney.demo.file.application
 * 版本信息: 版本1.0
 * 日期:2020/8/19
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/8/19 13:35
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.lvmoney.**"
})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class TransactionCustomerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionCustomerApplication.class, args);
    }
}
