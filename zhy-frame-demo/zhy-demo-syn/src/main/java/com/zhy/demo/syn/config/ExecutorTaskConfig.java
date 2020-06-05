package com.zhy.demo.syn.config;/**
 * 描述:
 * 包名:com.zhy.demo.syn.handler
 * 版本信息: 版本1.0
 * 日期:2020/4/1
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/1 9:21
 */
@Configuration
@ComponentScan("com.zhy.demo.syn.config")
@EnableAsync
public class ExecutorTaskConfig implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(200);
        executor.setQueueCapacity(25);
        executor.initialize();
        return executor;
    }

    /**
     * The {@link AsyncUncaughtExceptionHandler} instance to be used
     * when an exception is thrown during an asynchronous method execution
     * with {@code void} return type.
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}
