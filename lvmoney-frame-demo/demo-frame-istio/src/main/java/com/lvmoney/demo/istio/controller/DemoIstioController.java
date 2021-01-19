package com.lvmoney.demo.istio.controller;/**
 * 描述:
 * 包名:com.lvmoney.demo.istio.controller
 * 版本信息: 版本1.0
 * 日期:2020/12/3
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/12/3 15:55
 */
@RestController
public class DemoIstioController {
    @GetMapping(path = "/")
    public String getResult() {
        return "Hello I'm V1!";
    }

    @GetMapping(path = "/p")
    public String getResult2() {
        return "post Hello I'm V1!";
    }
}
