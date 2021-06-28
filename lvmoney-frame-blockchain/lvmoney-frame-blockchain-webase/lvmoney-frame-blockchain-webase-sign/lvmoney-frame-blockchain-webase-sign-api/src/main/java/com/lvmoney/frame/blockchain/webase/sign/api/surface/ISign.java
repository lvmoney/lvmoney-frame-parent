package com.lvmoney.frame.blockchain.webase.sign.api.surface;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.sign.api.surface
 * 版本信息: 版本1.0
 * 日期:2021/6/24
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.blockchain.webase.sign.api.ao.UserInfoAo;
import com.lvmoney.frame.blockchain.webase.sign.api.constant.SignConstant;
import com.lvmoney.frame.blockchain.webase.sign.api.vo.SignResult;
import com.lvmoney.frame.blockchain.webase.sign.api.vo.UserInfoVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/24 11:09
 */
public interface ISign {
    /**
     * 根据用户编号查询用户信息
     *
     * @param signUserId:
     * @param returnPrivateKey:
     * @throws
     * @return: com.lvmoney.frame.blockchain.webase.sign.api.vo.SignResult<com.lvmoney.frame.blockchain.webase.sign.api.vo.UserInfoVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/24 13:36
     */
    @GetMapping(value = SignConstant.URL_SIGN_USER_USER_INFO)
    SignResult<UserInfoVo> userInfo(@PathVariable("signUserId") String signUserId, @RequestParam("returnPrivateKey") boolean returnPrivateKey);
}
