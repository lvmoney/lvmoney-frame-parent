package com.zhy.frame.pool.thread.controller;/**
 * 描述:
 * 包名:com.zhy.frame.pool.thread.controller
 * 版本信息: 版本1.0
 * 日期:2020/4/1
 * Copyright XXXXXX科技有限公司
 */

import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.*;
import com.zhy.frame.pool.thread.service.impl.ConCallable;
import com.zhy.frame.pool.thread.service.impl.SumTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.*;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/1 20:06
 */
@RestController
public class ConController {
    private final static Logger logger = LoggerFactory.getLogger(Logger.class);

    @RequestMapping(value = "con/test", method = RequestMethod.GET)
    public String test1() {
        try {
            //10万条数据
            List<String> list = new ArrayList<>();
            List<String> list2 = new ArrayList<>();

            for (int i = 1; i <= 100000; i++) {
                list.add("test:" + i);
            }

            //每条线程处理的数据尺寸
            int size = 250;
            int count = list.size() / size;
            if (count * size != list.size()) {
                count++;
            }
            int countNum = 0;
            final CountDownLatch countDownLatch = new CountDownLatch(count);
//            ExecutorService executorService = Executors.newFixedThreadPool(4);
            ExecutorService executorService = new ThreadPoolExecutor(8, 8,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>());

            ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);
            while (countNum < list.size()) {
                countNum += size;
                ConCallable callable = new ConCallable();
                //截取list的数据，分给不同线程处理
                callable.setList(ImmutableList.copyOf(list.subList(countNum - size, countNum < list.size() ? countNum : list.size())));
                ListenableFuture listenableFuture = listeningExecutorService.submit(callable);

                Futures.addCallback(listenableFuture, new FutureCallback<List<String>>() {
                    @Override
                    public void onSuccess(List<String> list1) {
                        countDownLatch.countDown();
                        list2.addAll(list1);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        countDownLatch.countDown();
                        logger.info("处理出错：", throwable);

                    }
                }, executorService);
            }
            countDownLatch.await(30, TimeUnit.MINUTES);
            logger.info("符合条件的返回数据个数为：" + list2.size());
            logger.info("回调函数：" + list2.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "正在处理......";

    }

    @RequestMapping(value = "con/test2", method = RequestMethod.GET)
    public int test2() {
        SumTask sumTask = new SumTask(1, 999999);
        sumTask.fork();
        return sumTask.join();
    }
}