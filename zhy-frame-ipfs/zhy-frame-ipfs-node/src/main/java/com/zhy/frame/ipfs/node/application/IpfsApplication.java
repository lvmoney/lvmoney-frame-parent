package com.zhy.frame.ipfs.node.application;/**
 * 描述:
 * 包名:com.zhy.frame.ipfs.node.application
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
 * @version:v1.0 2020/5/6 11:50
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.zhy.**"
})
public class IpfsApplication {
    public static void main(String[] args) {
        SpringApplication.run(IpfsApplication.class, args);
    }
}
