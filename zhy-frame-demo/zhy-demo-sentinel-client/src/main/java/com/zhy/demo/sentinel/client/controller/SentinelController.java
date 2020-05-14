package com.zhy.demo.sentinel.client.controller;/**
 * 描述:
 * 包名:com.zhy.frame.ops.sentinel.controller
 * 版本信息: 版本1.0
 * 日期:2020/4/8
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.netflix.ribbon.hystrix.FallbackHandler;
import com.zhy.demo.sentinel.client.client.IDemoProviderClient;
import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.base.core.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/8 12:46
 */
@RestController
public class SentinelController {
    @Autowired
    IDemoProviderClient iDemoProviderClient;

    @GetMapping(value = "/hello/{id}")
    @SentinelResource(value = "/frame/provider/fallback", blockHandler = "exceptionHandler", fallback = "fallback")
    public String hello(@PathVariable("id") String id) {
        ApiResult<String> result = iDemoProviderClient.fallback(id);
        System.out.println(JsonUtil.t2JsonString(result));
        return "Hello Sentinel";
    }

    // 降级处理
    public String fallback() {
        return "服务降级,";
    }

    // 异常处理
    public String exceptionHandler(BlockException be) {
        be.printStackTrace();
        return "服务抛异常";
    }

}
