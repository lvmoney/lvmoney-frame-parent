package com.zhy.frame.core.config;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/27
 * Copyright 四川******科技有限公司
 */


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @describe：1、这里注释打开能够完成返回值null转""的处理 2、不过打开后会和jwt的拦截器冲突（暂未解决）
 * 3、所以注释了，在jwt的拦截器中做了null转""的处理
 * 4、架构的设计本应该把null的转换放到common 模块中
 * 5、但是jwt token的方式应该每个项目都会用到放到jwt问题也不大
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 * @Configuration
 */
public class FrameSerializerConfig extends WebMvcConfigurationSupport {

    /**
     * @describe: 解决中文乱码问题
     * @param: []
     * @return: org.springframework.http.converter.HttpMessageConverter<java.lang.String>
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:11
     */
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }

    /**
     * @describe: jackson builder
     * @param: [builder]
     * @return: com.fasterxml.jackson.databind.ObjectMapper
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:12
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
                jsonGenerator.writeString("");
            }
        });
        return objectMapper;
    }


}
