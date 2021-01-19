package com.lvmoney.frame.cache.lock.service;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/3/9
 * Copyright 四川******科技有限公司
 */

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public interface DistributedLockerService {
    /**
     * 锁住key
     *
     * @param lockKey: 分布式锁key
     * @throws
     * @return: org.redisson.api.RLock
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:19
     */
    RLock lock(String lockKey);

    /**
     * 锁住key
     *
     * @param lockKey: 分布式锁key
     * @param timeout: 锁着时长
     * @throws
     * @return: org.redisson.api.RLock
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:24
     */
    RLock lock(String lockKey, int timeout);

    /**
     * 锁住key，时间到了放开
     *
     * @param lockKey: 分布式锁key
     * @param unit:    单位
     * @param timeout: 锁住时长
     * @throws
     * @return: org.redisson.api.RLock
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:20
     */
    RLock lock(String lockKey, TimeUnit unit, int timeout);

    /**
     * 尝试锁住key
     *
     * @param lockKey:   分布式锁key
     * @param unit:      单位
     * @param waitTime:  等待时长
     * @param leaseTime: 租用时长
     * @throws
     * @return: boolean
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:20
     */
    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime);

    /**
     * 释放锁
     *
     * @param lockKey: 锁key
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:22
     */
    void unlock(String lockKey);

    /**
     * 释放锁
     *
     * @param lock: 锁对象
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:22
     */
    void unlock(RLock lock);

    /**
     * 设置redisson对象
     *
     * @param redissonClient: redisson对象
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:22
     */
    void setRedissonClient(RedissonClient redissonClient);
}
