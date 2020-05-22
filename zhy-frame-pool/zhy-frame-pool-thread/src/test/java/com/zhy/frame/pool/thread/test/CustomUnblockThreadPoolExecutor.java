package com.zhy.frame.pool.thread.test;/**
 * 描述:
 * 包名:com.zhy.frame.pool.thread.test
 * 版本信息: 版本1.0
 * 日期:2020/5/6
 * Copyright XXXXXX科技有限公司
 */

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/6 9:34
 */
public class CustomUnblockThreadPoolExecutor {

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

        pool = new ThreadPoolExecutor(1, 3, 30,
                TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(5), new CustomThreadFactory(), new CustomRejectedExecutionHandler());
    }

    public void destory() {
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

    private class CustomThreadFactory implements ThreadFactory {

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = CustomUnblockThreadPoolExecutor.class.getSimpleName() + count.addAndGet(1);

            System.out.println("lvmoney" + threadName);
            t.setName(threadName);
            return t;
        }
    }

    public static void main(String[] args) {
        CustomUnblockThreadPoolExecutor exec = new CustomUnblockThreadPoolExecutor();

        //1. 初始化
        exec.init();

        ExecutorService pool = exec.getCustomThreadPoolExecutor();

        for (int i = 1; i < 100; i++) {
            System.out.println("提交第" + i + "个任务");
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(">>>task is running========");
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        //2. 销毁----此处不能销毁,因为任务没有提交执行完,如果销毁线程池,任务也就无法执行
        //exec.destory();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解释：当提交任务被拒绝时，进入拒绝机制，我们实现拒绝方法，把任务重新用阻塞提交方法put提交，实现阻塞提交任务功能，防止队列过大，OOM，提交被拒绝方法在下面
     *
     * public void execute(Runnable command) {
     if (command == null)
     throw new NullPointerException();
     int c = ctl.get();
     if (workerCountOf(c) < corePoolSize) {
     if (addWorker(command, true))
     return;
     c = ctl.get();
     }
     if (isRunning(c) && workQueue.offer(command)) {
     int recheck = ctl.get();
     if (! isRunning(recheck) && remove(command))
     reject(command);
     else if (workerCountOf(recheck) == 0)
     addWorker(null, false);
     }
     else if (!addWorker(command, false))
     // 进入拒绝机制， 我们把runnable任务拿出来，重新用阻塞操作put，来实现提交阻塞功能
     reject(command);
     }
     */
}
