/**
 * 描述:
 * 包名:com.zhy.jwt.handler
 * 版本信息: 版本1.0
 * 日期:2019年1月4日  下午2:41:22
 * Copyright 四川******科技有限公司
 */

package com.zhy.frame.authentication.jwt.config;

import com.zhy.frame.authentication.jwt.interceptor.JwtInterceptor;
import com.zhy.frame.base.serializer.config.SerializerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @describe：设置了token拦截器和null转“”
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月4日 下午2:41:22
 */
@Primary
public class JwtInterceptorConfig extends SerializerConfig {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }


}
