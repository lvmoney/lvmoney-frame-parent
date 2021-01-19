package com.lvmoney.demo.istio.application;/**
 * 描述:
 * 包名:com.lvmoney.demo.istio.application
 * 版本信息: 版本1.0
 * 日期:2020/12/3
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/12/3 15:55
 */
@SpringCloudApplication
@ComponentScan(basePackages = {
        "com.lvmoney.**"
})
public class DemoIstioApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoIstioApplication.class, args);
    }
}
