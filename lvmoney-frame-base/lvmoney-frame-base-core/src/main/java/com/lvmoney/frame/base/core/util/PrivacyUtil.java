package com.lvmoney.frame.base.core.util;/**
 * 描述:
 * 包名:com.lvmoney.platform.common.util
 * 版本信息: 版本1.0
 * 日期:2021/8/26
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.base.core.constant.UtilConstant;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;

import static com.lvmoney.frame.base.core.constant.UtilConstant.*;


/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2021/8/26 20:13
 */
public class PrivacyUtil {
    /**
     * 默认的隐藏字符
     */
    private static final String DEFAULT_REPLACE_CHARACTER = "*";
    /**
     * 邮箱识别符号
     */
    private static final String DEFAULT_EMAIL_CHARACTER = "@";

    /**
     * 隐藏真实的电话号
     *
     * @param telephone:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/8/26 21:01
     */
    public static String privacyTelephone(String telephone) {
        return showStartAndEnd(telephone, PRIVACY_TELEPHONE_CARD_START, PRIVACY_TELEPHONE_CARD_END);
    }

    /**
     * 隐藏真实的道路运输经营许可证
     *
     * @param operationLicense:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/8/26 21:01
     */
    public static String privacyOperationLicense(String operationLicense) {
        return showStartAndEnd(operationLicense, PRIVACY_OPERATION_LICENSE_START, PRIVACY_OPERATION_LICENSE_END);
    }


    /**
     * 隐藏真实的道路运输证
     *
     * @param roadTransportCode:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/8/26 21:01
     */
    public static String privacyRoadTransportCode(String roadTransportCode) {
        return showStartAndEnd(roadTransportCode, PRIVACY_ROAD_TRANSPORT_CODE_START, PRIVACY_ROAD_TRANSPORT_CODE_END);
    }


    /**
     * 隐藏真实的税务登记证号
     *
     * @param taxRegistrationNo:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/8/26 21:01
     */
    public static String privacyTaxRegistrationNo(String taxRegistrationNo) {
        return showStartAndEnd(taxRegistrationNo, PRIVACY_TAX_REGISTRATION_NO_START, PRIVACY_TAX_REGISTRATION_NO_END);
    }


    /**
     * 隐藏真实的社会信用代码
     *
     * @param socialCreditCode:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/8/26 21:01
     */
    public static String privacySocialCreditCode(String socialCreditCode) {
        return showStartAndEnd(socialCreditCode, PRIVACY_SOCIAL_CREDIT_CODE_START, PRIVACY_SOCIAL_CREDIT_CODE_END);
    }


    /**
     * 隐藏真实的档案号/车辆识别代码
     *
     * @param fileNumber:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/8/26 21:01
     */
    public static String privacyFileNumber(String fileNumber) {
        return showStartAndEnd(fileNumber, PRIVACY_FILE_NUMBER_START, PRIVACY_FILE_NUMBER_END);
    }


    /**
     * 隐藏真实的车牌号码
     *
     * @param licensePlate:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/8/26 21:01
     */
    public static String privacyLicensePlate(String licensePlate) {
        return showStartAndEnd(licensePlate, PRIVACY_LICENSE_PLATE_START, PRIVACY_LICENSE_PLATE_END);
    }


    /**
     * 隐藏真实的邮箱地址
     *
     * @param email:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/8/26 21:01
     */
    public static String privacyEmail(String email) {
        String eBefore = email.substring(0, email.indexOf(DEFAULT_EMAIL_CHARACTER));
        String eAfter = email.substring(email.indexOf(DEFAULT_EMAIL_CHARACTER));
        String temp = showStartAndEnd(eBefore, PRIVACY_EMAIL_START, PRIVACY_EMAIL_END);
        return temp + eAfter;
    }

    /**
     * 隐藏真实的身份号
     *
     * @param idCard:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/8/26 21:02
     */
    public static String privacyIdCard(String idCard) {
        return showStartAndEnd(idCard, PRIVACY_ID_CARD_START, PRIVACY_ID_CARD_END);

    }

    /**
     * 隐藏真实的银行卡号
     *
     * @param bankCard:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/8/26 21:02
     */
    public static String privacyBankCard(String bankCard) {
        return showStartAndEnd(bankCard, PRIVACY_TELEPHONE_CARD_START, PRIVACY_TELEPHONE_CARD_END);

    }

    /**
     * 加密 ，默认会加上掩码
     * insert into test select hex(AES_ENCRYPT(('121212'),'1212<!$@='));
     * <p>
     * SELECT * FROM test AS a
     * WHERE a.pass = hex(AES_ENCRYPT('121212','1212<!$@='));
     *
     * @param res:
     * @param password:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/8/27 10:51
     */
    public static String privacyEncryption(String res, String password) {
        return AesUtil.encrypt(res, password + UtilConstant.PRIVACY_CONFUSION_CODE).toUpperCase();
    }

    /**
     * 解密 ，默认会加上掩码
     *
     * @param res:
     * @param password:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/8/27 11:47
     */
    public static String privacyDecrypt(String res, String password) {
        return AesUtil.decrypt(res, password + UtilConstant.PRIVACY_CONFUSION_CODE);
    }


    public static void main(String[] args) {
        String s = privacyEncryption("510802199002110914", "adcd");
        String res = privacyDecrypt(s, "adcd");

        String s1 = privacyIdCard("510802199002110914");
        System.out.println(s);
        System.out.println(res);
        System.out.println(s1);
    }

    /**
     * 线上字符指定长度的前几位和后几位
     * 如果字符长度小于前几位和后几位的和则全部隐藏
     *
     * @param str:
     * @param start:
     * @param end:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/12/9 14:52
     */
    public static String showStartAndEnd(String str, int start, int end) {
        int length = str.length();
        if (length <= start + end) {
            return String.join(BaseConstant.EMPTY, Collections.nCopies(length, DEFAULT_REPLACE_CHARACTER));
        }

        String right = StringUtils.right(str, end);
        String leftPad = StringUtils.leftPad(right, StringUtils.length(str), DEFAULT_REPLACE_CHARACTER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < start; i++) {
            sb.append(DEFAULT_REPLACE_CHARACTER);
        }
        String removeStart = StringUtils.removeStart(leftPad, sb.toString());
        String result = StringUtils.left(str, start).concat(removeStart);
        return result;
    }

    /**
     * 获得指定长度的字符
     *
     * @param character:
     * @param length:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/8/26 21:04
     */
    public static String getSpecifyLengthCharacter(String character, int length) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < length; i++) {
            result.append(character);
        }
        return result.toString();
    }


}
