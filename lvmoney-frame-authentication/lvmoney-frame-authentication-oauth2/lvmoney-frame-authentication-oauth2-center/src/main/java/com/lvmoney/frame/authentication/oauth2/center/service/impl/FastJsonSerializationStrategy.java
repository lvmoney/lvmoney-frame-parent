package com.lvmoney.frame.authentication.oauth2.center.service.impl;

import com.lvmoney.frame.cache.redis.converter.FrameFastJsonRedisSerializer;
import org.springframework.security.oauth2.provider.token.store.redis.StandardStringSerializationStrategy;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class FastJsonSerializationStrategy extends StandardStringSerializationStrategy {
    private static final FrameFastJsonRedisSerializer OBJECT_SERIALIZER = new FrameFastJsonRedisSerializer();

    public FastJsonSerializationStrategy() {
    }

    @Override
    protected <T> T deserializeInternal(byte[] bytes, Class<T> clazz) {
        return (T) OBJECT_SERIALIZER.deserialize(bytes);
    }

    @Override
    protected byte[] serializeInternal(Object object) {
        return OBJECT_SERIALIZER.serialize(object);
    }
}