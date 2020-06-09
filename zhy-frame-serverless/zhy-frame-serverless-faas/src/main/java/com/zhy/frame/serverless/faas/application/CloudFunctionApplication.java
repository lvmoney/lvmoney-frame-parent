package com.zhy.frame.serverless.faas.application;/**
 * 描述:
 * 包名:com.zhy.frame.serverless.faas.application
 * 版本信息: 版本1.0
 * 日期:2020/5/22
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.core.util.SpringBeanUtil;
import com.zhy.frame.serverless.faas.vo.Foo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/22 10:31
 */
@SpringBootApplication
@ComponentScan(basePackages = {
        "com.zhy.**"
})
public class CloudFunctionApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudFunctionApplication.class, args);
    }

    @Bean
    public Function<String, String> reverseString() {
        return value -> new StringBuilder(value).reverse().toString();
    }

    @Bean
    public Function<String, String> uppercase() {
        return value -> value.toUpperCase();
    }

    @Bean
    public Supplier<Flux<Foo>> words() {
        return () -> Flux.fromArray(new Foo[]{new Foo("foo"), new Foo("bar")}).log();
    }

    @Bean
    public Function<Foo, List> word() {
        return ss -> {
            return Arrays.asList(ss.getValue().split(","));
        };
    }

    @Bean
    public Function<Flux<String>, Flux<String>> lowerCase() {
        return flux -> flux.map(value -> value.toLowerCase());
    }
}
