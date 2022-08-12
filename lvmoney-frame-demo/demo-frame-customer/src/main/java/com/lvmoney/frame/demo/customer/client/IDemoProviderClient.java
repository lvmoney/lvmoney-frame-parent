package com.lvmoney.frame.demo.customer.client;/**
 * 描述:
 * 包名:com.lvmoney.demo.customer.client
 * 版本信息: 版本1.0
 * 日期:2020/3/5
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.demo.api.surface.IDemoProvider;
import com.lvmoney.frame.dispatch.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/5 15:37
 */
@FeignClient(name = "${rpc.server.gateway}", configuration = {FeignConfig.class}, fallbackFactory = IDemoProviderClientFallback.class)
public interface IDemoProviderClient extends IDemoProvider {
}
