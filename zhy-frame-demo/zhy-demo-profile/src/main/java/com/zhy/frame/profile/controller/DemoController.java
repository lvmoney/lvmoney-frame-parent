package com.zhy.frame.profile.controller;/**
 * 描述:
 * 包名:com.zhy.demo.provider.function
 * 版本信息: 版本1.0
 * 日期:2020/3/8
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.base.core.util.JsonUtil;
import com.zhy.frame.profile.dao.AddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/8 17:40
 */
@RestController
public class DemoController {
    @Autowired
    AddressDao addressDao;

    @GetMapping(value = "frame/profile/test")
    public ApiResult<String> test() {
        System.out.println(JsonUtil.t2JsonString(addressDao.selectById("1")));
        return ApiResult.success("shiro");
    }

}
