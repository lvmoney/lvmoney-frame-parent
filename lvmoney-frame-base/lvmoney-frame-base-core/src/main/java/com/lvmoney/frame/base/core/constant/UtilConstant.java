package com.lvmoney.frame.base.core.constant;/**
 * 描述:
 * 包名:com.lvmoney.platform.prefix.constant
 * 版本信息: 版本1.0
 * 日期:2021/9/8
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2021/9/8 19:20
 */
public class UtilConstant {
    /**
     * 替换表达式需要替换
     */
    public static final String EXPRESSION_PRIVACY = "$1%s$2";

    /**
     * 加密密钥混淆码
     */
    public static final String PRIVACY_CONFUSION_CODE = "<!$@=";

    /**
     * 盐值混淆码
     */
    public static final String SALT_CONFUSION_CODE = "^!@#%";

    /**
     * 显示身份证号前6位
     */
    public static final Integer PRIVACY_ID_CARD_START = 6;
    /**
     * 显示身份证号后四位
     */
    public static final Integer PRIVACY_ID_CARD_END = 4;
    /**
     * 显示银行卡前四位
     */
    public static final Integer PRIVACY_BANK_CARD_START = 4;
    /**
     * 显示银行卡后四位
     */
    public static final Integer PRIVACY_BANK_CARD_END = 4;
    /**
     * 显示电话前三位
     */
    public static final Integer PRIVACY_TELEPHONE_CARD_START = 3;
    /**
     * 显示电话后四位
     */
    public static final Integer PRIVACY_TELEPHONE_CARD_END = 4;



    /**
     * 显示路运输经营许可证前三位
     */
    public static final Integer PRIVACY_OPERATION_LICENSE_START = 3;
    /**
     * 显示路运输经营许可证后三位
     */
    public static final Integer PRIVACY_OPERATION_LICENSE_END = 3;


    /**
     * 显示道路运输证前三位
     */
    public static final Integer PRIVACY_ROAD_TRANSPORT_CODE_START = 3;
    /**
     * 显示路道路运输证后三位
     */
    public static final Integer PRIVACY_ROAD_TRANSPORT_CODE_END = 3;


    /**
     * 显示税务登记证号前三位
     */
    public static final Integer PRIVACY_TAX_REGISTRATION_NO_START = 3;
    /**
     * 显示税务登记证号后三位
     */
    public static final Integer PRIVACY_TAX_REGISTRATION_NO_END = 3;


    /**
     * 显示社会信用代码前三位
     */
    public static final Integer PRIVACY_SOCIAL_CREDIT_CODE_START = 3;
    /**
     * 显示社会信用代码后三位
     */
    public static final Integer PRIVACY_SOCIAL_CREDIT_CODE_END = 3;


    /**
     * 显示档案号/车辆识别代码前2位
     */
    public static final Integer PRIVACY_FILE_NUMBER_START = 2;
    /**
     * 显示档案号/车辆识别代码后2位
     */
    public static final Integer PRIVACY_FILE_NUMBER_END = 2;



    /**
     * 显示车牌号前2位
     */
    public static final Integer PRIVACY_LICENSE_PLATE_START = 2;
    /**
     * 显示车牌号后2位
     */
    public static final Integer PRIVACY_LICENSE_PLATE_END = 2;


    /**
     * 显示邮箱前3位
     */
    public static final Integer PRIVACY_EMAIL_START = 3;
    /**
     * 显示车邮箱后2位
     */
    public static final Integer PRIVACY_EMAIL_END = 2;

    /**
     * 正则表达式占位符需要替换
     */
    public static final String EXPRESSION_REG_EX = "(\\d{%s})\\d{%s}(\\d{%s})";




}
