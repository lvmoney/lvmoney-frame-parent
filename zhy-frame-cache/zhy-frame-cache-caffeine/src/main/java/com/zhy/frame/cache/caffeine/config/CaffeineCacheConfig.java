package com.zhy.frame.cache.caffeine.config;/**
 * 描述:
 * 包名:com.zhy.frame.cache.caffeine.config
 * 版本信息: 版本1.0
 * 日期:2020/5/16
 * Copyright XXXXXX科技有限公司
 */


import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/16 10:38
 */
@EnableCaching
@Configuration
public class CaffeineCacheConfig {
    @Value("${caffeine.maxsize:10000}")
    private int caffeineMaxSize;
    @Value("${caffeine.expire.time:60}")
    private int caffeineExpireTime;

    @Primary
    @Bean("caffeineCacheManager")
    public CacheManager caffeineCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder().recordStats()
                .expireAfterWrite(caffeineMaxSize, TimeUnit.SECONDS)
                .maximumSize(caffeineMaxSize));
        return cacheManager;
    }

}
