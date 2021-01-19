package com.lvmoney.frame.mq.rabbitmq.provider.sender;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/21
 * Copyright 四川******科技有限公司
 */

import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.mq.common.annotations.MqService;
import com.lvmoney.frame.mq.common.constant.MqConstant;
import com.lvmoney.frame.mq.common.ro.ErrorRecordRo;
import com.lvmoney.frame.mq.common.service.MqSendService;
import com.lvmoney.frame.mq.common.vo.MessageVo;
import com.lvmoney.frame.mq.common.vo.MsgErrorVo;
import com.lvmoney.frame.mq.rabbitmq.common.constant.RabbitmqConstant;
import com.lvmoney.frame.mq.rabbitmq.provider.service.BaseRabbitmqErrorService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


/**
 * @describe：Fanout 就是我们熟悉的广播模式或者订阅模式，给Fanout转发器发送消息，绑定了这个转发器的所有队列都收到这个消息。
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@MqService(MqConstant.RABBIT_TYPE_RANOUT)
public class FanoutSenderImpl implements MqSendService {
    @Autowired
    BaseRabbitmqErrorService baseRabbitmqErrorService;
    @Autowired
    ConnectionFactory connectionFactory;

    @Bean("fanoutRabbitTemplate")
    @Scope("prototype")
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMandatory(true);
        template.setMessageConverter(new SerializerMessageConverter());
        return template;
    }

    @Value("${rabbitmq.error.record.expire:18000}")
    String expire;

    @Override
    public boolean send(MessageVo msg) {
        AtomicBoolean result = new AtomicBoolean(true);
        //由于Fanout的特性就算配置了binding_key，所有的队列都会收到消息
        RabbitTemplate rabbitTemplate = rabbitTemplate(connectionFactory);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            //exchange 错误被调用ack=false
            if (!ack) {
                ErrorRecordRo errorRecordRo = new ErrorRecordRo();
                errorRecordRo.setExpire(Long.valueOf(expire));
                MsgErrorVo msgErrorVo = new MsgErrorVo();
                msgErrorVo.setMessageVo(msg);
                msgErrorVo.setExName(RabbitmqConstant.EXCHANGE_FANOUT);
                List<MsgErrorVo> data = new ArrayList<>();
                data.add(msgErrorVo);
                errorRecordRo.setData(data);
                baseRabbitmqErrorService.errorRecord2Redis(errorRecordRo);
                result.set(false);
            }
        });
        rabbitTemplate.setReturnCallback((Message message, int replyCode, String replyText,
                                          String exchange, String routingKey) -> { //exchange 正确,queue 错误 ,confirm被回调, ack=true; return被回调 replyText:NO_ROUTE
            ErrorRecordRo errorRecordRo = new ErrorRecordRo();
            errorRecordRo.setExpire(Long.valueOf(expire));
            MsgErrorVo msgErrorVo = new MsgErrorVo();
            msgErrorVo.setMessageVo(msg);
            msgErrorVo.setExName(exchange);
            msgErrorVo.setRoutingKey(routingKey);
            List<MsgErrorVo> data = new ArrayList<>();
            data.add(msgErrorVo);
            errorRecordRo.setData(data);
            baseRabbitmqErrorService.errorRecord2Redis(errorRecordRo);
            result.set(false);
        });
        rabbitTemplate.convertAndSend(RabbitmqConstant.EXCHANGE_FANOUT, JsonUtil.t2JsonString(msg));
        return result.get();
    }


}
