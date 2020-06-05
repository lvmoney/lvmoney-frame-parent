package com.zhy.frame.cache.lock.config;/**
 * 描述:
 * 包名:com.zhy.frame.cache.lock.handler
 * 版本信息: 版本1.0
 * 日期:2020/5/19
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.zhy.frame.base.serializer.config.SerializerConfig;
import com.zhy.frame.cache.lock.interceptor.HotRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/19 9:21
 */
public class HotRequestInterceptorConfig extends SerializerConfig {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(hotRequestInterceptor())
                .addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Bean
    public HotRequestInterceptor hotRequestInterceptor() {
        return new HotRequestInterceptor();
    }
}