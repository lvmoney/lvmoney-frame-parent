package com.zhy.frame.dubbo.client.cloud;/**
 * 描述:
 * 包名:com.zhy.frame.dubbo.client.cloud
 * 版本信息: 版本1.0
 * 日期:2019/12/31
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.dispatch.feign.config.FeignConfig;
import com.zhy.frame.dubbo.api.ICommonService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/12/31 9:43
 */
@FeignClient(name = "shop-service-provider", configuration = FeignConfig.class)

public interface ICommonServiceClient extends ICommonService {
}
