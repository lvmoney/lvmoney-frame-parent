package com.zhy.demo.customer.controller;/**
 * 描述:
 * 包名:com.zhy.demo.customer.controller
 * 版本信息: 版本1.0
 * 日期:2020/3/5
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.demo.customer.client.IDemoProviderClient;
import com.zhy.frame.base.core.api.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/5 15:25
 */
@RestController
public class DemoCustomerController {
    @Autowired
    IDemoProviderClient iDemoProviderClient;

    @GetMapping(value = "frame/customer/demo/get")
    ApiResult<String> demoGet(String name) {
        ApiResult apiResult = iDemoProviderClient.demoGet(name);
        return ApiResult.success(iDemoProviderClient.demoGet(name).getData() + name);
    }
}
