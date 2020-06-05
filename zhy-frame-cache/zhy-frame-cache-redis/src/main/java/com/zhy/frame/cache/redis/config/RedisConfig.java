package com.zhy.frame.cache.redis.config;

import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.base.core.util.SupportUtil;
import com.zhy.frame.cache.common.exception.CacheException;
import com.zhy.frame.cache.redis.converter.FrameFastJsonRedisSerializer;
import com.zhy.frame.cache.redis.enums.RedisMessageEnum;
import com.zhy.frame.cache.redis.listener.RedisListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @describe：redis序列化配置，如果不配置通过工具看redis中的数据会有乱码，但是也不会出现反序列化出错的问题（GenericFastJsonRedisSerializer）
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月18日 上午11:22:38
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
    /**
     * 是否支持Listener
     */
    @Value("${frame.redis.handler.support:false}")
    private boolean supportListener;
    /**
     * 监听类型默认为过期
     */
    @Value("${frame.redis.handler.type:expired}")
    private String listenerType;

    /**
     * @describe: 缓存管理器 spring boot 2.0后 配置缓存管理器 和2.0以前 不一样 根据自己的版本 配置
     * @param: [redisTemplate]
     * @return: org.springframework.data.redis.cache.RedisCacheManager
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:29
     */
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisTemplate) {
        return RedisCacheManager.create(redisTemplate);
    }

    /**
     * @describe: 以下两种redisTemplate自由根据场景选择
     * @param: [connectionFactory]
     * @return: org.springframework.data.redis.core.RedisTemplate<java.lang.Object, java.lang.Object>
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:29
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        // 配置redisTemplate
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
        // set key serializer
        StringRedisSerializer serializer = new StringRedisSerializer();
        // 设置key序列化类，否则key前面会多了一些乱码
        redisTemplate.setKeySerializer(serializer);
        redisTemplate.setHashKeySerializer(serializer);
        // fastjson serializer 如果用GenericFastJsonRedisSerializer，直接保存实体反序列化会报错.
        // 可以把对应的实体序列化（FastJsonUtil）后再保存就好了。
        // GenericFastJsonRedisSerializer fastSerializer = new GenericFastJsonRedisSerializer();
        /**
         * 忽略了@type字段，以免反序列化报错
         */
        FrameFastJsonRedisSerializer fastSerializer = new FrameFastJsonRedisSerializer();
        redisTemplate.setValueSerializer(fastSerializer);
        redisTemplate.setHashValueSerializer(fastSerializer);
        // 如果 KeySerializer 或者 ValueSerializer 没有配置，则对应的 KeySerializer、ValueSerializer 才使用这个 Serializer
        redisTemplate.setDefaultSerializer(fastSerializer);
        LettuceConnectionFactory factory = (LettuceConnectionFactory) connectionFactory;
        // factory
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.afterPropertiesSet();
        //开启事务支持
//        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(factory);
//        stringRedisTemplate.setEnableTransactionSupport(true);
        return stringRedisTemplate;
    }

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        String pattern = RedisMessageEnum.valueOf(listenerType.toUpperCase()).getType();
        if (!SupportUtil.support(supportListener)) {
            throw new BusinessException(CacheException.Proxy.REDIS_LISTENER_SUPPORT_ERROR);
        } else if (BaseConstant.SUPPORT_TRUE_BOOL == supportListener) {
            container.addMessageListener(new RedisListener(), new PatternTopic(pattern));
        }
        return container;
    }
}
