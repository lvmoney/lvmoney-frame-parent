package com.lvmoney.frame.cache.caffeine.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.cache.caffeine.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/5/16
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.cache.caffeine.service.CaffeineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/16 10:37
 */
@Service
public class CaffeineServiceImpl implements CaffeineService {
    @Autowired
    CacheManager caffeineCacheManager;

    @Value("${spring.application.name:lvmoney}")
    private String serverName;
    /**
     * 默认可以前缀
     */
    private final static String DEFAULT_CACHE_PREFIX = "default";


    @Override
    public <T> T get(Object key) {
        if (key == null) {
            return null;
        }

        Cache cache = caffeineCacheManager.getCache(DEFAULT_CACHE_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + serverName);
        if (cache != null) {
            Cache.ValueWrapper wrapper = cache.get(key);
            if (wrapper != null) {
                return (T) wrapper.get();
            }
        }

        return null;
    }

    @Override
    public <T> T get(String cacheName, Object key) {
        if (cacheName == null || key == null) {
            return null;
        }

        Cache cache = caffeineCacheManager.getCache(serverName + BaseConstant.CONNECTOR_UNDERLINE + cacheName);
        if (cache != null) {
            Cache.ValueWrapper wrapper = cache.get(key);
            if (wrapper != null) {
                return (T) wrapper.get();
            }
        }

        return null;
    }

    @Override
    public void save(Object key, Object value) {
        if (key == null || value == null) {
            return;
        }

        Cache cache = caffeineCacheManager.getCache(DEFAULT_CACHE_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + serverName);
        if (cache != null) {
            cache.put(key, value);
        }
    }

    @Override
    public void save(String cacheName, Object key, Object value) {
        if (cacheName == null || key == null || value == null) {
            return;
        }

        Cache cache = caffeineCacheManager.getCache(serverName + BaseConstant.CONNECTOR_UNDERLINE + cacheName);
        if (cache != null) {
            cache.put(key, value);
        }
    }
}
