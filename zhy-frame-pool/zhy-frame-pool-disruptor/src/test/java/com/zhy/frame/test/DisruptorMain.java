package com.zhy.frame.test;/**
 * 描述:
 * 包名:com.zhy.frame.test
 * 版本信息: 版本1.0
 * 日期:2020/4/26
 * Copyright XXXXXX科技有限公司
 */


import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.zhy.frame.pool.disruptor.factory.MsgEventFactory;
import com.zhy.frame.pool.disruptor.handler.MsgEventHandler;
import com.zhy.frame.pool.disruptor.producer.MsgEventProducer;
import com.zhy.frame.pool.disruptor.vo.MsgEvent;

import javax.xml.crypto.Data;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/26 18:57
 */
public class DisruptorMain {
    public static void main(String[] args) {
        // 1.创建一个可缓存的线程 提供线程来触发Consumer 的事件处理
        ExecutorService executor = Executors.newCachedThreadPool();
        // 2.创建工厂
        EventFactory<MsgEvent> eventFactory = new MsgEventFactory();
        // 3.创建ringBuffer 大小
        int ringBufferSize = 1024 * 1024; // ringBufferSize大小一定要是2的N次方
        // 4.创建Disruptor
        Disruptor<MsgEvent> disruptor = new Disruptor<MsgEvent>(eventFactory, ringBufferSize, executor,
                ProducerType.SINGLE, new YieldingWaitStrategy());
        // 5.连接消费端方法
        disruptor.handleEventsWith(new MsgEventHandler());
        // 6.启动
        disruptor.start();
        // 7.创建RingBuffer容器
        RingBuffer<MsgEvent> ringBuffer = disruptor.getRingBuffer();
        // 8.创建生产者
        MsgEventProducer producer = new MsgEventProducer(ringBuffer);
        for (int i = 1; i <= 100; i++) {
            producer.onData("test", "test2", new Date().getTime());
        }
        //10.关闭disruptor和executor
        disruptor.shutdown();
        executor.shutdown();
    }
}
