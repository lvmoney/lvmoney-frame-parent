package com.lvmoney.demo.cache.config;/**
 * 描述:
 * 包名:com.lvmoney.frame.cache.lock.handler
 * 版本信息: 版本1.0
 * 日期:2020/5/19
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.authentication.jwt.interceptor.JwtInterceptor;
import com.lvmoney.frame.base.serializer.config.SerializerConfig;
import com.lvmoney.frame.cache.hot.interceptor.HotRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/19 9:21
 */
@Configuration
public class CacheInterceptorConfig extends SerializerConfig {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**");
        registry.addInterceptor(hotRequestInterceptor())
                .addPathPatterns("/**");

        super.addInterceptors(registry);
    }

    @Bean
    public HotRequestInterceptor hotRequestInterceptor() {
        return new HotRequestInterceptor();
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }

}