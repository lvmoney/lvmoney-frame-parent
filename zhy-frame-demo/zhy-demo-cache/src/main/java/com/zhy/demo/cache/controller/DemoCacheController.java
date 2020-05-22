package com.zhy.demo.cache.controller;/**
 * 描述:
 * 包名:com.zhy.demo.cache.function
 * 版本信息: 版本1.0
 * 日期:2020/5/17
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.cache.lock.annotion.HotRequest;
import com.zhy.frame.cache.queue.service.impl.RedisDelayedQueueListenerImpl;
import com.zhy.frame.cache.queue.vo.MessageVo;
import com.zhy.frame.cache.queue.config.RedisDelayedQueue;
import com.zhy.frame.cache.redis.service.BaseRedisService;
import com.zhy.frame.cache.redis.service.RedisListenerService;
import com.zhy.frame.cache.lock.vo.HotRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/17 23:08
 */
@RestController
public class DemoCacheController {
    @Autowired
    BaseRedisService baseRedisService;
    @Autowired
    RedisListenerService redisListenerService;
    @Autowired
    RedisDelayedQueue redisDelayedQueue;

    @PostMapping(value = "frame/cache/save")
    public ApiResult<String> test() {
        baseRedisService.setString("cacheDemo", "test", 1000000L);
        return ApiResult.success("cache save");
    }

    @HotRequest(required = true, threshold = 5, section = 1800)
    @PostMapping(value = "frame/cache/get")
    public ApiResult<String> get(HotRequestVo hotRequestVo) {
//        System.out.println("function");
        return ApiResult.success(baseRedisService.getByKey("cacheDemo"));
    }

    @PostMapping(value = "frame/cache/queue")
    public ApiResult<String> queue() {
        MessageVo taskBody = new MessageVo();
        taskBody.setType("default");
        taskBody.setData("测试DTO实体类的姓名,3秒之后执行");
        //添加队列3秒之后执行
        redisDelayedQueue.addQueue(taskBody, 10, TimeUnit.SECONDS, RedisDelayedQueueListenerImpl.class.getName());

        taskBody.setType("default");
        taskBody.setData("测试DTO实体类的姓名,10秒之后执行");
        //添加队列10秒之后执行
        redisDelayedQueue.addQueue(taskBody, 20, TimeUnit.SECONDS, RedisDelayedQueueListenerImpl.class.getName());

        taskBody.setType("default");
        taskBody.setData("测试DTO实体类的姓名,20秒之后执行");
        //添加队列20秒之后执行
        redisDelayedQueue.addQueue(taskBody, 30, TimeUnit.SECONDS, RedisDelayedQueueListenerImpl.class.getName());
        return ApiResult.success("queue");
    }
}
