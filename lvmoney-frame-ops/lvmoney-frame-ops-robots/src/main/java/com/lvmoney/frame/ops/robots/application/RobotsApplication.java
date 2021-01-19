package com.lvmoney.frame.ops.robots.application;/**
 * 描述:
 * 包名:com.lvmoney.xxljob.application
 * 版本信息: 版本1.0
 * 日期:2019/10/25
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/10/25 17:58
 */
@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"})
public class RobotsApplication {
    public static void main(String[] args) {
        SpringApplication.run(RobotsApplication.class, args);
    }

}
