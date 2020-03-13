package com.zhy.frame.cache.common.factory;/**
 * 描述:
 * 包名:com.zhy.frame.cache.common.factory
 * 版本信息: 版本1.0
 * 日期:2019/11/27
 * Copyright 四川******科技有限公司
 */


import com.zhy.frame.cache.common.annation.CacheService;
import com.zhy.frame.cache.common.service.CacheCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/11/27 10:42
 */
@Component
public class CacheCommonServiceFactoryImpl implements CacheCommonService {
    @Autowired
    private Map<String, CacheCommonService> strategyMap = new ConcurrentHashMap();
    @Value("${frame.cache.ware:redisCache}")
    private String cacheWare;


    public void setCacheWare(String cacheWare) {
        this.cacheWare = cacheWare;
    }

    public Map<String, CacheCommonService> getStrategyMap() {
        return strategyMap;
    }

    /**
     * @describe:策略注入
     * @author: lvmoney /四川******科技有限公司
     * 2018年11月8日下午3:08:36
     */
    @Autowired
    public <T> CacheCommonServiceFactoryImpl(Map<String, CacheCommonService> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach((k, v) -> this.strategyMap.put(k, v));

    }

    private String getCacheCommonServiceBeanName(String mqType) {
        Map<String, CacheCommonService> map = this.getStrategyMap();
        for (Map.Entry<String, CacheCommonService> entry : map.entrySet()) {
            Class<? extends CacheCommonService> clazz = entry.getValue().getClass();
            if (clazz.isAnnotationPresent(CacheService.class)) {
                CacheService dynamicService = clazz.getAnnotation(CacheService.class);
                String name = dynamicService.value();
                if (name.equals(mqType)) {
                    return entry.getKey();
                }
            }
        }
        return "";
    }

    @Override
    public void setString(String key, Object object, Long time) {
        String beanName = getCacheCommonServiceBeanName(cacheWare);
        strategyMap.get(beanName).setString(key, object, time);
    }

    @Override
    public void setString(String key, Object object) {

    }

    @Override
    public Object getByKey(String key) {
        return null;
    }

    @Override
    public void deleteByKey(String key) {

    }

    @Override
    public void deleteByWildcardKey(String key) {

    }

    @Override
    public void renameKey(String key, String newKey) {

    }

    @Override
    public void flushdb() {

    }

    @Override
    public Long deleteByMapKey(String key, String... mapKey) {
        return null;
    }

    @Override
    public Object getByMapKey(String key, String mapKey) {
        return null;
    }

    @Override
    public void addMap(String key, Map obj, Long time) {

    }
}
