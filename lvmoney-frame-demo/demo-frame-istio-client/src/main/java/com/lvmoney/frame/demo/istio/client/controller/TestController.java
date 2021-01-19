package com.lvmoney.frame.demo.istio.client.controller;/**
 * 描述:
 * 包名:com.lvmoney.frame.demo.istio.client.controller
 * 版本信息: 版本1.0
 * 日期:2020/12/21
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.demo.istio.client.feign.IDemoProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/12/21 19:33
 */
@RestController
public class TestController {
    @Autowired
    IDemoProviderClient iDemoProviderClient;

    @PostMapping(path = "/")
    public String getResult() {
        return "Hello I'm V2! and" + iDemoProviderClient.getResult();
    }
}
