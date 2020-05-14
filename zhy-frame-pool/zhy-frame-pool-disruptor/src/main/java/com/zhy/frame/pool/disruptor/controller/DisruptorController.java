package com.zhy.frame.pool.disruptor.controller;/**
 * 描述:
 * 包名:com.zhy.frame.pool.disruptor.controller
 * 版本信息: 版本1.0
 * 日期:2020/4/28
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.pool.disruptor.service.MsgService;
import com.zhy.frame.pool.disruptor.vo.MsgEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/28 11:58
 */
@RestController
public class DisruptorController {
    @Autowired
    MsgService msgService;

    @GetMapping(value = "frame/disruptor/test")
    public ApiResult<String> test() {

        ExecutorService executor = Executors.newCachedThreadPool();
        MsgEvent msgEvent2 = new MsgEvent("user", "testAA", new Date().getTime());
        MsgEvent msgEvent = new MsgEvent("user", "testAA", new Date().getTime());
        msgService.one2One(executor, msgEvent, msgEvent2);
        return ApiResult.success("success");
    }
}
