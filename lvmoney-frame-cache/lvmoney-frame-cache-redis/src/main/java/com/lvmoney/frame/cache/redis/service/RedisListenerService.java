package com.lvmoney.frame.cache.redis.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.cache.redis.service
 * 版本信息: 版本1.0
 * 日期:2020/5/18
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.data.redis.connection.Message;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/18 10:24
 */
public interface RedisListenerService {
    /**
     * 删除
     *
     * @param message:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/18 10:24
     */
    void delete(Message message);

    /**
     * 增
     *
     * @param message:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/18 10:26
     */
    void set(Message message);

    /**
     * 重命名
     *
     * @param message:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/18 10:26
     */
    void rename(Message message);

    /**
     * 设置过期时间
     *
     * @param message:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/18 10:26
     */
    void expire(Message message);

    /**
     * 过期时
     *
     * @param message:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/18 10:26
     */
    void expired(Message message);

    /**
     * 其他
     *
     * @param message:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/18 10:26
     */
    void other(Message message);

    /**
     * 获得热点key
     *
     * @throws
     * @return: java.util.List<com.lvmoney.frame.cache.lock.vo.HotRequestVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/18 15:02
     */
    Object getHotspot();
}
