package com.lvmoney.frame.blockchain.webase.weidentity.common.prop;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.wallet.apiserver.prop
 * 版本信息: 版本1.0
 * 日期:2021/6/26
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/26 16:53
 */
@Component
@Data
public class WeIdentityProp {

    /**
     * weid合约方法默认版本
     */
    @Value("${webase.weid.default.version:1.0.0}")
    private String defaultUseCns;
}
