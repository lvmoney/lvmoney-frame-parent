package com.zhy.frame.base.interceptor;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/27
 * Copyright 四川******科技有限公司
 */


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @describe： 序列化，拦截器基础类配置类
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 * @Configuration
 */
public abstract class AbstractInterceptorConfig extends WebMvcConfigurationSupport {
    private static final String CHARACTER_ENCODE_UTF8_UPPER = "UTF-8";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
    }

    public HttpMessageConverter<String> stringConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName(CHARACTER_ENCODE_UTF8_UPPER));
        return converter;
    }

    public HttpMessageConverter fastConverter() {
        //1、定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2、添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat,
                //字符串null返回空字符串
                SerializerFeature.WriteNullStringAsEmpty,
                //数值类型为0
                SerializerFeature.WriteNullNumberAsZero,
                //空字段保留
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteMapNullValue);
        fastJsonConfig.setCharset(Charset.forName(CHARACTER_ENCODE_UTF8_UPPER));
        //2-1 处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        //3、在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return fastConverter;
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.clear();
        converters.add(stringConverter());
        converters.add(fastConverter());
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowCredentials(true);
            }
        };
    }


}
