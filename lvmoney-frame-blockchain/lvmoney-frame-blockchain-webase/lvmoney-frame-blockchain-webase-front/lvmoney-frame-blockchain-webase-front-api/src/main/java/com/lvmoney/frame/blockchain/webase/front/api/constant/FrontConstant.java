package com.lvmoney.frame.blockchain.webase.front.api.constant;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.front.api.constant
 * 版本信息: 版本1.0
 * 日期:2021/6/23
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/23 15:03
 */
public class FrontConstant {
    /**
     * 获取密钥url
     */
    public static final String URL_FRONT_PRIVATE_KEY = "privateKey";
    /**
     * 获取交易信息url
     */
    public static final String URL_FRONT_WEB3_TRANSACTION = "{groupId}/web3/transaction/{transHash}";
    /**
     * 获取块url
     */
    public static final String URL_FRONT_WEB3_BLOCK_BY_HASH = "{groupId}/web3/blockByHash/{blockHash}";

    /**
     * 交易url
     */
    public static final String URL_FRONT_TRANS_HANDLE_WITH_SIGN = "trans/handleWithSign";

    /**
     * 解析input or output url
     */
    public static final String URL_FRONT_TOOL_DECODE = "tool/decode";

}
