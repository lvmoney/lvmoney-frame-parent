package com.lvmoney.frame.demo.seata.test.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */

@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"}, exclude = DataSourceAutoConfiguration.class)
@Configuration
@MapperScan("com.lvmoney.frame.demo.seata.test.dao*")
public class SeataTestApplication {
    /**
     * @describe: shiro, jwt, redis整合成功。1、事务注解是否生效校验。2、自定义shiro标签中的url的过滤
     * @param: [args]
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:31
     */
    public static void main(String[] args) {
        SpringApplication.run(SeataTestApplication.class, args);
    }


}