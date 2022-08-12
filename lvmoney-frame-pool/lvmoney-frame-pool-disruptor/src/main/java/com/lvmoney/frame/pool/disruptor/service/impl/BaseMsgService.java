package com.lvmoney.frame.pool.disruptor.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.pool.disruptor.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/4/26
 * Copyright XXXXXX科技有限公司
 */


import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lvmoney.frame.pool.disruptor.factory.MsgEventFactory;
import com.lvmoney.frame.pool.disruptor.handler.MsgEventHandler;
import com.lvmoney.frame.pool.disruptor.producer.MsgEventProducer;
import com.lvmoney.frame.pool.disruptor.service.MsgService;
import com.lvmoney.frame.pool.disruptor.vo.MsgEvent;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/26 19:27
 */
public abstract class BaseMsgService implements MsgService {
    /**
     * buffer size 默认 1024*1024
     */
    @Value("${frame.disruptor.ringBufferSize:1048576}")
    private int ringBufferSize = 1024 * 1024;

    @Override
    public Disruptor getSingleDisruptor(ExecutorService executorService) {
        EventFactory<MsgEvent> eventFactory = new MsgEventFactory();
        Disruptor<MsgEvent> disruptor = new Disruptor(eventFactory, ringBufferSize, executorService,
                ProducerType.SINGLE, new YieldingWaitStrategy());
        return disruptor;
    }

    @Override
    public Disruptor getMultiDisruptor(ExecutorService executorService) {
        EventFactory<MsgEvent> eventFactory = new MsgEventFactory();
        Disruptor<MsgEvent> disruptor = new Disruptor(eventFactory, ringBufferSize, executorService,
                ProducerType.MULTI, new YieldingWaitStrategy());
        return disruptor;
    }

    @Override
    public void bindSingleCustomer(Disruptor disruptor) {
        disruptor.handleEventsWith(new MsgEventHandler());
    }

    @Override
    public MsgEventProducer bindSingleProducer(Disruptor disruptor) {
        RingBuffer<MsgEvent> ringBuffer = disruptor.getRingBuffer();
        MsgEventProducer producer = new MsgEventProducer(ringBuffer);
        return producer;
    }

    @Override
    public void buildData(MsgEventProducer msgEventProducer, MsgEvent... msgEvent) {
        Arrays.stream(msgEvent).forEach(e -> {
            msgEventProducer.onData(e.getType(), e.getData(), e.getDate());
        });
    }

    @Override
    public void start(Disruptor disruptor) {
        disruptor.start();
    }

    @Override
    public void stop(Disruptor disruptor, ExecutorService executorService) {
        disruptor.shutdown();
        executorService.shutdown();
    }

    @Override
    public void one2One(ExecutorService executorService, MsgEvent... msgEvent) {
        Disruptor<MsgEvent> disruptor = this.getSingleDisruptor(executorService);
        this.bindSingleCustomer(disruptor);
        this.start(disruptor);
        MsgEventProducer msgEventProducer = this.bindSingleProducer(disruptor);
        this.buildData(msgEventProducer, msgEvent);
        this.stop(disruptor, executorService);
    }
}
