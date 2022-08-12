package com.lvmoney.frame.test;/**
 * 描述:
 * 包名:com.lvmoney.frame.test
 * 版本信息: 版本1.0
 * 日期:2020/4/27
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.pool.disruptor.service.impl.BaseMsgService;
import com.lvmoney.frame.pool.disruptor.service.impl.MsgServiceImpl;
import com.lvmoney.frame.pool.disruptor.vo.MsgEvent;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/27 9:48
 */
public class One2One {
    public static void main(String[] args) {
        BaseMsgService baseMsgService = new MsgServiceImpl();
        ExecutorService executor = Executors.newCachedThreadPool();
        MsgEvent msgEvent2 = new MsgEvent("test2", "testAA", new Date().getTime());
        MsgEvent msgEvent = new MsgEvent("test", "testAA", new Date().getTime());
        baseMsgService.one2One(executor, msgEvent, msgEvent2);
    }
}
