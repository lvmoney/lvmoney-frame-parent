package com.lvmoney.frame.blockchain.webase.weidentity.apiserver.feign;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.weidentity.apiserver.feign
 * 版本信息: 版本1.0
 * 日期:2021/7/5
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.blockchain.webase.weidentity.api.surface.IWeId;
import com.lvmoney.frame.dispatch.feign.config.DisableHystrix;
import com.lvmoney.frame.dispatch.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/7/5 13:40
 */
@FeignClient(name = "${webase.server.weidentity.name}", url = "${webase.server.weidentity.url}", configuration = {FeignConfig.class, DisableHystrix.class})
public interface IWeIdClient extends IWeId {
}
