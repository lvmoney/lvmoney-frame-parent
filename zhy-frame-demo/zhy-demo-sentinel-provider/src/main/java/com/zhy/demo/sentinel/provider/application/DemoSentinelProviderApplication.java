package com.zhy.demo.sentinel.provider.application;/**
 * 描述:
 * 包名:com.zhy.demo.provider.application
 * 版本信息: 版本1.0
 * 日期:2020/3/5
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/5 15:23
 */
@SpringCloudApplication
@ComponentScan(basePackages = {
        "com.zhy.**"
})
public class DemoSentinelProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoSentinelProviderApplication.class, args);
    }
}
