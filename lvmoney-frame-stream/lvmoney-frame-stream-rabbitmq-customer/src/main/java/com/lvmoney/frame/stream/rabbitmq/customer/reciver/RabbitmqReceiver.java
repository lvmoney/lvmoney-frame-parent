package com.lvmoney.frame.stream.rabbitmq.customer.reciver;/**
 * 描述:
 * 包名:com.lvmoney.frame.stream.rabbitmq.customer.reciver
 * 版本信息: 版本1.0
 * 日期:2020/4/25
 * Copyright XXXXXX科技有限公司
 */


import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.stereotype.Component;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/25 17:26
 */
@Component
@Slf4j
@EnableBinding(Processor.class)
public class RabbitmqReceiver {
    @StreamListener(Processor.INPUT)
    public void process(String message) {
        log.info("hahahah : " + message);
        System.out.println("hahahah : " + message);
    }
}
