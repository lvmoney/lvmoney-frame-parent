package com.zhy.frame.config.nacos.controller;/**
 * 描述:
 * 包名:com.zhy.k8s.nacos.function
 * 版本信息: 版本1.0
 * 日期:2019/11/14
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/14 14:04
 */
@RestController
@RequestMapping(value = "/config")
@RefreshScope
public class ConfigController {
    /**
     * 读取hengboy.name配置信息
     */
    @Value(value = "${lvmoney.test:2323}")
    private String userName;

    /**
     * 获取配置内容
     *
     * @return
     */
    @RequestMapping(value = "/get")
    public String getConfig() {
        return userName;
    }
}
