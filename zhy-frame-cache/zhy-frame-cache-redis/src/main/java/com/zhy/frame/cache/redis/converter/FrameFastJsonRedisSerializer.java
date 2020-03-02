package com.zhy.frame.cache.redis.converter;/**
 * 描述:
 * 包名:com.zhy.redis.converter
 * 版本信息: 版本1.0
 * 日期:2019/7/27
 * Copyright 四川******科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

import java.nio.charset.Charset;

/**
 * @describe：redis fastjson序列化，GenericFastJsonRedisSerializer有个bug：
 * 直接存对象到redis，反序列化会报错：autoType is not support
 * 暂未找到简单合适的解决方案，于是把@type忽略了，就不报错了。
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/7/27 10:43
 */
@NoArgsConstructor
public class FrameFastJsonRedisSerializer<T> implements RedisSerializer<T> {
    private static final String IGNORE_CHAR = "type";

    private ObjectMapper objectMapper = new ObjectMapper();
    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Class<T> clazz;

    static {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    public FrameFastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        PropertyFilter profilter = new PropertyFilter() {

            @Override
            public boolean apply(Object object, String name, Object value) {
                if (IGNORE_CHAR.equalsIgnoreCase(name)) {
                    //false表示last字段将被排除在外
                    return false;
                }
                return true;
            }

        };
        return JSON.toJSONString(t, profilter, SerializerFeature.DisableCircularReferenceDetect).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);

        return JSON.parseObject(str, clazz);
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        Assert.notNull(objectMapper, "'objectMapper' must not be null");
        this.objectMapper = objectMapper;
    }

    protected JavaType getJavaType(Class<?> clazz) {
        return TypeFactory.defaultInstance().constructType(clazz);
    }


}
