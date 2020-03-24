package com.zhy.frame.authentication.oauth2.center.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 * @Configuration
 * @EnableCaching
 */

public class CaffeineCacheConfiguration {
    /**
     * @describe: 一级缓存管理
     * @param: []
     * @return: org.springframework.cache.CacheManager
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:01
     */
    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        ArrayList<CaffeineCache> caches = new ArrayList<CaffeineCache>();
        cacheManager.setCaches(caches);
        return cacheManager;
    }

}
