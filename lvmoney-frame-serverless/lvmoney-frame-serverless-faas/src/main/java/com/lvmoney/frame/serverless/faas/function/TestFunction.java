package com.lvmoney.frame.serverless.faas.function;/**
 * 描述:
 * 包名:com.lvmoney.frame.serverless.faas.function
 * 版本信息: 版本1.0
 * 日期:2020/5/22
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.function.Function;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/22 10:28
 */
public class TestFunction implements Function<String, String> {
    @Override
    public String apply(String s) {
        return "Hello " + s + ", and welcome to Spring Cloud Function!!!";
    }
}
