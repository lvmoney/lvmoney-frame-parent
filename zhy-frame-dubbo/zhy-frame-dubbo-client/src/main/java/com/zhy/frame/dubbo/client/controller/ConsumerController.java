package com.zhy.frame.dubbo.client.controller;/**
 * 描述:
 * 包名:com.zhy.frame.dubbo.client.function
 * 版本信息: 版本1.0
 * 日期:2019/12/27
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.dubbo.api.ICommonService;
import com.zhy.frame.dubbo.client.cloud.ICommonServiceClient;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/12/27 17:36
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Reference
    ICommonService iCommonService;
    @Autowired
    ICommonServiceClient iProviderServiceClient;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getList() {
        return iCommonService.common();
    }

    @RequestMapping(value = "/cloud", method = RequestMethod.GET)
    public String getCloud() {
        return iProviderServiceClient.common();
    }
}
