package com.zhy.frame.pool.disruptor.service.impl;/**
 * 描述:
 * 包名:com.zhy.frame.pool.disruptor.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/4/28
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.util.JsonUtil;
import com.zhy.frame.pool.common.annotion.MsgHandler;
import com.zhy.frame.pool.disruptor.service.MsgHandlerService;
import com.zhy.frame.pool.disruptor.vo.MsgEvent;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/28 11:43
 */
@MsgHandler(name = "user")
public class MsgHandlerServiceImpl implements MsgHandlerService {
    @Override
    public void onEvent(MsgEvent msgEvent) {
        System.out.println("消费者:" + JsonUtil.t2JsonString(msgEvent));
    }
}
