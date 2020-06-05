package com.zhy.frame.demo.customer.controller;/**
 * 描述:
 * 包名:com.zhy.demo.customer.function
 * 版本信息: 版本1.0
 * 日期:2020/3/5
 * Copyright XXXXXX科技有限公司
 */


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.demo.customer.client.IDemoProviderClient;
import com.zhy.frame.base.core.api.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/5 15:25
 */
@RestController
public class DemoCustomerController {
    @Autowired
    IDemoProviderClient iDemoProviderClient;

    //    @HystrixCommand(fallbackMethod = "frameHystrix")
    @GetMapping(value = "frame/customer/fallback")
    ApiResult<String> fallback(String name) {
        ApiResult apiResult = iDemoProviderClient.fallback(name);
        if (!apiResult.isSuccess()) {
            throw new BusinessException(apiResult.getCode(), apiResult.getMsg());

        }
        return ApiResult.success(apiResult.getData() + name);
    }

    @HystrixCommand(fallbackMethod = "frameHystrix")
    @GetMapping(value = "frame/customer/hystrix")
    ApiResult<String> hystrix(String name) {
        ApiResult apiResult = iDemoProviderClient.hystrix(name);
        return ApiResult.success(apiResult.getData() + name);
    }
}
