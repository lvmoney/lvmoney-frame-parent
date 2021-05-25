package com.lvmoney.frame.mq.pulsar.customer.receiver;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/22
 * Copyright 四川******科技有限公司
 */


import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.mq.common.factory.MqDataHandServiceContext;
import com.lvmoney.frame.mq.common.vo.MessageVo;
import com.lvmoney.frame.mq.pulsar.common.constant.PulsarConstant;
import io.github.majusko.pulsar.annotation.PulsarConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Component
public class SynchronousReceiver {
    @Autowired
    MqDataHandServiceContext handMqServiceContext;

    @PulsarConsumer(topic = "frame.mq.name" + BaseConstant.CONNECTOR_UNDERLINE + PulsarConstant.SYN_QUEUE_NAME, clazz = MessageVo.class)
    public void receive(MessageVo mqDataVo) {
        String mqType = mqDataVo.getMsgType();
        handMqServiceContext.getData(mqType, mqDataVo);
    }
}
