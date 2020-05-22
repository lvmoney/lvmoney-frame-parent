package com.zhy.frame.cache.queue.config;/**
 * 描述:
 * 包名:com.zhy.frame.cache.queue.vo
 * 版本信息: 版本1.0
 * 日期:2020/5/20
 * Copyright XXXXXX科技有限公司
 */


import org.redisson.api.RBlockingQueue;
import org.redisson.api.RDelayedQueue;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/20 15:10
 */
@Component
public class RedisDelayedQueue {

    @Autowired
    RedissonClient redissonClient;

    /**
     * 添加队列
     *
     * @param t:         数据
     * @param delay:     时间数量
     * @param timeUnit:  时间单位
     * @param queueName: 队列名称
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/20 15:34
     */
    public <T> void addQueue(T t, long delay, TimeUnit timeUnit, String queueName) {
        RBlockingQueue<T> blockingFairQueue = redissonClient.getBlockingQueue(queueName);
        RDelayedQueue<T> delayedQueue = redissonClient.getDelayedQueue(blockingFairQueue);
        delayedQueue.offer(t, delay, timeUnit);
        delayedQueue.destroy();
    }
}
