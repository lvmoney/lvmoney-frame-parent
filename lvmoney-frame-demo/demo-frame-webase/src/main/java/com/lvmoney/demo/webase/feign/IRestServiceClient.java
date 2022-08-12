package com.lvmoney.demo.webase.feign;/**
 * 描述:
 * 包名:com.lvmoney.demo.webase.feign
 * 版本信息: 版本1.0
 * 日期:2021/6/30
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.blockchain.webase.weidentity.api.surface.IRestService;
import com.lvmoney.frame.dispatch.feign.config.DisableHystrix;
import com.lvmoney.frame.dispatch.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0
 * 2021/6/30 19:42
 */
@FeignClient(name = "${webase.server.weid.name}", url = "${webase.server.weid.url}", configuration = {FeignConfig.class, DisableHystrix.class})
public interface IRestServiceClient extends IRestService {
}
