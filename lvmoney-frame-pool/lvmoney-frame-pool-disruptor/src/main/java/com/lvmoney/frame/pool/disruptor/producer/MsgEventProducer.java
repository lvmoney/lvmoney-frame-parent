package com.lvmoney.frame.pool.disruptor.producer;/**
 * 描述:
 * 包名:com.lvmoney.frame.pool.disruptor.producer
 * 版本信息: 版本1.0
 * 日期:2020/4/26
 * Copyright XXXXXX科技有限公司
 */


import com.lmax.disruptor.EventTranslatorVararg;
import com.lmax.disruptor.RingBuffer;
import com.lvmoney.frame.pool.disruptor.vo.MsgEvent;

import java.nio.ByteBuffer;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/26 15:43
 */
public class MsgEventProducer {

    public final RingBuffer<MsgEvent> ringBuffer;

    public MsgEventProducer(RingBuffer<MsgEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private EventTranslatorVararg eventTranslatorVararg = new EventTranslatorVararg<MsgEvent>() {
        @Override
        public void translateTo(MsgEvent msgEvent, long l, Object... objects) {
            msgEvent.setType((String) objects[0]);
            msgEvent.setData((String) objects[1]);
            msgEvent.setDate((Long) objects[2]);
        }
    };

    public void onData(String type, String data, Long date) {
        ringBuffer.publishEvent(eventTranslatorVararg, type, data, date);
    }
}
