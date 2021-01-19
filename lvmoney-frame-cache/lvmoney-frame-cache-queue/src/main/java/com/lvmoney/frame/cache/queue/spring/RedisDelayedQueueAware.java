package com.lvmoney.frame.cache.queue.spring;/**
 * 描述:
 * 包名:com.lvmoney.frame.cache.queue.init
 * 版本信息: 版本1.0
 * 日期:2020/5/20
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.base.core.util.SupportUtil;
import com.lvmoney.frame.cache.common.exception.CacheException;
import com.lvmoney.frame.cache.queue.pool.RedisDelayedQueuePool;
import com.lvmoney.frame.cache.queue.service.RedisDelayedQueueListener;
import com.lvmoney.frame.cache.queue.vo.MessageVo;
import org.redisson.api.RBlockingQueue;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/20 15:07
 */
@Component
public class RedisDelayedQueueAware implements ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisDelayedQueueAware.class);

    @Autowired
    RedissonClient redissonClient;
    @Value("${redisDelayedQueue.corePoolSize:8}")
    int corePoolSize;
    @Value("${redisDelayedQueue.maxPoolSize:17}")
    int maxPoolSize;
    @Value("${redisDelayedQueue.keepAliveTime:3}")
    Long keepAliveTime;
    @Value("${redisDelayedQueue.capacity:10}")
    int capacity;
    @Value("${frame.redis.delayedQueue.support:false}")
    boolean queueSupport;

    /**
     * 获取应用上下文并获取相应的接口实现类
     *
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (!SupportUtil.support(queueSupport)) {
            throw new BusinessException(CacheException.Proxy.REDIS_DELAYED_QUEUE_SUPPORT_ERROR);
        } else if (BaseConstant.SUPPORT_TRUE_BOOL == queueSupport) {
            Map<String, RedisDelayedQueueListener> map = applicationContext.getBeansOfType(RedisDelayedQueueListener.class);
            for (Map.Entry<String, RedisDelayedQueueListener> taskEventListenerEntry : map.entrySet()) {
                String listenerName = taskEventListenerEntry.getValue().getClass().getName();
                start(listenerName, taskEventListenerEntry.getValue());
            }
        }

    }

    /**
     * 启动线程获取队列
     *
     * @param queueName:
     * @param redisDelayedQueueListener: 任务回调监听
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/20 15:40
     */
    private <T> void start(String queueName, RedisDelayedQueueListener redisDelayedQueueListener) {
        RBlockingQueue<T> blockingFairQueue = redissonClient.getBlockingQueue(queueName);
        RedisDelayedQueuePool exec = new RedisDelayedQueuePool();
        exec.init(corePoolSize, maxPoolSize, keepAliveTime, capacity);
        ExecutorService pool = exec.getRedisDelayedQueuePool();
        pool.execute(() -> {
            while (true) {
                try {
                    T t = blockingFairQueue.take();
                    redisDelayedQueueListener.receive((MessageVo) t);
                } catch (Exception e) {
                    LOGGER.info("监听队列线程错误{}", e);
                }
            }
        });
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

}
