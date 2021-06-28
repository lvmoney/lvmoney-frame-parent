package com.lvmoney.frame.blockchain.webase.wallet.api.surface;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.wallet.api.surface
 * 版本信息: 版本1.0
 * 日期:2021/6/24
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.blockchain.webase.wallet.api.ao.*;
import com.lvmoney.frame.blockchain.webase.wallet.api.constant.WalletConstant;
import com.lvmoney.frame.blockchain.webase.wallet.api.vo.HandleWithSignVo;
import com.lvmoney.frame.prefix.constant.FramePrefixConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @describe：1）积分智能合约接口封装， 2）如果 入参不带groupId和signUserId时，系统会采用默认值
 * 3）默认值通过yml配置传入
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/24 19:50
 */
@RequestMapping(FramePrefixConstant.PLATFORM_BLOCKCHAIN_WEBASE_WALLET)
public interface IWallet {
    /**
     * 积分总数查询
     *
     * @param wallet:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<java.lang.Long>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/26 18:16
     */
    @GetMapping(WalletConstant.CONTRACT_FUNC_COIN_TOTAL_AMOUNT)
    ApiResult<Long> totalAmount(Wallet wallet);


    /**
     * 查询积分最小单位，一般是1
     *
     * @param wallet:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<java.lang.Long>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/26 18:16
     */
    @GetMapping(WalletConstant.CONTRACT_FUNC_COIN_MIN_UNIT)
    ApiResult<Long> minUnit(Wallet wallet);

    /**
     * 查询积分简称
     *
     * @param wallet:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<java.lang.Long>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/26 18:16
     */
    @GetMapping(WalletConstant.CONTRACT_FUNC_COIN_SHORT_NAME)
    ApiResult<String> shortName(Wallet wallet);


    /**
     * 积分描述
     *
     * @param wallet:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<java.lang.Long>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/26 18:16
     */
    @GetMapping(WalletConstant.CONTRACT_FUNC_COIN_DESCRIPTION)
    ApiResult<String> description(Wallet wallet);

    /**
     * 查看账户余额
     *
     * @param balanceAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<java.lang.Long>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/26 18:16
     */
    @PostMapping(WalletConstant.CONTRACT_FUNC_COIN_BALANCE)
    ApiResult<Long> balance(BalanceAo balanceAo);

    /**
     * 用户id转账到指定公钥地址
     *
     * @param sendAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.wallet.api.vo.HandleWithSignVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/28 10:26
     */
    @PostMapping(WalletConstant.CONTRACT_FUNC_COIN_SEND)
    ApiResult<HandleWithSignVo> send(SendAo sendAo);


    /**
     * 需要通过approve授权后使用，不能超过授权的积分数量
     * 将地址 from 中的 value 数量的积分转入地址 to ，并触发 transfer 事件，data 是转账备注。
     * <p>
     * 方法的调用者可以不为 from， 此时需要预先进行 approve 授权
     * <p>
     * from 不能为调用者自身地址，否则会报错
     * <p>
     * suspend 状态下无法执行此操作
     *
     * @param sendFromAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.wallet.api.vo.HandleWithSignVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/28 11:17
     */
    @PostMapping(WalletConstant.CONTRACT_FUNC_COIN_SEND_FROM)
    ApiResult<HandleWithSignVo> sendFrom(SendFromAo sendFromAo);

    /**
     * 允许 spender 从自己账户提取限额 value 的积分
     *
     * @param approveAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.wallet.api.vo.HandleWithSignVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/28 11:22
     */
    @PostMapping(WalletConstant.CONTRACT_FUNC_COIN_APPROVE)
    ApiResult<HandleWithSignVo> approve(ApproveAo approveAo);

    /**
     * 有权限的用户暂停所有交易。
     * 遇到紧急状况，你可以调用 suspend 方法，暂停合约，这样任何人都不能调用 send 函数。故障修复后，可以调用 unSuspend 方法解除暂停
     *
     * @param wallet:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.wallet.api.vo.HandleWithSignVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/28 11:38
     */
    @PostMapping(WalletConstant.CONTRACT_FUNC_COIN_SUSPEND)
    ApiResult<HandleWithSignVo> suspend(Wallet wallet);

    /**
     * 有权限的用户暂停所有交易。释放，交易可继续
     *
     * @param wallet:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.wallet.api.vo.HandleWithSignVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/28 13:38
     */
    @PostMapping(WalletConstant.CONTRACT_FUNC_COIN_UN_SUSPEND)
    ApiResult<HandleWithSignVo> unSuspend(Wallet wallet);

    /**
     * 是否有暂停合约权限
     *
     * @param isSuspenderAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<java.lang.Boolean>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/28 13:45
     */
    @PostMapping(WalletConstant.CONTRACT_FUNC_COIN_IS_SUSPENDER)
    ApiResult<Boolean> isSuspender(IsSuspenderAo isSuspenderAo);

