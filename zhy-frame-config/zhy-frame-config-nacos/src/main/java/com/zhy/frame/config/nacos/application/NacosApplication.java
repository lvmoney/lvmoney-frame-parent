package com.zhy.frame.config.nacos.application;/**
 * 描述:
 * 包名:com.zhy.k8s.nacos.application
 * 版本信息: 版本1.0
 * 日期:2019/11/14
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/14 9:17
 */
@SpringBootApplication(scanBasePackages = {"com.zhy.**"})
@RestController
public class NacosApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosApplication.class, args);
    }

    @NacosValue(value = "${server.port:11212}", autoRefreshed = false)
    private String testProperties;

    @GetMapping("/test")
    public String test() {
        return testProperties;
    }
}
