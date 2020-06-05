package com.zhy.frame.sync.netty.socket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.zhy.**"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
