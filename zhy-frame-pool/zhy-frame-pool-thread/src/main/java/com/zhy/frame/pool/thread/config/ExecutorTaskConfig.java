package com.zhy.frame.pool.thread.config;/**
 * 描述:
 * 包名:com.zhy.demo.syn.handler
 * 版本信息: 版本1.0
 * 日期:2020/4/1
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/1 9:21
 */
@Configuration
@ComponentScan("com.zhy")
@EnableAsync
public class ExecutorTaskConfig implements AsyncConfigurer {

    /**
     * 最大线程数
     */
    @Value("${frame.thread.pool.maxPoolSize:120}")
    private int maxPoolSize;
    /**
     * 当前线程数
     */
    @Value("${frame.thread.pool.corePoolSize:2}")
    private int corePoolSize;
    /**
     * 线程池所使用的缓冲队列
     */
    @Value("${frame.thread.pool.queueCapacity:1}")
    private int queueCapacity;
    /**
     * 等待任务在关机时完成--表明等待所有线程执行完,默认15s
     */
    @Value("${frame.thread.pool.awaitTerminationSeconds:900}")
    private int awaitTerminationSeconds;
    /**
     * 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
     */
    @Value("${frame.thread.pool.waitForTasksToCompleteOnShutdown:true}")
    private boolean waitForTasksToCompleteOnShutdown;
    /**
     * 线程名称前缀
     */
    @Value("${frame.thread.pool.threadNamePrefix:Async-}")
    private String threadNamePrefix;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
        //当前线程数
        threadPool.setCorePoolSize(corePoolSize);
        // 最大线程数
        threadPool.setMaxPoolSize(maxPoolSize);
        //线程池所使用的缓冲队列
        threadPool.setQueueCapacity(queueCapacity);
        //等待任务在关机时完成--表明等待所有线程执行完
        threadPool.setWaitForTasksToCompleteOnShutdown(waitForTasksToCompleteOnShutdown);
        // 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
        threadPool.setAwaitTerminationSeconds(awaitTerminationSeconds);
        //  线程名称前缀
        threadPool.setThreadNamePrefix(threadNamePrefix);
        // 初始化
        threadPool.initialize();
        return threadPool;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new FrameAsyncExceptionHandler();
    }
}
