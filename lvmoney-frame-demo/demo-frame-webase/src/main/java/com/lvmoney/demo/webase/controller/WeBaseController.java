package com.lvmoney.demo.webase.controller;/**
 * 描述:
 * 包名:com.lvmoney.demo.customer.function
 * 版本信息: 版本1.0
 * 日期:2020/3/5
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.demo.webase.feign.ISignClient;
import com.lvmoney.demo.webase.feign.ITransClient;
import com.lvmoney.frame.blockchain.webase.front.api.ao.DecodeAo;
import com.lvmoney.frame.blockchain.webase.front.api.ao.HandleWithSignAo;
import com.lvmoney.frame.blockchain.webase.front.api.ao.PrivateKeyAo;
import com.lvmoney.frame.blockchain.webase.front.api.constant.FrontConstant;
import com.lvmoney.frame.blockchain.webase.front.api.vo.HandleWithSignVo;
import com.lvmoney.frame.blockchain.webase.front.api.vo.Web3BlockByHashVo;
import com.lvmoney.frame.blockchain.webase.front.api.vo.Web3TransactionVo;
import com.lvmoney.demo.webase.feign.IFrontClient;
import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.blockchain.webase.sign.api.ao.UserInfoAo;
import com.lvmoney.frame.blockchain.webase.sign.api.constant.SignConstant;
import com.lvmoney.frame.blockchain.webase.sign.api.vo.SignResult;
import com.lvmoney.frame.blockchain.webase.sign.api.vo.UserInfoVo;
import com.lvmoney.frame.blockchain.webase.trans.ao.CallAo;
import com.lvmoney.frame.blockchain.webase.trans.constant.TransConstant;
import com.lvmoney.frame.blockchain.webase.trans.vo.TransResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/5 15:25
 */
@RestController
public class WeBaseController {
    @Autowired
    IFrontClient iFrontClient;

    @Autowired
    ISignClient iSignClient;

    @Autowired
    ITransClient iTransClient;


    @PostMapping(value = FrontConstant.URL_FRONT_WEB3_TRANSACTION)
    public ApiResult transaction(@PathVariable("groupId") int groupId, @PathVariable("transHash") String transHash) {
        Web3TransactionVo re = iFrontClient.web3Transaction(groupId, transHash);
        return ApiResult.success(re);
    }

    @GetMapping(value = FrontConstant.URL_FRONT_PRIVATE_KEY)
    public ApiResult privateKey(PrivateKeyAo privateKeyAo) {
        Object re = iFrontClient.privateKey(privateKeyAo.getAppId(), privateKeyAo.getSignUserId(), privateKeyAo.getType(), privateKeyAo.getUserName(), privateKeyAo.getReturnPrivateKey());
        return ApiResult.success(re);
    }

    @GetMapping(value = FrontConstant.URL_FRONT_WEB3_BLOCK_BY_HASH)
    public ApiResult blockByHash(@PathVariable("groupId") int groupId, @PathVariable("blockHash") String blockByHash) {
        Web3BlockByHashVo re = iFrontClient.web3blockByHash(groupId, blockByHash);
        return ApiResult.success(re);
    }

    @PostMapping(value = FrontConstant.URL_FRONT_TRANS_HANDLE_WITH_SIGN)
    public ApiResult handleWithSign(@RequestBody HandleWithSignAo handleWithSignAo) {
        HandleWithSignVo re = iFrontClient.handleWithSign(handleWithSignAo);
        return ApiResult.success(re);
    }

    @GetMapping(value = FrontConstant.URL_FRONT_TOOL_DECODE)
    public ApiResult toolDecode(DecodeAo decodeAo) {
        String re = iFrontClient.toolDecode(decodeAo);
        return ApiResult.success(re);
    }


    @GetMapping(value = SignConstant.URL_SIGN_USER_USER_INFO)
    public ApiResult userInfo(@PathVariable("signUserId") String signUserId, UserInfoAo userInfoAo) {
        SignResult<UserInfoVo> re = iSignClient.userInfo(signUserId, userInfoAo.getReturnPrivateKey());
        return ApiResult.success(re.getData());
    }

    @PostMapping(value = TransConstant.URL_TRANS_CALL)
    public ApiResult  transCall(@RequestBody CallAo callAo) {
        TransResult re = iTransClient.transCall(callAo);
        return ApiResult.success(re.getData());
    }
}
