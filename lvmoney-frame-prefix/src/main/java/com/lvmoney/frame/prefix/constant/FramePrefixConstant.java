package com.lvmoney.frame.prefix.constant;/**
 * 描述:
 * 包名:com.lvmoney.frame.prefix.constant
 * 版本信息: 版本1.0
 * 日期:2021/6/26
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/26 15:33
 */
public class FramePrefixConstant {
    /**
     * 框架统一访问 rest request url统一前缀
     */
    private static final String FRAME_PREFIX = "/frame";
    /**
     * 区块链 webase模块 前缀
     */
    private static final String BLOCK_CHAIN_WEBASE_PREFIX = FRAME_PREFIX + "/webase";
    /**
     * 网关调用用户中台权限校验接口前缀
     */
    public static final String FRAME_AUTHENTICATION_PREFIX = FRAME_PREFIX + "/user/authentication";
    /**
     * webase 钱包 前缀
     */
    public static final String PLATFORM_BLOCKCHAIN_WEBASE_WALLET = BLOCK_CHAIN_WEBASE_PREFIX + "/wallet";
    /**
     * webase weidentity 前缀
     */
    public static final String PLATFORM_BLOCKCHAIN_WEBASE_WE_IDENTITY = BLOCK_CHAIN_WEBASE_PREFIX + "/weidentity";

}
