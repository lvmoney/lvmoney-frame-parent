package com.lvmoney.frame.newsql.scylla.controller;/**
 * 描述:
 * 包名:com.lvmoney.frame.nosql.scylla.function
 * 版本信息: 版本1.0
 * 日期:2020/4/16
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.core.util.SnowflakeIdFactoryUtil;
import com.lvmoney.frame.newsql.scylla.service.DeviceDataSerivce;
import com.lvmoney.frame.newsql.scylla.vo.DeviceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/16 10:38
 */
@RestController
public class ScyllaController {
    @Autowired
    DeviceDataSerivce deviceDataSerivce;

    @GetMapping(value = "scylla/save")
    public ApiResult<String> testJwt(@RequestParam("name") String name) {
        DeviceData deviceData = new DeviceData();
        deviceData.setId(String.valueOf(SnowflakeIdFactoryUtil.nextId()));
        deviceData.setDeviceId(1000L);
        deviceData.setValue("test");
        deviceData.setProperty("TEST");
        deviceDataSerivce.saveDeviceData(deviceData);
        return ApiResult.success(name + JsonUtil.t2JsonString(deviceData));
    }
}
