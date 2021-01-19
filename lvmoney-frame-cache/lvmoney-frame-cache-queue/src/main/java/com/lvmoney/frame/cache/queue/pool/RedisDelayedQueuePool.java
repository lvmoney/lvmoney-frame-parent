package com.lvmoney.frame.cache.queue.pool;/**
 * 描述:
 * 包名:com.lvmoney.frame.pool.thread.test
 * 版本信息: 版本1.0
 * 日期:2020/5/6
 * Copyright XXXXXX科技有限公司
 *
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/6 9:33
 */

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/6 9:33
 */

/**
 * @describe：定制属于自己的非阻塞线程池
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/6 9:34
 */
public class RedisDelayedQueuePool {
    private ThreadPoolExecutor pool = null;

    /**
     * 初始化
     *
     * @param corePoolSize:
     * @param maxPoolSize:
     * @param keepAliveTime:
     * @param capacity:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/20 17:16
     */
    public void init(int corePoolSize, int maxPoolSize, Long keepAliveTime, int capacity) {
        pool = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime,
                TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(capacity), new RedisDelayedQueueFactory(), new RedisDelayedQueueFactoryHandler());
    }


    public ExecutorService getRedisDelayedQueuePool() {
        return this.pool;
    }


    private class RedisDelayedQueueFactoryHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class RedisDelayedQueueFactory implements ThreadFactory {
        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = RedisDelayedQueuePool.class.getSimpleName() + count.addAndGet(1);
            t.setName(threadName);
            return t;
        }
    }
}