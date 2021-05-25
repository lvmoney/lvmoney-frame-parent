package com.lvmoney.frame.mq.pulsar.provider.sender;/**
 * 描述:
 * 包名:com.lvmoney.frame.mq.pulsar.provider.sender
 * 版本信息: 版本1.0
 * 日期:2021/5/25
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.mq.common.annotations.MqService;
import com.lvmoney.frame.mq.common.constant.MqConstant;
import com.lvmoney.frame.mq.common.service.MqSendService;
import com.lvmoney.frame.mq.common.vo.MessageVo;
import com.lvmoney.frame.mq.pulsar.common.constant.PulsarConstant;
import io.github.majusko.pulsar.producer.PulsarTemplate;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/5/25 13:35
 */
@MqService(MqConstant.PULSAR_TYPE_SIMPLE)
public class SimpleSenderImpl implements MqSendService {
    @Value("${frame.mq.name:lvmoney}")
    private String frameMqName;
    @Autowired
    private PulsarTemplate<MessageVo> producer;

    @Override
    public boolean send(MessageVo msg) {
        try {
            producer.send(frameMqName + BaseConstant.CONNECTOR_UNDERLINE + PulsarConstant.SIMPLE_QUEUE_NAME, msg);
        } catch (PulsarClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
