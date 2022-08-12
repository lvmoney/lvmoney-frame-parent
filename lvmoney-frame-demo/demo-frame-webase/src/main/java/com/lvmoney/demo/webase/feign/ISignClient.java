package com.lvmoney.demo.webase.feign;/**
 * 描述:
 * 包名:com.lvmoney.demo.webase.feign
 * 版本信息: 版本1.0
 * 日期:2021/6/24
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.blockchain.webase.sign.api.surface.ISign;
import com.lvmoney.frame.dispatch.feign.config.DisableHystrix;
import com.lvmoney.frame.dispatch.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司 
 * @version:v1.0
 * 2021/6/24 11:35  
 */
@FeignClient(name = "${webase.server.sign.name}", url = "${webase.server.sign.url}", configuration = {FeignConfig.class, DisableHystrix.class})
public interface ISignClient extends ISign {
}
