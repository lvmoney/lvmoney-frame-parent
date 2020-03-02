package com.zhy.frame.route.gateway.server;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway.server
 * 版本信息: 版本1.0
 * 日期:2019/8/14
 * Copyright XXXXXX科技有限公司
 */


import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/14 8:40
 */
@Component
public class AuthenticationServerConfig {
    @Value("${rpc.server.authentication}")
    String authenticationServer;

    public AuthenticationServer authenticationServer(String token) {
        AuthenticationServer authorityServiceLoginInvoker = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .contract(new SpringMvcContract())
                .requestInterceptor(template -> template.header("token", token))
                .target(AuthenticationServer.class, authenticationServer);
        return authorityServiceLoginInvoker;
    }
}
