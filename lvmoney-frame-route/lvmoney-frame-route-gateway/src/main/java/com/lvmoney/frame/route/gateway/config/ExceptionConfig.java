package com.lvmoney.frame.route.gateway.config;/**
 * 描述:
 * 包名:com.lvmoney.frame.route.gateway.handler
 * 版本信息: 版本1.0
 * 日期:2020/3/14
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.route.gateway.exception.GatewayExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/14 21:43
 */
@Configuration
public class ExceptionConfig {
    /**
     * 自定义异常处理[@@]注册Bean时依赖的Bean，会从容器中直接获取，所以直接注入即可
     */
    @Primary
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ErrorWebExceptionHandler errorWebExceptionHandler(ObjectProvider<List<ViewResolver>> viewResolversProvider,
                                                             ServerCodecConfigurer serverCodecConfigurer) {
        GatewayExceptionHandler gatewayExceptionHandler = new GatewayExceptionHandler();
        gatewayExceptionHandler.setViewResolvers(viewResolversProvider.getIfAvailable(Collections::emptyList));
        gatewayExceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
        gatewayExceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());
        return gatewayExceptionHandler;
    }

}
