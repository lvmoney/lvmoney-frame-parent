package com.lvmoney.frame.dispatch.feign.interceptor;/**
 * 描述:
 * 包名:com.lvmoney.frame.dispatch.feign.interceptor
 * 版本信息: 版本1.0
 * 日期:2020/3/8
 * Copyright XXXXXX科技有限公司
 */


import cn.hutool.core.util.ObjectUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.lvmoney.frame.base.core.constant.BaseConstant.AUTHORIZATION_TOKEN_KEY;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/8 22:53
 */

@Configuration
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = Objects.requireNonNull(attributes).getRequest();
        String token = request.getHeader(AUTHORIZATION_TOKEN_KEY);
        if (ObjectUtil.isNotEmpty(token)) {
            template.header(AUTHORIZATION_TOKEN_KEY, request.getHeader(AUTHORIZATION_TOKEN_KEY));
        }
    }
}
