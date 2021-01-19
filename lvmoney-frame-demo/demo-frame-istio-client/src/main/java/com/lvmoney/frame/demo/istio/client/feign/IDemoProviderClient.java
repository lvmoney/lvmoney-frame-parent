package com.lvmoney.frame.demo.istio.client.feign;/**
 * 描述:
 * 包名:com.lvmoney.demo.customer.client
 * 版本信息: 版本1.0
 * 日期:2020/3/5
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.dispatch.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/5 15:37
 */
@FeignClient(url = "localhost:8070", name = "test", configuration = {FeignConfig.class/*, DisableHystrix.class*/}, fallbackFactory = IDemoProviderClientFallbackFactory.class)
public interface IDemoProviderClient {
    @GetMapping(path = "/")
    String getResult();
}
