package com.lvmoney.demo.caffeine.controller;/**
 * 描述:
 * 包名:com.lvmoney.demo.cache.function
 * 版本信息: 版本1.0
 * 日期:2020/5/17
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.cache.caffeine.service.CaffeineService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/17 23:08
 */
@RestController
@Api(tags = {"项目管理"})
public class DemoCaffeineController {

    @Autowired
    CaffeineService caffeineService;

    @ApiOperation(value = "查询banner", notes = "查询banner")
    @PostMapping(value = "frame/caffeine/get")
    public ApiResult<String> get() {
        caffeineService.save("test", "test", "test");
        System.out.println(JsonUtil.t2JsonString(caffeineService.get("test", "test")));
        return ApiResult.success("test");
    }
}
