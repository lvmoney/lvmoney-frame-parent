package com.zhy.demo.syn.controller;/**
 * 描述:
 * 包名:com.zhy.demo.syn.function
 * 版本信息: 版本1.0
 * 日期:2020/3/27
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.demo.syn.constant.SynConstant;
import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.base.core.util.JsonUtil;
import com.zhy.frame.cache.lock.constant.LockConstant;
import com.zhy.frame.cache.lock.service.DistributedLockerService;
import com.zhy.frame.cache.redis.service.BaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/27 9:56
 */
@RestController
public class DemoSynController {
    @Autowired
    BaseRedisService baseRedisService;
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;
    private static final String SYN_KEY = "synKey";

    @Autowired
    DistributedLockerService distributedLockerService;

    @GetMapping(value = "frame/syn/read")
    public ApiResult<String> read() {
        int key = ThreadLocalRandom.current().nextInt(10000);

        String json = JsonUtil.t2JsonString(baseRedisService.getByMapKey(SYN_KEY, String.valueOf(key)));
        return ApiResult.success(json);
    }

    @GetMapping(value = "frame/syn/write")
    public ApiResult<String> write() {
        distributedLockerService.lock(LockConstant.PROD_SECTION_LOCK_KEY, TimeUnit.SECONDS, SynConstant.LOCK);

        Map map1 = new HashMap() {{
            put("A", "aa");
            put("B", "bb");
            put("C", "CCCC");
        }};
        for (int i = 0; i < 10000; i++) {
            int key = ThreadLocalRandom.current().nextInt(10000);
            map1.put(String.valueOf(key), key + "AAAAAAA");
        }
        baseRedisService.addMap(SYN_KEY, map1, null);
        int key = ThreadLocalRandom.current().nextInt(10000);

        String json = JsonUtil.t2JsonString(baseRedisService.getByMapKey(SYN_KEY, String.valueOf(key)));

        distributedLockerService.unlock(LockConstant.PROD_SECTION_LOCK_KEY);
        return ApiResult.success("CCCC=" + json);
    }

    @GetMapping(value = "frame/syn/init")
    public ApiResult<String> init() {
        distributedLockerService.lock(LockConstant.PROD_SECTION_LOCK_KEY, TimeUnit.SECONDS, LockConstant.LOCK_TIME);
        Map map = new HashMap() {{
            put("A", "a");
            put("B", "b");
            put("C", "c");
        }};
        for (int i = 0; i < 10000; i++) {
            int key = ThreadLocalRandom.current().nextInt(10000);
            map.put(String.valueOf(key), String.valueOf(key));
        }
        baseRedisService.addMap(SYN_KEY, map, null);
        int key = ThreadLocalRandom.current().nextInt(10000);
        String json = JsonUtil.t2JsonString(baseRedisService.getByMapKey(SYN_KEY, String.valueOf(key)));
        distributedLockerService.unlock(LockConstant.PROD_SECTION_LOCK_KEY);
        return ApiResult.success("c=" + json);
    }


}
