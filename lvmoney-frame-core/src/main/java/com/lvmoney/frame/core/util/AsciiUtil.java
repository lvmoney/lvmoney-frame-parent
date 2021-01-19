package com.lvmoney.frame.core.util;/**
 * 描述:
 * 包名:com.lvmoney.common.util
 * 版本信息: 版本1.0
 * 日期:2019/11/6
 * Copyright XXXXXX科技有限公司
 */


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/6 17:54
 */
public class AsciiUtil {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//
//        System.out.println(charToByteAscii('9'));
//        System.out.println(byteAsciiToChar(33));
//        System.out.println(SumStrAscii("19"));
//        System.out.println(SumStrAscii("一"));
/**
 * nt randNumber =rand.nextInt(MAX - MIN + 1) + MIN; // randNumber 将被赋值为一个 MIN 和 MAX 范围内的随机数
 // */
//        System.out.println(ThreadLocalRandom.current().nextInt(3));
//        System.out.println(ThreadLocalRandom.current().nextInt(10) + 48);
//
//        System.out.println(ThreadLocalRandom.current().nextInt(26) + 65);
//
//        System.out.println(ThreadLocalRandom.current().nextInt(26) + 97);
//        for (int i = 0; i < 100; i++) {
//            System.out.println(getSingleChar());
//        }
        System.out.println(getNextChar(48));
        //101010
        List<Integer> inint = new ArrayList() {{
            add(48);
            add(122);
            add(48);
            add(48);
            add(48);
            add(48);
        }};

        for (int i = 0; i < 62; i++) {
            inint = getPrex(inint);
        }
        System.out.println(getPrexString(inint));

