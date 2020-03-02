package com.zhy.platform.authentication.oauth2.common.constant;/**
 * 描述:
 * 包名:com.zhy.platform.authentication.oauth2.common.constant
 * 版本信息: 版本1.0
 * 日期:2020/2/18
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/2/18 19:12
 */
public class Oauth2CommonConstant {
    /**
     * oauth2客户端授权验证基础信息redis存储的key值。
     */
    public static final String REDIS_FRAME_USER_DETAILS_NAME = "REDIS_FRAME_USER_DETAILS_NAME";

    /**
     * token granter 拓展前缀，防止重复覆盖
     */
    public static final String TOKEN_GRANTER_SHORT_MSG = "TOKEN_GRANTER_SHORT_MSG";

    /**
     * oauth2访问受保护资源key
     */
    public static final String REDIS_FRAME_OAUTH2_PROTECT_RESOURCE = "REDIS_FRAME_OAUTH2_PROTECT_RESOURCE";


}
