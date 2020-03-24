package com.zhy.frame.route.gateway.feign;/**
 * 描述:
 * 包名:com.zhy.frame.route.gateway.feign
 * 版本信息: 版本1.0
 * 日期:2020/3/15
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.authentication.api.surface.IAuthorityCheck;
import com.zhy.frame.dispatch.feign.config.FeignConfig;
import com.zhy.frame.dispatch.feign.interceptor.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/15 12:42
 */
@FeignClient(name = "${rpc.server.authority}", configuration = {FeignConfig.class})
public interface IAuthorityCheckClient extends IAuthorityCheck {
}
