package com.zhy.demo.provider.controller;/**
 * 描述:
 * 包名:com.zhy.demo.provider.controller
 * 版本信息: 版本1.0
 * 日期:2020/3/5
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.demo.api.surface.IDemoProvider;
import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.cloud.base.annotations.ReleaseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/5 15:23
 */
@RestController
public class DemoProviderController implements IDemoProvider {
    @Override
    @ReleaseServer(release = true)
    public ApiResult<String> demoGet(String name) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(BaseConstant.AUTHORIZATION_TOKEN_KEY);
        System.out.println(token);
        return ApiResult.success(name);
    }


}
