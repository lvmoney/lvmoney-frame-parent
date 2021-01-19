package com.lvmoney.frame.demo.seata.client.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"})
@EnableFeignClients(basePackages = {"com.lvmoney.**"})
public class SeataClientApplication {
    /**
     * @describe: shiro, jwt, redis整合成功。1、事务注解是否生效校验。2、自定义shiro标签中的url的过滤
     * @param: [args]
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:30
     */
    public static void main(String[] args) {
        SpringApplication.run(SeataClientApplication.class, args);
    }

}
