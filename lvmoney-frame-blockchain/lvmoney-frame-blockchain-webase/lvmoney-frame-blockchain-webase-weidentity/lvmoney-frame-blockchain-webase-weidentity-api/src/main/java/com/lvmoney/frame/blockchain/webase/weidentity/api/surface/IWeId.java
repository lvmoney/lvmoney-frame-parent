package com.lvmoney.frame.blockchain.webase.weidentity.api.surface;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.weidentity.api.surface
 * 版本信息: 版本1.0
 * 日期:2021/7/5
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.blockchain.webase.weidentity.api.ao.RequestAo;
import com.lvmoney.frame.blockchain.webase.weidentity.api.constant.WeidConstant;
import com.lvmoney.frame.blockchain.webase.weidentity.api.vo.ResponseVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/7/5 11:37
 */
public interface IWeId {

    /**
     * 创建WeIdentity DID（无参创建方式）
     *
     * @param requestAo:
     * @throws
     * @return: com.lvmoney.frame.blockchain.webase.weidentity.api.vo.ResponseVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/4 12:02
     */
    @PostMapping(value = WeidConstant.URL_WEID_API_INVOKE)
    ResponseVo invoke(@RequestBody RequestAo requestAo);
}
