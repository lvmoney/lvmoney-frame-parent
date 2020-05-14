package com.zhy.frame.base.profile.constant;/**
 * 描述:
 * 包名:com.zhy.frame.base.profile.constant
 * 版本信息: 版本1.0
 * 日期:2020/5/2
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/2 18:07
 */
public class ProfileConstant {
    /**
     * 加密算法
     */
    public static final String ALGORITHM = "PBEWithMD5AndDES";
    /**
     * pool size
     */
    public static final String POOL_SIZE = "1";
    /**
     * ProviderName
     */
    public static final String PROVIDER_NAME = "SunJCE";
    /**
     * SaltGeneratorClassName
     */
    public static final String SALT_GENERATOR_CLASS_NAME = "org.jasypt.salt.RandomSaltGenerator";
    /**
     * StringOutputType
     */
    public static final String STRING_OUTPUT_TYPE = "base64";
    /**
     * KeyObtentionIterations
     */
    public static final String KEY_OBTENTION_ITERATIONS = "1000";
    /**
     * 默认密码。encryptPassword("199672", "lvmoney")
     */
    public static final String DEFAULT_PASSWORD = "HGho9/nfr23AlRt3pc8KGQ==";
    /**
     * 密码的零时默认值
     */
    public static final String DEFAULT_TEMP = "default";
}
