package com.zhy.demo.sentinel.provider.controller;/**
 * 描述:
 * 包名:com.zhy.demo.provider.controller
 * 版本信息: 版本1.0
 * 日期:2020/3/5
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.base.core.exception.CommonException;
import com.zhy.frame.cloud.base.annotations.ReleaseServer;
import com.zhy.frame.demo.api.surface.IDemoProvider;
import org.springframework.web.bind.annotation.RestController;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/5 15:23
 */
@RestController
public class DemoProviderController implements IDemoProvider {
    @ReleaseServer(release = true)
    @Override
    public ApiResult<String> fallback(String name) {
//        return ApiResult.success("test");
        throw new BusinessException(CommonException.Proxy.PARAM_ERROR);
    }

    @Override
    public ApiResult<String> hystrix(String name) {
        return ApiResult.success("hystrix" + name);
    }

    @Override
    public ApiResult<String> test(String name) {
        return ApiResult.success("test" + name);
    }
}
