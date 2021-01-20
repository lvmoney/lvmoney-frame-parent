/**
 * 描述:源数据程序入口
 * 包名:com.lvmoney.pay.gateway.application
 * 版本信息: 版本1.0
 * 日期:2018年9月30日  上午8:51:33
 * Copyright 成都三合力通科技有限公司
 */

package com.lvmoney.frame.sync.canal.common.application;

import com.lvmoney.frame.sync.canal.common.annotation.EnableCanalClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @describe：统一网关，springboot主方法
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"})
@EnableCanalClient
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}