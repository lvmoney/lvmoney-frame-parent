package com.lvmoney.frame.demo.provider.controller;/**
 * 描述:
 * 包名:com.lvmoney.demo.provider.function
 * 版本信息: 版本1.0
 * 日期:2020/3/8
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.cloud.base.annotations.NotAuthority;
import com.lvmoney.frame.cloud.base.annotations.NotToken;
import com.lvmoney.frame.cloud.common.ro.AuthorizedRo;
import com.lvmoney.frame.cloud.common.service.AuthorizedService;
import com.lvmoney.frame.demo.provider.ao.TestAo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/8 17:40
 */
@RestController
public class DemoController {
    @Autowired
    AuthorizedService authorizedService;

    @NotToken
    @GetMapping(value = "frame/provider/jwt/get")
    public ApiResult<String> testJwt(@RequestParam("name") String name) {
        return ApiResult.success(name + "jwt");
    }

    @NotAuthority
    @GetMapping(value = "frame/provider/shiro/get")
    public ApiResult<String> testShiro(TestAo testAo) {
        return ApiResult.success(JsonUtil.t2JsonString(testAo) + "shiro");
    }

    @GetMapping(value = "frame/provider/sysId/add")
    public ApiResult<String> sysId(@RequestParam("sysId") String sysId) {
        AuthorizedRo authorizedRo = new AuthorizedRo();
        authorizedRo.setExpired(180000L);
        authorizedRo.setData(new HashMap() {{
            put("www.provider.com", new HashSet() {{
                add(sysId);
            }});
        }});
        authorizedService.addAuthorized2Server(authorizedRo);
        return ApiResult.success(sysId);
    }
}
