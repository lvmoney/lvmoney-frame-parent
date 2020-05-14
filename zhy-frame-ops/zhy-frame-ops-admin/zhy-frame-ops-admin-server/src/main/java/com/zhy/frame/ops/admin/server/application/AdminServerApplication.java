package com.zhy.frame.ops.admin.server.application;/**
 * 描述:
 * 包名:com.zhy.k8s.admin.application
 * 版本信息: 版本1.0
 * 日期:2019/9/20
 * Copyright 四川******科技有限公司
 */


import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/9/20 9:48
 */
@SpringBootApplication(scanBasePackages = {"com.zhy.**"})
@EnableAdminServer
public class AdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminServerApplication.class, args);
    }

}
