package com.lvmoney.frame.blockchain.webase.wallet.apiserver.controller;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.wallet.apiserver.controller
 * 版本信息: 版本1.0
 * 日期:2021/6/26
 * Copyright XXXXXX科技有限公司
 */


import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.blockchain.webase.wallet.api.ao.*;
import com.lvmoney.frame.blockchain.webase.wallet.api.constant.WalletConstant;
import com.lvmoney.frame.blockchain.webase.wallet.api.surface.IWallet;
import com.lvmoney.frame.blockchain.webase.wallet.api.vo.HandleWithSignVo;
import com.lvmoney.frame.blockchain.webase.wallet.apiserver.feign.ICoinClient;
import com.lvmoney.frame.blockchain.webase.wallet.apiserver.prop.WalletContractProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/26 15:27
 */
@RestController
public class IWalletController implements IWallet {
    /**
     * 积分合约返回值默认取值下标
     */
    private static final Integer DEFAULT_INDEX = 0;
    @Autowired
    ICoinClient iCoinClient;
    @Autowired
    WalletContractProp walletContractProp;

    @Override
    public ApiResult<Long> totalAmount(Wallet wallet) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_TOTAL_AMOUNT);
        handleWithSignAo = getExactValue(wallet, handleWithSignAo);
        List result = iCoinClient.coin(handleWithSignAo);
        return ApiResult.success(result.get(DEFAULT_INDEX));
    }

    @Override
    public ApiResult<Long> minUnit(Wallet wallet) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_MIN_UNIT);
        handleWithSignAo = getExactValue(wallet, handleWithSignAo);
        List result = iCoinClient.coin(handleWithSignAo);
        return ApiResult.success(result.get(DEFAULT_INDEX));
    }

    @Override
    public ApiResult<String> shortName(Wallet wallet) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_SHORT_NAME);
        handleWithSignAo = getExactValue(wallet, handleWithSignAo);
        List result = iCoinClient.coin(handleWithSignAo);
        return ApiResult.success(result.get(DEFAULT_INDEX));
    }

    @Override
    public ApiResult<String> description(Wallet wallet) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_DESCRIPTION);
        handleWithSignAo = getExactValue(wallet, handleWithSignAo);
        List result = iCoinClient.coin(handleWithSignAo);
        return ApiResult.success(result.get(DEFAULT_INDEX));
    }

    @Override
    public ApiResult<Long> balance(BalanceAo balanceAo) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_BALANCE);
        handleWithSignAo.setFuncParam(new ArrayList() {{
            add(balanceAo.getOwner());
        }});
        List result = iCoinClient.coin(handleWithSignAo);
        return ApiResult.success(result.get(DEFAULT_INDEX));
    }

    @Override
    public ApiResult<HandleWithSignVo> send(SendAo sendAo) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_SEND);
        handleWithSignAo.setFuncParam(new ArrayList() {{
            add(sendAo.getTo());
            add(sendAo.getValue());
            add(sendAo.getData());
        }});
        handleWithSignAo = getExactValue(sendAo, handleWithSignAo);
        return ApiResult.success(iCoinClient.handleWithSign(handleWithSignAo));
    }

    @Override
    public ApiResult<HandleWithSignVo> sendFrom(SendFromAo sendFromAo) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_SEND_FROM);
        handleWithSignAo.setFuncParam(new ArrayList() {{
            add(sendFromAo.getFrom());
            add(sendFromAo.getTo());
            add(sendFromAo.getValue());
            add(sendFromAo.getData());
        }});
        handleWithSignAo = getExactValue(sendFromAo, handleWithSignAo);
        return ApiResult.success(iCoinClient.handleWithSign(handleWithSignAo));
    }

    @Override
    public ApiResult<HandleWithSignVo> approve(ApproveAo approveAo) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_APPROVE);
        handleWithSignAo.setFuncParam(new ArrayList() {{
            add(approveAo.getApender());
            add(approveAo.getValue());
        }});
        handleWithSignAo = getExactValue(approveAo, handleWithSignAo);
        return ApiResult.success(iCoinClient.handleWithSign(handleWithSignAo));
    }

    @Override
    public ApiResult<HandleWithSignVo> suspend(Wallet wallet) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_SUSPEND);
        handleWithSignAo = getExactValue(wallet, handleWithSignAo);
        return ApiResult.success(iCoinClient.handleWithSign(handleWithSignAo));
    }

    @Override
    public ApiResult<HandleWithSignVo> unSuspend(Wallet wallet) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_UN_SUSPEND);
        handleWithSignAo = getExactValue(wallet, handleWithSignAo);
        return ApiResult.success(iCoinClient.handleWithSign(handleWithSignAo));
    }

    @Override
    public ApiResult<Boolean> isSuspender(IsSuspenderAo isSuspenderAo) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_IS_SUSPENDER);
        handleWithSignAo.setFuncParam(new ArrayList() {{
            add(isSuspenderAo.getAddress());
        }});
        handleWithSignAo = getExactValue(isSuspenderAo, handleWithSignAo);
        return ApiResult.success(iCoinClient.coin(handleWithSignAo).get(DEFAULT_INDEX));
    }

    @Override
    public ApiResult<HandleWithSignVo> addSuspender(AddSuspenderAo addSuspenderAo) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_ADD_SUSPENDER);
        handleWithSignAo.setFuncParam(new ArrayList() {{
            add(addSuspenderAo.getAddress());
        }});
        handleWithSignAo = getExactValue(addSuspenderAo, handleWithSignAo);
        return ApiResult.success(iCoinClient.handleWithSign(handleWithSignAo));
    }

    @Override
    public ApiResult<Boolean> isIssuer(IsIssuerAo isIssuerAo) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_IS_ISSUER);
        handleWithSignAo.setFuncParam(new ArrayList() {{
            add(isIssuerAo.getAddress());
        }});
        handleWithSignAo = getExactValue(isIssuerAo, handleWithSignAo);
        return ApiResult.success(iCoinClient.coin(handleWithSignAo).get(DEFAULT_INDEX));
    }

    @Override
    public ApiResult<HandleWithSignVo> issue(IssueAo issueAo) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_ISSUE);
        handleWithSignAo.setFuncParam(new ArrayList() {{
            add(issueAo.getTo());
            add(issueAo.getValue());
            add(issueAo.getData());
        }});
        handleWithSignAo = getExactValue(issueAo, handleWithSignAo);
        return ApiResult.success(iCoinClient.handleWithSign(handleWithSignAo));
    }

    @Override
    public ApiResult<HandleWithSignVo> addIssuer(AddIssuerAo addIssuerAo) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_ADD_ISSUER);
        handleWithSignAo.setFuncParam(new ArrayList() {{
            add(addIssuerAo.getAddress());
        }});
        handleWithSignAo = getExactValue(addIssuerAo, handleWithSignAo);
        return ApiResult.success(iCoinClient.handleWithSign(handleWithSignAo));
    }

    @Override
    public ApiResult<HandleWithSignVo> destroy(DestroyAo destroyAo) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_DESTROY);
        handleWithSignAo.setFuncParam(new ArrayList() {{
            add(destroyAo.getValue());
            add(destroyAo.getData());
        }});
        handleWithSignAo = getExactValue(destroyAo, handleWithSignAo);
        return ApiResult.success(iCoinClient.handleWithSign(handleWithSignAo));
    }

    @Override
    public ApiResult<HandleWithSignVo> destroyFrom(DestroyFromAo destroyFromAo) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_DESTROY_FROM);
        handleWithSignAo.setFuncParam(new ArrayList() {{
            add(destroyFromAo.getFrom());
            add(destroyFromAo.getValue());
            add(destroyFromAo.getData());
        }});
        handleWithSignAo = getExactValue(destroyFromAo, handleWithSignAo);
        return ApiResult.success(iCoinClient.handleWithSign(handleWithSignAo));
    }

    @Override
    public ApiResult<Long> allowance(AllowanceAo allowanceAo) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_ALLOWANCE);
        handleWithSignAo.setFuncParam(new ArrayList() {{
            add(allowanceAo.getOwner());
            add(allowanceAo.getSpender());
        }});
        handleWithSignAo = getExactValue(allowanceAo, handleWithSignAo);
        return ApiResult.success(iCoinClient.coin(handleWithSignAo).get(DEFAULT_INDEX));
    }

    @Override
    public ApiResult<HandleWithSignVo> increaseAllowance(IncreaseAllowanceAo increaseAllowanceAo) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_INCREASE_ALLOWANCE);
        handleWithSignAo.setFuncParam(new ArrayList() {{
            add(increaseAllowanceAo.getSpender());
            add(increaseAllowanceAo.getAddedValue());
        }});
        handleWithSignAo = getExactValue(increaseAllowanceAo, handleWithSignAo);
        return ApiResult.success(iCoinClient.handleWithSign(handleWithSignAo));
    }

    @Override
    public ApiResult<HandleWithSignVo> decreaseAllowance(DecreaseAllowanceAo decreaseAllowanceAo) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_DECREASE_ALLOWANCE);
        handleWithSignAo.setFuncParam(new ArrayList() {{
            add(decreaseAllowanceAo.getSpender());
            add(decreaseAllowanceAo.getSubtractedValue());
        }});
        handleWithSignAo = getExactValue(decreaseAllowanceAo, handleWithSignAo);
        return ApiResult.success(iCoinClient.handleWithSign(handleWithSignAo));
    }

    @Override
    public ApiResult<Boolean> suspended(Wallet wallet) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_SUSPENDED);
        handleWithSignAo = getExactValue(wallet, handleWithSignAo);
        return ApiResult.success(iCoinClient.coin(handleWithSignAo).get(DEFAULT_INDEX));
    }

    @Override
    public ApiResult<HandleWithSignVo> renounceSuspender(RenounceSuspenderAo renounceSuspenderAo) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_RENOUNCE_SUSPENDER);
        handleWithSignAo.setFuncParam(new ArrayList() {{
            add(renounceSuspenderAo.getAddress());
        }});
        handleWithSignAo = getExactValue(renounceSuspenderAo, handleWithSignAo);
        return ApiResult.success(iCoinClient.handleWithSign(handleWithSignAo));
    }

    @Override
    public ApiResult<HandleWithSignVo> renounceIssuer(Wallet wallet) {
        HandleWithSignAo handleWithSignAo = initHandleWithSign(WalletConstant.CONTRACT_FUNC_COIN_RENOUNCE_ISSUER);
        handleWithSignAo = getExactValue(wallet, handleWithSignAo);
        return ApiResult.success(iCoinClient.handleWithSign(handleWithSignAo));
    }

    /**
     * 共用的一个合约，对合约地址和内容进行通用初始化,指定合约的方法
     *
     * @param funcName:
     * @throws
     * @return: com.lvmoney.frame.blockchain.webase.front.api.ao.HandleWithSignAo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/26 17:27
     */
    private HandleWithSignAo initHandleWithSign(String funcName) {
        HandleWithSignAo handleWithSignAo = new HandleWithSignAo();
        handleWithSignAo.setContractAddress(walletContractProp.getContractAddress());
        handleWithSignAo.setContractAbi(JSON.parseArray(walletContractProp.getContractAbi()));
        handleWithSignAo.setFuncName(funcName);
        handleWithSignAo.setGroupId(walletContractProp.getDefaultGroupId());
        handleWithSignAo.setSignUserId(walletContractProp.getDefaultSignUserId());
        handleWithSignAo.setUseCns(walletContractProp.isDefaultUseCns());
        handleWithSignAo.setFuncParam(new ArrayList());
        return handleWithSignAo;
    }

    /**
     * 入参为空使用默认值
     *
     * @param object:
     * @param handleWithSignAo:
     * @throws
     * @return: com.lvmoney.frame.blockchain.webase.wallet.api.ao.HandleWithSignAo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/28 10:06
     */
    private HandleWithSignAo getExactValue(Object object, HandleWithSignAo handleWithSignAo) {
        if (object instanceof Wallet) {
            Wallet wallet = (Wallet) object;
            if (ObjectUtil.isNotEmpty(wallet.getGroupId())) {
                handleWithSignAo.setGroupId(wallet.getGroupId());
            }
            if (ObjectUtil.isNotEmpty(wallet.getGroupId())) {
                handleWithSignAo.setSignUserId(wallet.getSignUserId());
            }

            if (ObjectUtil.isNotEmpty(wallet.getUseCns())) {
                handleWithSignAo.setUseCns(wallet.getUseCns());
            }
            handleWithSignAo.setVersion(wallet.getVersion());
            handleWithSignAo.setCnsName(wallet.getCnsName());

        }
        return handleWithSignAo;
    }
}
