package com.lvmoney.frame.authentication.shiro.constant;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/2/22
 * Copyright 四川******科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class ShiroConstant {
    /**
     * 系统资源redis key
     */
    public static final String SYS_SHIRO_RES = "sysShiroRes";

    /**
     * 系统资源redis key
     */
    public static final String USER_SHIRO_RES = "userShiroRes";

    /**
     * 系统资源用户权限redis key
     */
    public static final String SYS_SHIRO_URI = "sysShiroUri";

    /**
     * 配置文件中请求地址忽略标识
     */
    public static final String SHIRO_REQUEST_IGNORE = "anon";

    /**
     * 基于多服务考虑，功能地址需要做服务区分的前缀
     */
    public static final String SHIRO_URL_PREFIX = "SERVER:";


    public static final String SERVLET_END_WITH = "/";


}
