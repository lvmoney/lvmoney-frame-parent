package com.lvmoney.frame.mq.common.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.mq.core.service
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */


import com.lvmoney.frame.mq.common.vo.MessageVo;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/11/19 14:38
 */
public interface MqSendService {
    /**
     * 发送消息
     *
     * @param msg: 消息体
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/11/12 18:12
     */
    boolean send(MessageVo msg);
}
