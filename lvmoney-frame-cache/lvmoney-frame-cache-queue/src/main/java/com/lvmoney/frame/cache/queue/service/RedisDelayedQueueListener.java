package com.lvmoney.frame.cache.queue.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.cache.queue.service
 * 版本信息: 版本1.0
 * 日期:2020/5/20
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.cache.queue.vo.MessageVo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/20 15:08
 */
public interface RedisDelayedQueueListener {
    /**
     * 执行
     *
     * @param messageVo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/20 15:08
     */
    void receive(MessageVo messageVo);
}
