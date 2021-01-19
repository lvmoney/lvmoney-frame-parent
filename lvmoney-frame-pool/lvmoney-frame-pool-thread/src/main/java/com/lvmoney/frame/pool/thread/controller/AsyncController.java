package com.lvmoney.frame.pool.thread.controller;/**
 * 描述:
 * 包名:com.lvmoney.frame.pool.thread.function
 * 版本信息: 版本1.0
 * 日期:2020/4/1
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.pool.thread.service.impl.AsyncTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/1 18:41
 */
@RestController
@EnableAsync
public class AsyncController {
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private AsyncTaskService asyncTaskService;

    /**
     * 阻塞请求
     *
     * @return
     */
    @GetMapping("blockingRequest")
    public String blockingRequest() {

        System.out.println(dtf.format(LocalDateTime.now()) + "--->主线程开始");

        String uuid = asyncTaskService.uuid();

        System.out.println(dtf.format(LocalDateTime.now()) + "--->主线程结束");

        return uuid;

    }

    /**
     * 使用Callable
     *
     * @return
     */
    @GetMapping("callable")
    public Callable<String> callable() {

        System.out.println(dtf.format(LocalDateTime.now()) + "--->主线程开始");

        Callable<String> callable = () -> {
            String uuid = asyncTaskService.uuid();
            System.out.println(dtf.format(LocalDateTime.now()) + "--->子任务线程(" + Thread.currentThread().getName() + ")");
            return uuid;
        };

        System.out.println(dtf.format(LocalDateTime.now()) + "--->主线程结束");

        return callable;

    }


    /**
     * 使用CompletableFuture
     * <p>
     * 使用该方式时，切记自己创建执行器，不要使用内置的 ForkJoinPool线程池，会有性能问题
     *
     * @return
     */
    @GetMapping("completableFuture")
    public CompletableFuture<String> completableFuture() {

        ExecutorService executor = Executors.newCachedThreadPool();

        System.out.println(dtf.format(LocalDateTime.now()) + "--->主线程开始");

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(asyncTaskService::uuid, executor);

        System.out.println(dtf.format(LocalDateTime.now()) + "--->主线程结束");

        return completableFuture;

    }


    /**
     * 使用ListenableFuture
     * <p>
     * 使用该方式时，切记自己创建执行器，不要使用内置的 ForkJoinPool线程池，会有性能问题
     *
     * @return
     */
    @GetMapping("listenableFuture")
    public ListenableFuture<String> listenableFuture() {

        System.out.println(dtf.format(LocalDateTime.now()) + "--->主线程开始");

        ListenableFutureTask<String> listenableFuture = new ListenableFutureTask<>(() -> asyncTaskService.uuid());

        Executors.newCachedThreadPool().execute(listenableFuture);

        System.out.println(dtf.format(LocalDateTime.now()) + "--->主线程结束");

        return listenableFuture;

    }


    /**
     * 使用WebAsyncTask
     *
     * @return
     */
    @GetMapping("asynctask")
    public WebAsyncTask asyncTask() {

        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();

        System.out.println(dtf.format(LocalDateTime.now()) + "--->主线程开始");

        WebAsyncTask<String> task = new WebAsyncTask(10000L, executor, () -> asyncTaskService.uuid());

        task.onCompletion(() -> {
            System.out.println(dtf.format(LocalDateTime.now()) + "--->调用完成");
        });

        task.onTimeout(() -> {
            System.out.println("onTimeout");
            return "onTimeout";
        });


        System.out.println(dtf.format(LocalDateTime.now()) + "--->主线程结束");

        return task;
    }

    /**
     * 使用DeferredResult
     *
     * @return
     */
    @GetMapping("deferredResult")
    public DeferredResult<String> deferredResult() {

        System.out.println(dtf.format(LocalDateTime.now()) + "--->主线程(" + Thread.currentThread().getName() + ")开始");

        DeferredResult<String> deferredResult = new DeferredResult<>();

        CompletableFuture.supplyAsync(() -> {

            String uuid = asyncTaskService.uuid();

            // int abc = 2/0;

            return uuid;


        }, Executors.newFixedThreadPool(5)).whenCompleteAsync((result, throwable) -> {
            if (throwable != null) {
                deferredResult.setErrorResult(throwable);
            } else {
                deferredResult.setResult(result);
            }
        });


        // 异步请求超时时调用
        deferredResult.onTimeout(() -> {
            System.out.println(dtf.format(LocalDateTime.now()) + "--->onTimeout");
        });

        // 异步请求完成后调用
        deferredResult.onCompletion(() -> {
            System.out.println(dtf.format(LocalDateTime.now()) + "--->onCompletion");
        });

        System.out.println(dtf.format(LocalDateTime.now()) + "--->主线程(" + Thread.currentThread().getName() + ")结束");

        return deferredResult;
    }

}
