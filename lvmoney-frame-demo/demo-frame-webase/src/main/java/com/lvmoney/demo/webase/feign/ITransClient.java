package com.lvmoney.demo.webase.feign;/**
 * 描述:
 * 包名:com.lvmoney.demo.webase.feign
 * 版本信息: 版本1.0
 * 日期:2021/6/24
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.blockchain.webase.trans.api.ITrans;
import com.lvmoney.frame.dispatch.feign.config.DisableHystrix;
import com.lvmoney.frame.dispatch.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司 
 * @version:v1.0
 * 2021/6/24 19:29  
 */
@FeignClient(name = "${webase.server.trans.name}", url = "${webase.server.trans.url}", configuration = {FeignConfig.class, DisableHystrix.class})
public interface ITransClient extends ITrans {
}
