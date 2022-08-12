package com.lvmoney.frame.demo.mq.sender.controller;/**
 * 描述:
 * 包名:com.lvmoney.demo.file.controller
 * 版本信息: 版本1.0
 * 日期:2020/8/19
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.mq.common.constant.MqConstant;
import com.lvmoney.frame.mq.common.factory.MqSendServiceContext;
import com.lvmoney.frame.mq.common.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/8/19 13:35
 */
@RestController
public class MqCustomerController {
    @Autowired
    MqSendServiceContext mqSendServiceContext;

    @PostMapping(value = "frame/mq/sender/test")
    public ApiResult<String> testJwt() {
        MessageVo messageVo = new MessageVo();
        messageVo.setData("lvmoney");
        messageVo.setMsgType("USER_DATA_TYPE");
        messageVo.setDate(Instant.now().toEpochMilli());
        mqSendServiceContext.sendMsg(MqConstant.KAFKA_TYPE_SIMPLE, messageVo);
        return ApiResult.success("file");
    }
}
