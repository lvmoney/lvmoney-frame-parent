package com.lvmoney.frame.demo.seata.provider.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;


/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */

@SpringBootApplication(scanBasePackages = {"com.lvmoney.**"})
@MapperScan("com.lvmoney.frame.demo.seata.provider.dao*")
public class SeataProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeataProviderApplication.class, args);
    }

}
