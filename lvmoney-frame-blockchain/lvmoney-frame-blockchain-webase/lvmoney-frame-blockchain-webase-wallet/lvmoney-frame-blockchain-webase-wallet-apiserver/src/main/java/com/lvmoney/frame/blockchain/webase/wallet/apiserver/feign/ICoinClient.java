package com.lvmoney.frame.blockchain.webase.wallet.apiserver.feign;/**
 * 描述:
 * 包名:com.lvmoney.demo.webase.feign
 * 版本信息: 版本1.0
 * 日期:2021/6/23
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.blockchain.webase.wallet.api.surface.ICoin;
import com.lvmoney.frame.dispatch.feign.config.DisableHystrix;
import com.lvmoney.frame.dispatch.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/23 15:31
 */
@FeignClient(name = "${webase.server.coin.name}", url = "${webase.server.coin.url}", configuration = {FeignConfig.class, DisableHystrix.class})
public interface ICoinClient extends ICoin {
}
