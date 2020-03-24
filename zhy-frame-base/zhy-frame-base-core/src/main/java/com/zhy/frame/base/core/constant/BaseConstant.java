package com.zhy.frame.base.core.constant;/**
 * 描述:
 * 包名:com.zhy.frame.core.constant
 * 版本信息: 版本1.0
 * 日期:2019/11/19
 * Copyright 四川******科技有限公司
 */


import java.io.File;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/11/19 16:38
 */
public class BaseConstant {
    /**
     * 返回值时间格式
     */
    public static final String API_RESULT_DATA_DATE_FORMART = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认成功的编码
     */
    public static final Integer SUCCESS_DEFAULT_CODE = 20000;
    /**
     * map默认长度
     */
    public static final Integer MAP_DEFAULT_SIZE = 14;

    /**
     * localhost 对应的ip
     */
    public static final String LOCALHOST_IP = "127.0.0.1";

    /**
     * unknown的请求结果
     */
    public static final String IP_UNKNOWN = "unknown";

    /**
     * saas服务组rediskey
     */
    public static final String REDIS_SERVER_CROUP_KEY = "SAAS_SERVER";

    /**
     * 文件分隔符
     */
    public static final String FILE_SEPARATOR = File.separator;
    /**
     * 支持 false
     */
    public static final String SUPPORT_TRUE = "true";
    /**
     * 支持 false
     */
    public static final String SUPPORT_FALSE = "false";
    /**
     * 支持 false
     */
    public static final boolean SUPPORT_TRUE_BOOL = true;
    /**
     * 支持 false
     */
    public static final boolean SUPPORT_FALSE_BOOL = false;
    /**
     * 权限 header token 的key
     */
    public static final String AUTHORIZATION_TOKEN_KEY = "token";
    /**
     * 虚拟路径后缀
     */
    public static final String WEBSITE_SUFFIX = ".com";

    /**
     * 虚拟路径前缀
     */
    public static final String WEBSITE_PREFIX = "www.";


    /**
     * nacos 环境以lb开头
     */
    public static final String NACOS_PREFIX = "lb://";
}
