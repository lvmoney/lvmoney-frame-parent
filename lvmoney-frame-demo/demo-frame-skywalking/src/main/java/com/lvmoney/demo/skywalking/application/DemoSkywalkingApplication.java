package com.lvmoney.demo.skywalking.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.lvmoney.**"
})
public class DemoSkywalkingApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoSkywalkingApplication.class, args);
    }
}
