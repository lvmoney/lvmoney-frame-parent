package com.zhy.frame.route.gateway.server;/**
 * 描述:
 * 包名:com.zhy.frame.route.gateway.server
 * 版本信息: 版本1.0
 * 日期:2020/3/8
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.authentication.api.surface.IJwtCheck;
import com.zhy.frame.authentication.api.surface.IShiroCheck;
import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.dispatch.feign.config.FeignClientErrorDecoder;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/8 10:36
 */
@Component
public class IShiroCheckConfig {
    @Value("${rpc.server.authority}")
    String authorityServer;
    /**
     * header content-type
     */
    private static final String CONTENT_TYPE = "Content-Type";
    /**
     * header content-type json
     */
    private static final String CONTENT_TYPE_JSON = "application/json;charset=UTF-8";

    public IShiroCheck authorityServer(String token) {
        IShiroCheck iShiroCheck = Feign.builder()
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .contract(new SpringMvcContract())
                .requestInterceptor(template -> template.header(BaseConstant.AUTHORIZATION_TOKEN_KEY, token).header(CONTENT_TYPE, CONTENT_TYPE_JSON))
                .target(IShiroCheck.class, authorityServer);
        return iShiroCheck;
    }
}