    /**
     * 有权限的用户分配公钥地址有暂停合约功能
     *
     * @param addSuspenderAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.wallet.api.vo.HandleWithSignVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/28 13:50
     */
    @PostMapping(WalletConstant.CONTRACT_FUNC_COIN_ADD_SUSPENDER)
    ApiResult<HandleWithSignVo> addSuspender(AddSuspenderAo addSuspenderAo);

    /**
     * 检查 account 是否有增加积分的权限
     *
     * @param isIssuerAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<java.lang.Boolean>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/28 14:01
     */
    @PostMapping(WalletConstant.CONTRACT_FUNC_COIN_IS_ISSUER)
    ApiResult<Boolean> isIssuer(IsIssuerAo isIssuerAo);


    /**
     * 有权限的用户新增指定用户积分，同时总积分数同等增加
     *
     * @param issueAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.wallet.api.vo.HandleWithSignVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/28 14:02
     */
    @PostMapping(WalletConstant.CONTRACT_FUNC_COIN_ISSUE)
    ApiResult<HandleWithSignVo> issue(IssueAo issueAo);

    /**
     * 有权限的人授予指定公钥地址拥有增加积分的权限
     * 拥有权限后就可以给别人授权
     *
     * @param addIssuerAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.wallet.api.vo.HandleWithSignVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/28 14:03
     */
    @PostMapping(WalletConstant.CONTRACT_FUNC_COIN_ADD_ISSUER)
    ApiResult<HandleWithSignVo> addIssuer(AddIssuerAo addIssuerAo);


    /**
     * 用户销毁自己的积分，同时总积分数同等减少
     *
     * @param destroyAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.wallet.api.vo.HandleWithSignVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/28 14:11
     */
    @PostMapping(WalletConstant.CONTRACT_FUNC_COIN_DESTROY)
    ApiResult<HandleWithSignVo> destroy(DestroyAo destroyAo);

    /**
     * 需要通过approve授权后使用，不能超过授权的积分数量
     *
     * @param destroyFromAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.wallet.api.vo.HandleWithSignVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/28 14:14
     */
    @PostMapping(WalletConstant.CONTRACT_FUNC_COIN_DESTROY_FROM)
    ApiResult<HandleWithSignVo> destroyFrom(DestroyFromAo destroyFromAo);

    /**
     * 返回 spender 可从 owner 提取的积分数量上限
     *
     * @param allowanceAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<java.lang.Long>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/28 14:38
     */
    @GetMapping(WalletConstant.CONTRACT_FUNC_COIN_ALLOWANCE)
    ApiResult<Long> allowance(AllowanceAo allowanceAo);

    /**
     * 允许 spender 提取的积分上限在原有基础上增加 addedValue
     *
     * @param increaseAllowanceAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.wallet.api.vo.HandleWithSignVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/28 14:40
     */
    @PostMapping(WalletConstant.CONTRACT_FUNC_COIN_INCREASE_ALLOWANCE)
    ApiResult<HandleWithSignVo> increaseAllowance(IncreaseAllowanceAo increaseAllowanceAo);

    /**
     * 允许 spender 提取的积分上限在原有基础上减少 subtractedValue
     *
     * @param decreaseAllowanceAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.wallet.api.vo.HandleWithSignVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/28 14:41
     */
    @PostMapping(WalletConstant.CONTRACT_FUNC_COIN_DECREASE_ALLOWANCE)
    ApiResult<HandleWithSignVo> decreaseAllowance(DecreaseAllowanceAo decreaseAllowanceAo);

    /**
     * 判断合约是否处于暂停状态
     *
     * @param wallet:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<java.lang.Boolean>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/28 14:43
     */
    @GetMapping(WalletConstant.CONTRACT_FUNC_COIN_SUSPENDED)
    ApiResult<Boolean> suspended(Wallet wallet);


    /**
     * 收回公钥地址暂停合约的权限
     *
     * @param renounceSuspenderAo:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.wallet.api.vo.HandleWithSignVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/28 14:52
     */
    @PostMapping(WalletConstant.CONTRACT_FUNC_COIN_RENOUNCE_SUSPENDER)
    ApiResult<HandleWithSignVo> renounceSuspender(RenounceSuspenderAo renounceSuspenderAo);

    /**
     * 移除增加积分的权限
     * <p>
     * 暂未测试通过：非授权用户移除被授权的用户
     *
     * @param wallet:
     * @throws
     * @return: com.lvmoney.frame.base.core.api.ApiResult<com.lvmoney.frame.blockchain.webase.wallet.api.vo.HandleWithSignVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/28 15:00
     */
    @PostMapping(WalletConstant.CONTRACT_FUNC_COIN_RENOUNCE_ISSUER)
    ApiResult<HandleWithSignVo> renounceIssuer(Wallet wallet);


}
