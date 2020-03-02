package com.zhy.frame.authentication.oauth2.center.utils;

import org.apache.commons.lang3.StringUtils;


/**
 * @describe： 检测密码强度
 * <p>
 * refer
 * https://github.com/venshine/PasswordUtil
 * <p>
 * <p>
 * Level（级别）
 * 0-3 : [easy]
 * 4-6 : [medium]
 * 7-9 : [strong]
 * 10-12 : [very strong]
 * >12 : [extremely strong]
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class PasswordUtil {
    private static final int PASSWROD_LENGTH_1 = 1;
    private static final int PASSWROD_LENGTH_2 = 2;
    private static final int PASSWROD_LENGTH_3 = 3;
    private static final int PASSWROD_LENGTH_4 = 4;
    private static final int PASSWROD_LENGTH_5 = 5;
    private static final int PASSWROD_LENGTH_6 = 6;
    private static final int PASSWROD_LENGTH_7 = 7;
    private static final int PASSWROD_LENGTH_8 = 8;
    private static final int PASSWROD_LENGTH_9 = 9;
    private static final int PASSWROD_LENGTH_10 = 10;
    private static final int PASSWROD_LENGTH_12 = 12;
    private static final int PASSWROD_LENGTH_16 = 16;

    private static final int ASCII_NUM_MIN = 48;
    private static final int ASCII_NUM_MAX = 57;
    private static final int CAPITAL_LETTER_MAX = 90;
    private static final int CAPITAL_LETTER_MIN = 65;

    private static final int SMALL_LETTER_MIN = 97;
    private static final int SMALL_LETTER_MAX = 122;

    private static final char EMPTY_CHAR = ' ';

    public enum LEVEL {
        /**
         * 简单
         */
        EASY,
        /**
         * 中
         */
        MEDIUM,
        /**
         * 强
         */
        STRONG,
        /**
         * 很强
         */
        VERY_STRONG,
        /**
         * 超强
         */
        EXTREMELY_STRONG
    }

    private static final int NUM = 1;
    private static final int SMALL_LETTER = 2;
    private static final int CAPITAL_LETTER = 3;
    private static final int OTHER_CHAR = 4;

    /**
     * Simple password dictionary
     */
    private static final String[] DICTIONARY = {"password", "123456", "abc123", "iloveyou", "adobe123", "123123", "sunshine",
            "1314520", "a1b2c3", "123qwe", "aaa111", "qweasd", "admin", "passwd", "abcdefghijklmnopqrstuvwxyz"};

    /**
     *@describe: Check character's type, includes num, capital letter, small letter and other character.
     *@param: [c]
     *@return: int
     *@author: lvmoney /XXXXXX科技有限公司
     *2019/9/9 18:18
     */
    private static int checkCharacterType(char c) {
        if (c >= ASCII_NUM_MIN && c <= ASCII_NUM_MAX) {
            return NUM;
        }
        if (c >= CAPITAL_LETTER_MIN && c <= CAPITAL_LETTER_MAX) {
            return CAPITAL_LETTER;
        }
        if (c >= SMALL_LETTER_MIN && c <= SMALL_LETTER_MAX) {
            return SMALL_LETTER;
        }
        return OTHER_CHAR;
    }

    /**
     *@describe: Count password's number by different type
     *@param: [passwd, type]
     *@return: int
     *@author: lvmoney /XXXXXX科技有限公司
     *2019/9/9 18:15
     */
    private static int countLetter(String passwd, int type) {
        int count = 0;
        if (null != passwd && passwd.length() > 0) {
            for (char c : passwd.toCharArray()) {
                if (checkCharacterType(c) == type) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * @describe: Check password's strength
     * @param: [passwd]
     * @return: int
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 16:18
     */
    public static int check(String passwd) {
        if (StringUtils.isBlank(passwd)) {
            throw new IllegalArgumentException("password is empty");
        }
        passwd = StringUtils.trimToEmpty(passwd);
        int len = passwd.length();
        int level = 0;
        if (len >= PASSWROD_LENGTH_6 || countLetter(passwd, NUM) > 0 || countLetter(passwd, SMALL_LETTER) > 0) {
            level++;
        }
        if (len > PASSWROD_LENGTH_4 && countLetter(passwd, CAPITAL_LETTER) > 0) {
            level++;
        }
        if (len > PASSWROD_LENGTH_6 && countLetter(passwd, OTHER_CHAR) > 0) {
            level++;
        }

        if (len > PASSWROD_LENGTH_4 && countLetter(passwd, NUM) > 0 && countLetter(passwd, SMALL_LETTER) > 0 || countLetter(passwd, NUM) > 0 && countLetter(passwd, CAPITAL_LETTER) > 0
                || countLetter(passwd, NUM) > 0 && countLetter(passwd, OTHER_CHAR) > 0
                || countLetter(passwd, SMALL_LETTER) > 0 && countLetter(passwd, CAPITAL_LETTER) > 0
                || countLetter(passwd, SMALL_LETTER) > 0 && countLetter(passwd, OTHER_CHAR) > 0
                || countLetter(passwd, CAPITAL_LETTER) > 0 && countLetter(passwd, OTHER_CHAR) > 0) {
            level++;
        }

        if (len > PASSWROD_LENGTH_6 && countLetter(passwd, NUM) > 0 && countLetter(passwd, SMALL_LETTER) > 0
                && countLetter(passwd, CAPITAL_LETTER) > 0 || countLetter(passwd, NUM) > 0
                && countLetter(passwd, SMALL_LETTER) > 0 && countLetter(passwd, OTHER_CHAR) > 0
                || countLetter(passwd, NUM) > 0 && countLetter(passwd, CAPITAL_LETTER) > 0
                && countLetter(passwd, OTHER_CHAR) > 0 || countLetter(passwd, SMALL_LETTER) > 0
                && countLetter(passwd, CAPITAL_LETTER) > 0 && countLetter(passwd, OTHER_CHAR) > 0) {
            level++;
        }

        if (len > PASSWROD_LENGTH_8 && countLetter(passwd, NUM) > 0 && countLetter(passwd, SMALL_LETTER) > 0
                && countLetter(passwd, CAPITAL_LETTER) > 0 && countLetter(passwd, OTHER_CHAR) > 0) {
            level++;
        }

        if (len > PASSWROD_LENGTH_6 && countLetter(passwd, NUM) >= PASSWROD_LENGTH_3 && countLetter(passwd, SMALL_LETTER) >= PASSWROD_LENGTH_3
                || countLetter(passwd, NUM) >= PASSWROD_LENGTH_3 && countLetter(passwd, CAPITAL_LETTER) >= PASSWROD_LENGTH_3
                || countLetter(passwd, NUM) >= PASSWROD_LENGTH_3 && countLetter(passwd, OTHER_CHAR) >= PASSWROD_LENGTH_2
                || countLetter(passwd, SMALL_LETTER) >= PASSWROD_LENGTH_3 && countLetter(passwd, CAPITAL_LETTER) >= PASSWROD_LENGTH_3
                || countLetter(passwd, SMALL_LETTER) >= PASSWROD_LENGTH_3 && countLetter(passwd, OTHER_CHAR) >= PASSWROD_LENGTH_2
                || countLetter(passwd, CAPITAL_LETTER) >= PASSWROD_LENGTH_3 && countLetter(passwd, OTHER_CHAR) >= PASSWROD_LENGTH_2) {
            level++;
        }

        if (len > PASSWROD_LENGTH_8 && countLetter(passwd, NUM) >= PASSWROD_LENGTH_2 && countLetter(passwd, SMALL_LETTER) >= PASSWROD_LENGTH_2
                && countLetter(passwd, CAPITAL_LETTER) >= PASSWROD_LENGTH_2 || countLetter(passwd, NUM) >= PASSWROD_LENGTH_2
                && countLetter(passwd, SMALL_LETTER) >= PASSWROD_LENGTH_2 && countLetter(passwd, OTHER_CHAR) >= PASSWROD_LENGTH_2
                || countLetter(passwd, NUM) >= PASSWROD_LENGTH_2 && countLetter(passwd, CAPITAL_LETTER) >= PASSWROD_LENGTH_2
                && countLetter(passwd, OTHER_CHAR) >= PASSWROD_LENGTH_2 || countLetter(passwd, SMALL_LETTER) >= PASSWROD_LENGTH_2
                && countLetter(passwd, CAPITAL_LETTER) >= PASSWROD_LENGTH_2 && countLetter(passwd, OTHER_CHAR) >= PASSWROD_LENGTH_2) {
            level++;
        }

        if (len > PASSWROD_LENGTH_10 && countLetter(passwd, NUM) >= PASSWROD_LENGTH_2 && countLetter(passwd, SMALL_LETTER) >= PASSWROD_LENGTH_2
                && countLetter(passwd, CAPITAL_LETTER) >= PASSWROD_LENGTH_2 && countLetter(passwd, OTHER_CHAR) >= PASSWROD_LENGTH_2) {
            level++;
        }

        if (countLetter(passwd, OTHER_CHAR) >= PASSWROD_LENGTH_3) {
            level++;
        }
        if (countLetter(passwd, OTHER_CHAR) >= PASSWROD_LENGTH_6) {
            level++;
        }

        if (len > PASSWROD_LENGTH_12) {
            level++;
            if (len >= PASSWROD_LENGTH_16) {
                level++;
            }
        }
        level = getNotPlusLevel(passwd, level);
        return level;
    }

    /**
     * @describe:获得非加level
     * @param: [passwd, level]
     * @return: int
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 18:14
     */
    private static int getNotPlusLevel(String passwd, int level) {
        int len = passwd.length();
        // decrease points
        if (countLetter(passwd, NUM) == len || countLetter(passwd, SMALL_LETTER) == len
                || countLetter(passwd, CAPITAL_LETTER) == len) {
            level--;
        }

        if (len % PASSWROD_LENGTH_3 == 0) {
            // ababab
            String part1 = passwd.substring(0, len / 3);
            String part2 = passwd.substring(len / 3, len / 3 * 2);
            String part3 = passwd.substring(len / 3 * 2);
            if (part1.equals(part2) && part2.equals(part3)) {
                level--;
            }
        }


        if (null != DICTIONARY && DICTIONARY.length > 0) {
            // dictionary
            for (int i = 0; i < DICTIONARY.length; i++) {
                if (DICTIONARY[i].indexOf(passwd.toLowerCase()) >= 0) {
                    level--;
                    break;
                }
            }
        }

        if (len < PASSWROD_LENGTH_6) {
            level--;
            if (len <= PASSWROD_LENGTH_4) {
                level--;
                if (len <= PASSWROD_LENGTH_3) {
                    level = 0;
                }
            }
        }

        if (passwd.replace(passwd.charAt(0), EMPTY_CHAR).trim().length() == 0 || level < 0) {
            level = 0;
        }
        return level;
    }


    /**
     *@describe: Get password strength level, includes easy, midium, strong, very strong, extremely strong
     *@param: [passwd]
     *@return: com.zhy.frame.authentication.oauth2.center.util.PasswordUtil.LEVEL
     *@author: lvmoney /XXXXXX科技有限公司
     *2019/9/9 18:18
     */
    public static LEVEL getPasswordLevel(String passwd) {
        int level = check(passwd);
        switch (level) {
            case 0:
            case 1:
            case 2:
            case 3:
                return LEVEL.EASY;
            case 4:
            case 5:
            case 6:
                return LEVEL.MEDIUM;
            case 7:
            case 8:
            case 9:
                return LEVEL.STRONG;
            case 10:
            case 11:
            case 12:
                return LEVEL.VERY_STRONG;
            default:
                return LEVEL.EXTREMELY_STRONG;
        }
    }

}
