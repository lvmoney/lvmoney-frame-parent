package com.zhy.frame.route.gateway.constant;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway
 * 版本信息: 版本1.0
 * 日期:2019/8/8
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/8 17:39
 */
public class GatewayConstant {
    /**
     * oauth2不需要权限校验redis key
     */
    public static final String GATEWAY_OAUTH2_FILTER_NMAE = "GATEWAY_OAUTH2_FILTER_NMAE";
    /**
     * shiro不需要权限校验redis key
     */
    public static final String GATEWAY_SHIRO_FILTER_NMAE = "GATEWAY_SHIRO_FILTER_NMAE";

    /**
     * 是否引入gateway的配置文件值
     */
    public static final String FRAME_GATEWAY_SUPPORT_FALSE = "false";
    /**
     * 是否引入gateway的配置文件值
     */
    public static final String FRAME_GATEWAY_SUPPORT_TRUE = "true";

    /**
     * 配置文件中请求地址忽略标识
     */
    public static final String GATEWAY_REQUEST_IGNORE = "ign";

    /**
     * 路由规则redis key
     */
    public static final String REDIS_GATEWAY_ROUTE_KEY = "GATEWAY_ROUTES";
    /**
     * 截取替换级数
     */
    public static final String ROUTE_DEFINITION_FILTER = "StripPrefix";

    /**
     * 白名单ｒｅｄｉｓ　ｋｅｙ
     */
    public static final String SERVER_WHILTE_LIST = "SERVER_WHILTE_LIST";


    public static final String LOCALHOST_IP = "127.0.0.1";


    /**
     * 是否引入白名单的配置文件值
     */
    public static final String FRAME_WHITE_SUPPORT_TRUE = "true";

    /**
     * 是否引入白名单的配置文件值
     */
    public static final String FRAME_WHITE_SUPPORT_FALSE = "false";


    /**
     * 是否鉴权 true
     */
    public static final String FRAME_AUTH_SUPPORT_TRUE = "true";

    /**
     * 是否鉴权 false
     */
    public static final String FRAME_AUTH_SUPPORT_FALSE = "false";
}
