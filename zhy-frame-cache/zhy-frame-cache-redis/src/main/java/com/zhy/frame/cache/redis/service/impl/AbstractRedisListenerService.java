package com.zhy.frame.cache.redis.service.impl;/**
 * 描述:
 * 包名:com.zhy.frame.cache.redis.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/5/18
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.cache.redis.service.RedisListenerService;
import io.lettuce.core.protocol.CommandType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/18 10:27
 */
public abstract class AbstractRedisListenerService implements RedisListenerService {
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void delete(Message message) {
        System.out.println("delete");
    }

    @Override
    public void set(Message message) {
        System.out.println("set");
    }

    @Override
    public void rename(Message message) {
        System.out.println("rename");
    }

    @Override
    public void expire(Message message) {
        System.out.println("expire");
    }

    @Override
    public void expired(Message message) {
        System.out.println("expired");
    }

    @Override
    public void other(Message message) {
        System.out.println("other");
    }

    @Override
    public Object getHotspot() {
        return null;
    }
}
