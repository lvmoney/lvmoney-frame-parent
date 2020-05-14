/**
 * 描述:源数据程序入口
 * 包名:com.zhy.pay.gateway.application
 * 版本信息: 版本1.0
 * 日期:2018年9月30日  上午8:51:33
 * Copyright xxxx科技有限公司
 */

package com.zhy.frame.demo.mongdb.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @describe：统一网关，springboot主方法
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 * @SpringBootApplication(scanBasePackages = {"com.zhy.**"})
 * @Configuration
 */

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.zhy.**"
})
public class MongoDemoApplication {
    /**
     * @describe: TODO1、断点续传还没有做，2、检索的封装还比较粗陋，增删改已经完成
     * @param: [args]
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 9:58
     */
    public static void main(String[] args) {
        SpringApplication.run(MongoDemoApplication.class, args);
    }
}
