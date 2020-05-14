package com.zhy.frame.test;/**
 * 描述:
 * 包名:com.zhy.frame.test
 * 版本信息: 版本1.0
 * 日期:2020/4/27
 * Copyright XXXXXX科技有限公司
 */


import com.lmax.disruptor.dsl.Disruptor;
import com.zhy.frame.pool.disruptor.handler.MsgEventHandler;
import com.zhy.frame.pool.disruptor.producer.MsgEventProducer;
import com.zhy.frame.pool.disruptor.service.impl.BaseMsgService;
import com.zhy.frame.pool.disruptor.vo.MsgEvent;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/27 21:34
 */
public class One2Many {
    public static void main(String[] args) {
//        BaseMsgService baseMsgService = new BaseMsgService();
//        ExecutorService executor = Executors.newCachedThreadPool();
//        Disruptor<MsgEvent> disruptor = baseMsgService.getSingleDisruptor(executor);
//        disruptor.handleEventsWith(new MsgEventHandler());
////        disruptor.handleEventsWith(new MsgEventHandler());
//        baseMsgService.start(disruptor);
//        MsgEventProducer producer = baseMsgService.bindSingleProducer(disruptor);
//        MsgEvent msgEvent2 = new MsgEvent("test2", "testAA", new Date().getTime());
//        MsgEvent msgEvent = new MsgEvent("test", "testAA", new Date().getTime());
//        baseMsgService.buildData(producer, msgEvent, msgEvent2);
//        baseMsgService.stop(disruptor, executor);
    }
}
