package com.zhy.frame.stream.rabbitmq.provider.controller;/**
 * 描述:
 * 包名:com.zhy.frame.stream.rabbitmq.provider.controller
 * 版本信息: 版本1.0
 * 日期:2020/4/25
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/25 18:34
 */
@RestController
public class SendController {
    @Autowired
    private Processor pipe;

    @GetMapping("/send")
    public void send(@RequestParam String message) {
        pipe.output().send(MessageBuilder.withPayload(message).build());
    }
}
