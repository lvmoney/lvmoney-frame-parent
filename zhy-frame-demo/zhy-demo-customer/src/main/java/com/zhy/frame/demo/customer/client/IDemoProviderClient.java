package com.zhy.frame.demo.customer.client;/**
 * 描述:
 * 包名:com.zhy.demo.customer.client
 * 版本信息: 版本1.0
 * 日期:2020/3/5
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.demo.api.surface.IDemoProvider;
import com.zhy.frame.dispatch.feign.config.DisableHystrix;
import com.zhy.frame.dispatch.feign.config.FeignConfig;
import com.zhy.frame.dispatch.feign.interceptor.FeignInterceptor;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/5 15:37
 */
@FeignClient(name = "${rpc.server.gateway}", configuration = {FeignConfig.class}, fallbackFactory = IDemoProviderClientFallback.class)
public interface IDemoProviderClient extends IDemoProvider {
}
