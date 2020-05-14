/**
 * 描述:
 * 包名:com.zhy.redis.service
 * 版本信息: 版本1.0
 * 日期:2019年1月7日  下午5:19:43
 * Copyright 四川******科技有限公司
 */

package com.zhy.frame.cache.redis.service;


import com.zhy.frame.cache.common.service.CacheCommonService;
import com.zhy.frame.core.vo.Page;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月7日 下午5:19:43
 */

public abstract class BaseRedisService implements CacheCommonService {


    /**
     * 存储set
     *
     * @param key:    redis key
     * @param object: 数据对象
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:40
     */
    public abstract void setSet(String key, Object object);

    /**
     * 设置过期时间
     *
     * @param key:  redis key
     * @param time: 过期时间
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:41
     */
    public abstract void setExpire(String key, Long time);


    /**
     * 添加对象到redis 里面的list中
     * redis中的 list 是双向的 所以添加的时候需要注意
     * rightPush 先进先出 leftPush 先进后出 这里 需要注意
     *
     * @param key:  list 对应的key
     * @param obj:  需要存的对象
     * @param time: 失效时间
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:42
     */
    public abstract void addList(String key, List obj, Long time);

    /**
     * 添加对象到redis 里面的list中
     * redis中的 list 是双向的 所以添加的时候需要注意
     * rightPush 先进先出 leftPush 先进后出 这里 需要注意
     *
     * @param key: list 对应的key
     * @param obj: 存储的对象
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:43
     */
    public abstract void addList(String key, List obj);

    /**
     * opsForList().range(key, start, end);  取范围值  redis里面的list下标从0开始
     * 流程 拿到key 对应的list 取 0 到 5  和 mysql的limt  类似 注意下标即可
     * 从redis list 里面的获取数据分页
     *
     * @param page: 分页信息
     * @param key:  redis key
     * @return: com.zhy.core.vo.Page
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:43
     */
    public abstract Page getListPage(Page page, String key);


    /**
     * 获得list的长度
     *
     * @param key: redis key
     * @return: java.lang.Long
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:44
     */
    public abstract Long getListSize(String key);

    /**
     * 从存储在键中的列表中删除等于值的元素的第一个计数事件。count> 0：删除等于从左到右移动的值的第一个元素；count< 0：删除等于从右到左移动的值的第一个元素；count = 0：删除等于value的所有元素。
     *
     * @param key:   redis key
     * @param count: count< 0：删除等于从右到左移动的值的第一个元素；count = 0：删除等于value的所有元素。
     * @param obj:   删除的对象
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:44
     */
    public abstract void rmValueByList(String key, Long count, Object obj);

    /**
     * 获得map的长度
     *
     * @param key: redis key
     * @return: java.lang.Long
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:45
     */
    public abstract Long getMapSize(String key);


    /**
     * 通过redis key获得map的数据
     *
     * @param key: redis key
     * @return: java.lang.Object
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:46
     */
    public abstract Object getMapByKey(String key);

    /**
     * 根据key值获得map的值
     *
     * @param page: 分页数据
     * @param key:  redis key
     * @return: com.zhy.core.vo.Page
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:47
     */
    public abstract Page getValueByKey(Page page, String key);

    /**
     * 判断map key是否存在
     *
     * @param key:    redis key
     * @param mapKey: map key
     * @return: boolean
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:47
     */
    public abstract boolean isExistMapKey(String key, String mapKey);


    /**
     * 获取指定key的所有list数据
     *
     * @param key: redis key
     * @return: java.util.List
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 19:48
     */
    public abstract List getListAll(String key);


    /**
     * 返回给定 key的剩余生存时间(TTL, time to live)
     *
     * @param key: 主键
     * @throws
     * @return: long
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/11/4 16:00
     */
    public abstract long ttl(String key);

    /**
     * 通配符获得key
     * @param wildcard:
     * @throws
     * @return: java.util.List<java.lang.String>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/21 19:03
     */
    @Override
    public abstract Set<String> getKeysByWildcard(String wildcard);


}
