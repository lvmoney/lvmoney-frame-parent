package com.lvmoney.frame.pool.disruptor.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.pool.disruptor.service
 * 版本信息: 版本1.0
 * 日期:2020/4/26
 * Copyright XXXXXX科技有限公司
 */


import com.lmax.disruptor.dsl.Disruptor;
import com.lvmoney.frame.pool.disruptor.producer.MsgEventProducer;
import com.lvmoney.frame.pool.disruptor.vo.MsgEvent;

import java.util.concurrent.ExecutorService;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/26 19:27
 */
public interface MsgService {
    /**
     * 获得single Disruptor
     *
     * @param ExecutorService:
     * @throws
     * @return: com.lmax.disruptor.dsl.Disruptor<com.lvmoney.frame.pool.disruptor.vo.MsgEvent>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/26 21:03
     */
    Disruptor getSingleDisruptor(ExecutorService executorService);

    /**
     * 获得multi Disruptor
     *
     * @param executorService:
     * @throws
     * @return: com.lmax.disruptor.dsl.Disruptor<com.lvmoney.frame.pool.disruptor.vo.MsgEvent>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/26 21:04
     */
    Disruptor getMultiDisruptor(ExecutorService executorService);

    /**
     * 绑定唯一消费者
     *
     * @param disruptor:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/27 9:18
     */
    void bindSingleCustomer(Disruptor disruptor);

    /**
     * 绑定唯一生成者
     *
     * @param disruptor:
     * @param msgEvent:
     * @throws
     * @return: com.lvmoney.frame.pool.disruptor.producer.MsgEventProducer
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/27 9:53
     */
    MsgEventProducer bindSingleProducer(Disruptor disruptor);

    /**
     * 生产者生成消息
     *
     * @param msgEventProducer:
     * @param msgEvent:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/27 9:55
     */
    void buildData(MsgEventProducer msgEventProducer, MsgEvent... msgEvent);

    /**
     * 开始
     *
     * @param disruptor:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/27 9:26
     */
    void start(Disruptor disruptor);

    /**
     * 结束
     *
     * @param disruptor:
     * @param executorService:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/27 9:30
     */
    void stop(Disruptor disruptor, ExecutorService executorService);

    /**
     * 一个生产者和一个消费者
     *
     * @param executorService:
     * @param msgEvent:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/4/27 9:43
     */
    void one2One(ExecutorService executorService, MsgEvent... msgEvent);
}
