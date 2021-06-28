package com.lvmoney.frame.blockchain.webase.wallet.api.constant;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.wallet.api.constant
 * 版本信息: 版本1.0
 * 日期:2021/6/24
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/24 19:50
 */
public class WalletConstant {
    /**
     * 交易url
     */
    public static final String URL_FRONT_TRANS_HANDLE_WITH_SIGN = "trans/handleWithSign";
    /**
     * 方法名：总积分
     */
    public static final String CONTRACT_FUNC_COIN_TOTAL_AMOUNT = "totalAmount";

    /**
     * 方法名：余额
     */
    public static final String CONTRACT_FUNC_COIN_BALANCE = "balance";

    /**
     * 方法名：单位数量
     */
    public static final String CONTRACT_FUNC_COIN_MIN_UNIT = "minUnit";


    /**
     * 方法名：简称
     */
    public static final String CONTRACT_FUNC_COIN_SHORT_NAME = "shortName";


    /**
     * 方法名：描述
     */
    public static final String CONTRACT_FUNC_COIN_DESCRIPTION = "description";


    /**
     * 方法名：允许 spender 从自己账户提取限额 value 的积分
     */
    public static final String CONTRACT_FUNC_COIN_APPROVE = "approve";


    /**
     * 方法名：返回 spender 可从 owner 提取的积分数量上限
     */
    public static final String CONTRACT_FUNC_COIN_ALLOWANCE = "allowance";


    /**
     * 方法名：允许 spender 提取的积分上限在原有基础上增加 addedValue
     */
    public static final String CONTRACT_FUNC_COIN_INCREASE_ALLOWANCE = "increaseAllowance";


    /**
     * 方法名：允许 spender 提取的积分上限在原有基础上减少 subtractedValue
     */
    public static final String CONTRACT_FUNC_COIN_DECREASE_ALLOWANCE = "decreaseAllowance";


    /**
     * 方法名：检查 account 是否有增加积分的权限
     */
    public static final String CONTRACT_FUNC_COIN_IS_ISSUER = "isIssuer";


    /**
     * 方法名：有权限的用户新增指定用户积分，同时总积分数同等增加
     */
    public static final String CONTRACT_FUNC_COIN_ISSUE = "issue";


    /**
     * 方法名：有权限的人授予指定公钥地址拥有增加积分的权限
     * 拥有权限后就可以给别人授权
     */
    public static final String CONTRACT_FUNC_COIN_ADD_ISSUER = "addIssuer";


    /**
     * 方法名：移除增加积分的权限
     * <p>
     * 暂未测试通过：非授权用户移除被授权的用户
     */
    public static final String CONTRACT_FUNC_COIN_RENOUNCE_ISSUER = "renounceIssuer";

    /**
     * 方法名：用户id转账到指定公钥地址
     */
    public static final String CONTRACT_FUNC_COIN_SEND = "send";

    /**
     * 方法名：将地址 from 中的 value 数量的积分转入地址 to ，并触发 transfer 事件，data 是转账备注
     */
    public static final String CONTRACT_FUNC_COIN_SEND_FROM = "sendFrom";

    /**
     * 方法名：用户销毁自己的积分，同时总积分数同等减少
     */
    public static final String CONTRACT_FUNC_COIN_DESTROY = "destroy";

    /**
     * 方法名：需要通过approve授权后使用，不能超过授权的积分数量
     */
    public static final String CONTRACT_FUNC_COIN_DESTROY_FROM = "destroyFrom";


    /**
     * 方法名：判断合约是否处于暂停状态
     */
    public static final String CONTRACT_FUNC_COIN_SUSPENDED = "suspended";


    /**
     * 方法名：有权限的用户暂停所有交易。
     * 遇到紧急状况，你可以调用 suspend 方法，暂停合约，这样任何人都不能调用 send 函数。故障修复后，可以调用 unSuspend 方法解除暂停
     */
    public static final String CONTRACT_FUNC_COIN_SUSPEND = "suspend";

    /**
     * 方法名：有权限的用户暂停所有交易释放。
     */
    public static final String CONTRACT_FUNC_COIN_UN_SUSPEND = "unSuspend";


    /**
     * 方法名：是否有暂停合约权限
     */
    public static final String CONTRACT_FUNC_COIN_IS_SUSPENDER = "isSuspender";


    /**
     * 方法名：有权限的用户分配公钥地址有暂停合约功能
     */
    public static final String CONTRACT_FUNC_COIN_ADD_SUSPENDER = "addSuspender";


    /**
     * 方法名：收回公钥地址暂停合约的权限
     */
    public static final String CONTRACT_FUNC_COIN_RENOUNCE_SUSPENDER = "renounceSuspender";


}
