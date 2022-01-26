package com.lvmoney.frame.base.core.constant;/**
 * 描述:
 * 包名:com.lvmoney.frame.core.constant
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
    public static final String API_RESULT_DATA_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";

    /**
     * 时间格式
     */
    public static final String COMMON_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认成功的编码
     */
    public static final Integer SUCCESS_DEFAULT_CODE = 20000;
    /**
     * 默认成功的msg
     */
    public static final String SUCCESS_DEFAULT_MSG = "success";
    /**
     * map默认长度
     */
    public static final Integer MAP_DEFAULT_SIZE = 16;

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

    /**
     * 字符编码小写:utf-8
     */
    public static final String CHARACTER_ENCODE_UTF8_LOWER = "utf-8";
    /**
     * 字符编码大写:utf-8
     */
    public static final String CHARACTER_ENCODE_UTF8_UPPER = "UTF-8";
    /**
     * ISO-8859-1
     */
    public static final String CHARACTER_ENCODE_ISO88591 = "ISO-8859-1";
    /**
     * GBK
     */
    public static final String CHARACTER_ENCODE_GBK = "GBK";


    /**
     * 逗号
     */
    public static final String CHARACTER_COMMA = ",";


    /**
     * 星号
     */
    public static final String CHARACTER_ASTERISK = "*";

    /**
     * 连接符下划线
     */
    public static final String CONNECTOR_UNDERLINE = "_";
    /**
     * 反斜杠
     */
    public static final String BACKSLASH = "/";
    /**
     * 斜杠
     */
    public static final String SLASH = "\\";
    /**
     * 小数点
     */
    public static final String DECIMAL_POINT = ".";

    /**
     * replace 小数点
     */
    public static final String REPLACE_DECIMAL_POINT = "\\.";

    /**
     * 大括号左
     */
    public static final String BRACE_LEFT = "{";
    /**
     * 大括号右
     */
    public static final String BRACE_RIGHT = "}";


    /**
     * 括号左
     */
    public static final String BRACKETS_LEFT = "(";
    /**
     * 括号右
     */
    public static final String BRACKETS_RIGHT = ")";


    /**
     * 请求头User-Agent
     */
    public static final String USER_AGENT = "User-Agent";
    /**
     * MSIE
     */
    public static final String MSIE = "MSIE";
    /**
     * TRIDENT
     */
    public static final String TRIDENT = "TRIDENT";
    /**
     * EDGE
     */
    public static final String EDGE = "EDGE";

    /**
     * 字符U
     */
    public static final String CHAR_U_UPPER = "U";

    /**
     * &符号
     */
    public static final String CHAR_AND = "&";


    /**
     * 字符,
     */
    public static final String CHAR_COMMA = ",";
    /**
     * 字符%
     */
    public static final String CHAR_PERCENT = "%";
    /**
     * 字符∞
     */
    public static final String CHAR_INFINITE = "∞";

    /**
     * 字符%
     */
    public static final String COLON = ":";

    /**
     * 字符b
     */
    public static final String CHAR_B_LOWER = "b";

    /**
     * 字符A
     */
    public static final char CHAR_A_UPPER = 'A';
    /**
     * 字符a
     */
    public static final char CHAR_A_LOWER = 'a';

    /**
     * 字符Z
     */
    public static final char CHAR_Z_UPPER = 'Z';
    /**
     * 字符z
     */
    public static final char CHAR_Z_LOWER = 'z';

    /**
     * ECC factory类型
     */
    public static final String EEC_FACTORY_TYPE = "EC";

    /**
     * ECC signature类型
     */
    public static final String EEC_SIGNATURE_TYPE = "SHA256withECDSA";
    /**
     * md5
     */
    public static final String MD5_SIGNATURE_TYPE = "md5";

    /**
     * SHA-256
     */
    public static final String SHA_256_SIGNATURE_TYPE = "SHA-256";

    /**
     * SHA-1
     */
    public static final String SHA_1_SIGNATURE_TYPE = "SHA-1";


    /**
     * HMAC_SHA1
     */
    public static final String HMAC_SHA1_SIGNATURE_TYPE = "HmacSHA1";


    /**
     * api请求签名字段是否转为大写
     */
    public static final boolean API_TO_UPPER = true;
    /**
     * api请求签名字段是否转为ENCODE
     */
    public static final boolean API_URL_ENCODE = true;

    /**
     * 空格占位符
     */
    public static final String PLACEHOLDER_BLANK_SPACE = "\u0020";
    /**
     * 换行符
     */
    public static final String PLACEHOLDER_WARP_SPACE = "\n";
    /**
     * 短横线
     */
    public static final String DASH_LINE = "-";
    /**
     * 分号
     */
    public static final String SEMICOLON = ";";
    /**
     * html 的后缀
     */
    public static final String HTML_SUFFIX = ".html";
    /**
     * 双引号
     */
    public static final String DOUBLE_QUOTATION_MARKS = "\"";


    /**
     * 双引号转义符
     */
    public static final String DOUBLE_QUOTATION_MARKS_ESCAPE = "\\\\\\\"";

    /**
     * 空
     */
    public static final String EMPTY = "";
    /**
     * get请求
     */
    public static final String REQUEST_TYPE_GET = "GET";
    /**
     * post请求
     */
    public static final String REQUEST_TYPE_POST = "POST";

    /**
     * 兆的长度
     */
    public static final Integer OMEN_SIZE = 1024 * 1024;

    /**
     * 毫秒到秒的进制
     */
    public static final Integer LONG_SEC_MS = 1000;


    /**
     * 小时到秒的进制
     */
    public static final Integer LONG_HOUR_SEC = 3600;


    /**
     * 分钟到秒的进制
     */
    public static final Integer LONG_MIN_SEC = 60;

    /**
     * 等于符号
     */
    public static final String CHAR_EQUAL_SIGN = "=";

    /**
     * 单引号
     */
    public static final String SINGLE_QUOTATION_MARK = "'";

}
