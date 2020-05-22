package com.zhy.frame.cache.caffeine.service;/**
 * 描述:
 * 包名:com.zhy.frame.cache.caffeine.service
 * 版本信息: 版本1.0
 * 日期:2020/5/16
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/16 10:37
 */
public interface CaffeineService {
    /**
     * 获得值，缓存key为默认值servername_${servername}
     *
     * @param key:
     * @throws
     * @return: T
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/16 10:52
     */
    <T> T get(Object key);

    /**
     * 获得值
     *
     * @param cacheName:
     * @param key:
     * @throws
     * @return: T
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/16 10:53
     */
    <T> T get(String cacheName, Object key);

    /**
     * 保存值，缓存key为默认值servername_${servername}
     *
     * @param key:
     * @param value:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/16 10:53
     */
    void save(Object key, Object value);

    /**
     * 保存值
     *
     * @param cacheName:
     * @param key:
     * @param value:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/16 10:53
     */
    void save(String cacheName, Object key, Object value);
}
