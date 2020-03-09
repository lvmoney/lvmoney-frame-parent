package com.zhy.demo.customer.fallback;/**
 * 描述:
 * 包名:com.zhy.demo.customer.fallback
 * 版本信息: 版本1.0
 * 日期:2020/3/6
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.demo.api.surface.IDemoProvider;
import com.zhy.frame.base.core.api.ApiResult;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/6 16:28
 */
@Component
public class IDemoProviderFallback implements IDemoProvider {
    @Override
    public ApiResult<String> demoGet(String name) {
        String test;
        return null;
    }
}
