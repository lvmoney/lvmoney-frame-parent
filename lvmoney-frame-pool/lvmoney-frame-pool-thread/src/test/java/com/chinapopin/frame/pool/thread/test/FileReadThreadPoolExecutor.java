package com.lvmoney.frame.pool.thread.test;/**
 * 描述:
 * 包名:com.lvmoney.frame.pool.thread.test
 * 版本信息: 版本1.0
 * 日期:2020/5/26
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.core.util.FileUtil;
import com.lvmoney.frame.core.util.SplitFileUtil;
import com.lvmoney.frame.core.vo.SplitFileVo;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/26 16:15
 */
public class FileReadThreadPoolExecutor {
    private static Long time;

    private ThreadPoolExecutor pool = null;

    /**
     * 线程池初始化方法
     * <p>
     * corePoolSize 核心线程池大小----1
     * maximumPoolSize 最大线程池大小----3
     * keepAliveTime 线程池中超过corePoolSize数目的空闲线程最大存活时间----30+单位TimeUnit
     * TimeUnit keepAliveTime时间单位----TimeUnit.MINUTES
     * workQueue 阻塞队列----new ArrayBlockingQueue<Runnable>(5)==== 5容量的阻塞队列
     * threadFactory 新建线程工厂----new CustomThreadFactory()====定制的线程工厂
     * rejectedExecutionHandler 当提交任务数超过maxmumPoolSize+workQueue之和时,
     * 即当提交第9个任务时(前面线程都没有执行完,此测试方法中用sleep(100)),
     * 任务会交给RejectedExecutionHandler来处理
     */

    public void init() {

        pool = new ThreadPoolExecutor(4, 8, 30,
                TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(8), new FileReadThreadPoolExecutor.FileThreadFactory(), new FileReadThreadPoolExecutor.CustomRejectedExecutionHandler());
    }

    public void destroy() {
        if (pool != null) {
            pool.shutdownNow();
        }
    }

    public ExecutorService getCustomThreadPoolExecutor() {
        return this.pool;
    }


    private class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            //核心改造点,由blockingqueue的offer改成put阻塞方法
            try {
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class FileThreadFactory implements ThreadFactory {

        private AtomicInteger count = new AtomicInteger(-1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = FileReadThreadPoolExecutor.class.getSimpleName() + count.addAndGet(1);
            t.setName(threadName);
            return t;
        }
    }

    public static void main(String[] args) {
        FileReadThreadPoolExecutor exec = new FileReadThreadPoolExecutor();

        //1. 初始化
        exec.init();
        ExecutorService pool = exec.getCustomThreadPoolExecutor();
        long start = System.currentTimeMillis();
        AtomicLong count = new AtomicLong(0);
        String file = "D:\\data\\temp\\test.txt";
        List<SplitFileVo> list = SplitFileUtil.getSplit(file, 4);
        for (int i = 0; i < list.size(); i++) {
            SplitFileVo vo = list.get(i);
            Runnable r = () -> {
                count.getAndAdd(FileUtil.readFile(file, vo.getBegin(), vo.getEnd()));
            };
            pool.execute(r);
        }

        //2. 销毁----此处不能销毁,因为任务没有提交执行完,如果销毁线程池,任务也就无法执行
        pool.shutdown();
        while (!pool.isTerminated()) {
        }
        long end = System.currentTimeMillis(); //获取结束时间
        System.out.println(count);
        System.out.println(end - start);


    }

}
