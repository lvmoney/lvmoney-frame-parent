package com.zhy.frame.authentication.jwt.config;/**
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
import com.zhy.frame.base.core.constant.BaseConstant;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 * @Configuration
 */
public class FrameSerializerConfig {
    /**
     * @describe: 解决中文乱码问题
     * @param: []
     * @return: org.springframework.http.converter.HttpMessageConverter<java.lang.String>
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:12
     */
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName(BaseConstant.CHARACTER_ENCODE_UTF8_UPPER));
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
