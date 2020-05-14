package com.zhy.demo.sentinel.client.client;/**
 * 描述:
 * 包名:com.zhy.demo.sentinel.client.client
 * 版本信息: 版本1.0
 * 日期:2020/4/10
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.api.ApiResult;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/10 10:48
 */
@Component
public class IDemoProviderClientFallback implements IDemoProviderClient {
    @Override
    public ApiResult<String> fallback(String name) {
        return null;
    }

    @Override
    public ApiResult<String> hystrix(String name) {
        return null;
    }

    @Override
    public ApiResult<String> test(String name) {
        return null;
    }
}