        List<Integer> inint2 = new ArrayList() {{
            add(48);
            add(122);
            add(48);
            add(48);
            add(48);
            add(48);
        }};
        inint2 = getPrex(inint2);
        System.out.println(getPrexString(inint2));
    }


    /**
     * 随机类型1代表数字
     */
    private static final int CHAR_TYPE_NUM = 1;
    /**
     * 随机类型2代表大写字母
     */
    private static final int CHAR_TYPE_UPPER = 2;
    /**
     * 随机类型3代表小写字母
     */
    private static final int CHAR_TYPE_LOWER = 3;
    /**
     * 数字ascii码最小值
     */
    private static final int TYPE_NUM_MIN = 48;
    /**
     * 数字ascii码最大值
     */
    private static final int TYPE_NUM_MAX = 57;
    /**
     * 大写字母ascii码最小值
     */
    private static final int TYPE_UPPER_MIN = 65;
    /**
     * 大写字母ascii码最大值
     */
    private static final int TYPE_UPPER_MAX = 90;
    /**
     * 小写字母ascii码最小值
     */
    private static final int TYPE_LOWER_MIN = 97;
    /**
     * 小写字母ascii码最大值
     */
    private static final int TYPE_LOWER_MAX = 122;


    /**
     * 获得随机字符，字符0-9 a-z A-Z
     * 0-9对应Ascii 48-57
     * A-Z 65-90
     * a-z 97-122
     * 第33～126号(共94个)是字符，其中第48～57号为0～9十个阿拉伯数字
     *
     * @throws
     * @return:java.lang.String
     * @author:lvmoney /XXXXXX科技有限公司
     * @date:2019/11/7 9:07
     */

    /**
     * 循环字符串顺序加1
     * 48,48,48,48,48,48->49,48,48,48,48,48
     * 达到 TYPE_LOWER_MAX 会顺序加一位
     *
     * @param init:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/7 9:52
     */
    public static List<Integer> getPrex(List<Integer> init) {
        List<Integer> temp = new ArrayList();
        temp.addAll(init);
        init.set(0, getNextChar(temp.get(0)));
        for (int i = 0; i < init.size() - 1; i++) {
            if (init.get(i) == TYPE_LOWER_MAX) {
                int next = ++i;
                if (temp.get(next) == -1) {
                    init.set(next, TYPE_NUM_MIN);
                } else {
                    init.set(next, getNextChar(temp.get(next)));
                }

            }
        }
        return init;
    }

    /**
     * 获得数组对应的字符串
     *
     * @param init:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/7 10:52
     */
    public static String getPrexString(List<Integer> init) {
        StringBuilder sb = new StringBuilder();
        for (int i : init) {
            if (i == -1) {
                continue;
            }
            sb.append(byteAsciiToChar(i));
        }
        return sb.toString();
    }


    public static String getSingleChar() {
        int type = ThreadLocalRandom.current().nextInt(3) + 1;
        int charResult = 1;
        if (type == CHAR_TYPE_NUM) {
            //获得数字0-9
            charResult = ThreadLocalRandom.current().nextInt(TYPE_NUM_MAX - TYPE_NUM_MIN + 1) + TYPE_NUM_MIN;
        } else if (type == CHAR_TYPE_UPPER) {
            //获得大写字母A-Z
            charResult = ThreadLocalRandom.current().nextInt(TYPE_UPPER_MAX - TYPE_UPPER_MIN + 1) + TYPE_UPPER_MIN;
        } else if (type == CHAR_TYPE_LOWER) {
            //获得小写字母a-z
            charResult = ThreadLocalRandom.current().nextInt(TYPE_LOWER_MAX - TYPE_LOWER_MIN + 1) + TYPE_LOWER_MIN;
        }
        return String.valueOf(byteAsciiToChar(charResult));
    }

    /**
     * 获得字符的下一个字符，通过0-9-A-Z-a-z-0循环
     *
     * @param ascii:
     * @throws
     * @return: int
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/7 9:30
     */
    public static int getNextChar(int ascii) {
        if (isChar(ascii)) {
            if (ascii == TYPE_NUM_MAX) {
                return TYPE_UPPER_MIN;
            }
            if (ascii == TYPE_UPPER_MAX) {
                return TYPE_LOWER_MIN;
            }
            if (ascii == TYPE_LOWER_MAX) {
                return TYPE_NUM_MIN;
            }
            return ++ascii;
        } else {
            return -1;
        }
    }

    /**
     * 判断一个ascii码是否是字符
     *
     * @param ascii:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/7 9:31
     */
    public static boolean isChar(int ascii) {
        if (ascii >= TYPE_NUM_MIN || ascii <= TYPE_NUM_MAX) {
            return true;
        }
        if (ascii >= TYPE_UPPER_MIN || ascii <= TYPE_UPPER_MAX) {
            return true;
        }
        if (ascii >= TYPE_LOWER_MIN || ascii <= TYPE_LOWER_MAX) {
            return true;
        }
        return false;
    }

    /**
     * 获得指定长度的字符串
     *
     * @param length: 长度
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/7 9:07
     */
    public static String getSpecifiedLengthString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(getSingleChar());
        }
        return sb.toString();
    }

    /**
     * 将char 强制转换为byte
     *
     * @param ch: 字符
     * @throws
     * @return: byte
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/7 9:08
     */
    public static byte charToByteAscii(char ch) {
        byte byteAscii = (byte) ch;
        return byteAscii;
    }

    /**
     * 将char直接转化为int，其值就是字符的ascii
     *
     * @param ch:
     * @throws
     * @return: byte
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/7 9:08
     */
    public static byte charToByteAscii2(char ch) {
        byte byteAscii = (byte) ch;

        return byteAscii;
    }

    /**
     * ascii转换为char 直接int强制转换为char
     *
     * @param ascii:
     * @throws
     * @return: char
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/7 9:08
     */
    public static char byteAsciiToChar(int ascii) {
        char ch = (char) ascii;
        return ch;
    }

    /**
     * 求出字符串的ASCII值和
     * 注意，如果有中文的话，会把一个汉字用两个byte来表示，其值是负数
     *
     * @param str:
     * @throws
     * @return: int
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/7 9:08
     */
    public static int sumStrAscii(String str) {
        byte[] bytestr = str.getBytes();
        int sum = 0;
        for (int i = 0; i < bytestr.length; i++) {
            sum += bytestr[i];
        }
        return sum;
    }
}
