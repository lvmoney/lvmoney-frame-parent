package com.zhy.frame.cache.common.service;/**
 * 描述:
 * 包名:com.zhy.frame.cache.core.service
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */


import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/11/19 14:00
 */
public interface CacheCommonService<T> {
    /**
     * 存储数据 string并设置过期时间
     *
     * @param key:     key
     * @param object: 数据对象
     * @param time:   失效时间
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:39
     */
    void setString(String key, Object object, Long time);

    /**
     * 存储String
     *
     * @param key:    key
     * @param object: 数据对象
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:40
     */
    void setString(String key, Object object);

    /**
     * 通过key值获得数据
     *
     * @param key: key
     * @throws
     * @return: java.lang.Object
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/11/20 11:24
     */
    Object getByKey(String key);

    /**
     * 根据key值删除对象
     *
     * @param key: key
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/11/20 11:25
     */
    void deleteByKey(String key);

    /**
     * 通过通配符的方式删除对象
     *
     * @param key:
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/11/20 11:27
     */
    void deleteByWildcardKey(String key);


    /**
     * 重命名key
     *
     * @param key:
     * @param newKey:
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/11/20 11:28
     */

    void renameKey(String key, String newKey);

    /**
     * 清空库
     *
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/11/20 11:28
     */
    void flushdb();


    /**
     * 增加map
     *
     * @param key:  key
     * @param obj:  对象
     * @param time: 失效时间
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:46
     */
    void addMap(String key, Map<String, T> obj, Long time);

    /**
     * 根据key和map的key值删除数据
     *
     * @param key:    key
     * @param mapKey: map key
     * @return: java.lang.Long
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:47
     */
    Long deleteByMapKey(String key, String... mapKey);

    /**
     * 通过map的key获得数据
     *
     * @param key:     key
     * @param mapKey: map key
     * @return: java.lang.Object
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:46
     */
    Object getByMapKey(String key, String mapKey);

}
