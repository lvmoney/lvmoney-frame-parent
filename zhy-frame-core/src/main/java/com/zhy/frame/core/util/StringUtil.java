/**
 * 描述:
 * 包名:com.zhy.hotel.util
 * 版本信息: 版本1.0
 * 日期:2018年12月4日  下午1:57:49
 * Copyright 四川******科技有限公司
 */

package com.zhy.frame.core.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年12月4日 下午1:57:49
 */

public class StringUtil extends StringUtils {
    private static final Pattern PATTERN = Pattern.compile("[\u4e00-\u9fa5]");
    /**
     * 16进制长度
     */
    private static final int BIN_STR_LENGTH = 16;

    /**
     * 空字符串 <code>""</code>.
     *
     * @since 2.0
     */
    public static final String EMPTY = "";

    public static boolean isContainChinese(String str) {
        Matcher m = PATTERN.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    /**
     * @describe: 不足左侧补零, 超过返回原值
     * @param: [str, strLength]
     * @return: java.lang.String
     * @author： lvmoney /四川******科技有限公司
     * 2019/3/9
     */
    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                // 左补0
                sb.append("0").append(str);
                // sb.append(str).append("0");//右补0
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }


    /**
     * 16进制转 byte[]
     *
     * @param hexString
     * @return
     */
    public static byte[] hexToByte(String hexString) {
        if (StringUtils.isEmpty(hexString)) {
            return null;
        }
        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() >> 1];
        int index = 0;
        for (int i = 0; i < hexString.length(); i++) {
            if (index > hexString.length() - 1) {
                return byteArray;
            }
            byte highDit = (byte) (Character.digit(hexString.charAt(index), 16) & 0xFF);
            byte lowDit = (byte) (Character.digit(hexString.charAt(index + 1), 16) & 0xFF);
            byteArray[i] = (byte) (highDit << 4 | lowDit);
            index += 2;
        }
        return byteArray;
    }

    /**
     * @describe: 将Unicode字符串转换成01字符串
     * @param: [str]
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:50
     */
    public static String strToBinStr(String str) {
        return boolArrToBinStr(strToBool(str));
    }

    /**
     * @describe: 将01字符串转换成Unicode字符串
     * @param: [arrStr]
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:50
     */
    public static String binStrToStr(String arrStr) {
        return boolToStr(binStrToBoolArr(arrStr));
    }

    /**
     * @describe: 将Unicode字符串转换成01字符串
     * @param: [str]
     * @return: int[]
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:50
     */
    public static int[] strToBinArr(String str) {
        return boolArrToBinArr(strToBool(str));
    }

    /**
     * @describe: 将01数组转换成Unicode字符串
     * @param: [intArr]
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:51
     */
    public static String binArrToStr(int[] intArr) {
        return boolToStr(binArrToBoolArr(intArr));
    }

    /**
     * @describe: 将bool型数组转换成01数组
     * @param: [boolArr]
     * @return: int[]
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:51
     */
    public static int[] boolArrToBinArr(boolean[] boolArr) {
        int[] intArr = new int[boolArr.length];
        for (int i = 0; i < boolArr.length; i++) {
            intArr[i] = boolArr[i] ? 1 : 0;
        }
        return intArr;
    }

    /**
     * @describe: 将01数组转换成bool型数组
     * @param: [intArr]
     * @return: boolean[]
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:51
     */
    public static boolean[] binArrToBoolArr(int[] intArr) {
        boolean[] boolArr = new boolean[intArr.length];
        for (int i = 0; i < intArr.length; i++) {
            boolArr[i] = intArr[i] == 1 ? true : false;
        }
        return boolArr;
    }

    /**
     * @describe: 将bool型数组转换成二进制字符串
     * @param: [boolArr]
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:51
     */
    public static String boolArrToBinStr(boolean[] boolArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (boolean b : boolArr) {
            if (b) {
                stringBuffer.append("1");
            } else {
                stringBuffer.append("0");
            }
        }
        return stringBuffer.toString();
    }

    /**
     * @describe: 将二进制字符串转换成bool型数组
     * @param: [str]
     * @return: boolean[]
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:51
     */
    public static boolean[] binStrToBoolArr(String str) {
        char[] chs = str.toCharArray();
        boolean[] boolArr = new boolean[chs.length];
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == '0') {
                boolArr[i] = false;
            } else {
                boolArr[i] = true;
            }
        }
        return boolArr;
    }

    /**
     * @describe: 将Unicode字符串转换成bool型数组
     * @param: [input]
     * @return: boolean[]
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:51
     */
    public static boolean[] strToBool(String input) {
        boolean[] output = binstr16ToBool(binstrToBinstr16(strToBinstr(input)));
        return output;
    }

    /**
     * @describe: 将bool型数组转换成Unicode字符串
     * @param: [input]
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:52
     */
    public static String boolToStr(boolean[] input) {
        String output = binstrToStr(binstr16ToBinstr(boolToBinstr16(input)));
        return output;
    }

    /**
     * @describe: 将字符串转换成二进制字符串，以空格相隔
     * @param: [str]
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:52
     */
    private static String strToBinstr(String str) {
        char[] strChar = str.toCharArray();
        String result = "";
        for (int i = 0; i < strChar.length; i++) {
            result += Integer.toBinaryString(strChar[i]) + " ";
        }
        return result;
    }

    /**
     * @describe: 将二进制字符串转换成Unicode字符串
     * @param: [binStr]
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:52
     */
    private static String binstrToStr(String binStr) {
        String[] tempStr = strToStrArray(binStr);
        char[] tempChar = new char[tempStr.length];
        for (int i = 0; i < tempStr.length; i++) {
            tempChar[i] = binstrToChar(tempStr[i]);
        }
        return String.valueOf(tempChar);
    }

    /**
     * @describe: 将二进制字符串格式化成全16位带空格的Binstr
     * @param: [input]
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:52
     */
    private static String binstrToBinstr16(String input) {
        StringBuffer output = new StringBuffer();
        String[] tempStr = strToStrArray(input);
        for (int i = 0; i < tempStr.length; i++) {
            for (int j = BIN_STR_LENGTH - tempStr[i].length(); j > 0; j--) {
                output.append('0');
            }
            output.append(tempStr[i] + " ");
        }
        return output.toString();
    }

    /**
     * @describe: 将全16位带空格的Binstr转化成去0前缀的带空格Binstr
     * @param: [input]
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:52
     */
    private static String binstr16ToBinstr(String input) {
        StringBuffer output = new StringBuffer();
        String[] tempStr = strToStrArray(input);
        for (int i = 0; i < tempStr.length; i++) {
            for (int j = 0; j < BIN_STR_LENGTH; j++) {
                if (tempStr[i].charAt(j) == '1') {
                    output.append(tempStr[i].substring(j) + " ");
                    break;
                }
                if (j == 15 && tempStr[i].charAt(j) == '0') {
                    output.append("0" + " ");
                }
            }
        }
        return output.toString();
    }

    /**
     * @describe: 二进制字串转化为boolean型数组 输入16位有空格的Binstr
     * @param: [input]
     * @return: boolean[]
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:52
     */
    private static boolean[] binstr16ToBool(String input) {
        String[] tempStr = strToStrArray(input);
        boolean[] output = new boolean[tempStr.length * 16];
        for (int i = 0, j = 0; i < input.length(); i++, j++) {
            if (input.charAt(i) == '1') {
                output[j] = true;
            } else if (input.charAt(i) == '0') {
                output[j] = false;
            } else {
                j--;
            }
        }
        return output;
    }

    /**
     * @describe: boolean型数组转化为二进制字串 返回带0前缀16位有空格的Binstr
     * @param: [input]
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:52
     */
    private static String boolToBinstr16(boolean[] input) {
        StringBuffer output = new StringBuffer();
        for (int i = 0; i < input.length; i++) {
            if (input[i]) {
                output.append('1');
            } else {
                output.append('0');
            }
            if ((i + 1) % 16 == 0) {
                output.append(' ');
            }
        }
        output.append(' ');
        return output.toString();
    }

    /**
     * @describe: 将二进制字符串转换为char
     * @param: [binStr]
     * @return: char
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:53
     */
    private static char binstrToChar(String binStr) {
        int[] temp = binstrToIntArray(binStr);
        int sum = 0;
        for (int i = 0; i < temp.length; i++) {
            sum += temp[temp.length - 1 - i] << i;
        }
        return (char) sum;
    }

    /**
     * @describe: 将初始二进制字符串转换成字符串数组，以空格相隔
     * @param: [str]
     * @return: java.lang.String[]
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:56
     */
    private static String[] strToStrArray(String str) {
        return str.split(" ");
    }

    /**
     * @describe: 将二进制字符串转换成int数组
     * @param: [binStr]
     * @return: int[]
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:56
     */
    private static int[] binstrToIntArray(String binStr) {
        char[] temp = binStr.toCharArray();
        int[] result = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            result[i] = temp[i] - 48;
        }
        return result;
    }


    /**
     * <p>如果傳入的參數為 <code>null</code>, 返回一個空字符串("").</p>
     * <p>
     * <pre>
     * StringUtils.defaultString(null)  = ""
     * StringUtils.defaultString("")    = ""
     * StringUtils.defaultString("bat") = "bat"
     * </pre>
     *
     * @param str 传入的参数，可以为null
     * @return 转换后的字符串，如果传入为 <code>null</code>，则为空字符串
     * @see String#valueOf(Object)
     */
    public static String defaultString(String str) {
        return str == null ? EMPTY : str;
    }

    /**
     * <p>检查字符串是否为空.</p>
     * <p>
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str 需要进行检查的字符串
     * @return 如果传入的str为null、空、空格，则返回<code>true</code> ,
     * @since 2.0
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 将concatText拼接到oldText，以separator为分隔符(首位不添加该分隔符)
     *
     * @param oldText    原有字符串
     * @param concatText 待拼接字符串
     * @param separator  分隔符
     * @return 拼接后的字符串
     */
    public static String join(String oldText, String concatText, String separator) {
        return StringUtil.isBlank(oldText) ? concatText : (oldText + separator + concatText);
    }

    /**
     * 将concatText拼接到oldText，以separator为分隔符(首位不添加该分隔符)
     *
     * @param oldText   原有字符串
     * @param concatObj 待拼接对象(直接调用.toString()转换为字符串)
     * @param separator 分隔符
     * @return 拼接后的字符串
     */
    public static String join(String oldText, Object concatObj, String separator) {
        return StringUtil.isBlank(oldText) ? concatObj.toString() : (oldText + separator + concatObj.toString());
    }

    /**
     * 将strList列表中每一个字符串按照separator分割，拼接到oldText(首位不添加该分隔符)
     *
     * @param strList   待拼接字符串列表
     * @param separator 分隔符
     * @return
     */
    public static String join(Collection<String> strList, String separator) {
        String text = StringUtil.EMPTY;
        Iterator<String> strItr = strList.iterator();
        while (strItr.hasNext()) {
            text = join(text, strItr.next(), separator);
        }
        return text;
    }

    /**
     * 首字母大写
     *
     * @param text 将首字母转为大写
     * @return 首字母大写字符串
     */
    public static String capitalize(String text) {
        String result = text;

        result = result.substring(0, 1).toUpperCase() + result.substring(1, result.length() - 1);

        return result;
    }

    /**
     * <p>Removes core occurrences of a substring from within the source string.</p>
     * <p>
     * <p>A <code>null</code> source string will return <code>null</code>.
     * An empty ("") source string will return the empty string.
     * A <code>null</code> remove string will return the source string.
     * An empty ("") remove string will return the source string.</p>
     * <p>
     * <pre>
     * StringUtils.remove(null, *)        = null
     * StringUtils.remove("", *)          = ""
     * StringUtils.remove(*, null)        = *
     * StringUtils.remove(*, "")          = *
     * StringUtils.remove("queued", "ue") = "qd"
     * StringUtils.remove("queued", "zz") = "queued"
     * </pre>
     *
     * @param str    the source String to search, may be null
     * @param remove the String to search for and remove, may be null
     * @return the substring with the string removed if found,
     * <code>null</code> if null String input
     * @since 2.1
     */
    public static String remove(String str, String remove) {
        if (isBlank(str) || isBlank(remove)) {
            return str;
        }
        return replace(str, remove, EMPTY, -1);
    }


    /**
     * <p>Replaces a String with another String inside a larger String,
     * for the first <code>max</code> values of the search String.</p>
     * <p>
     * <p>A <code>null</code> reference passed to this method is a no-op.</p>
     * <p>
     * <pre>
     * StringUtils.replace(null, *, *, *)         = null
     * StringUtils.replace("", *, *, *)           = ""
     * StringUtils.replace("any", null, *, *)     = "any"
     * StringUtils.replace("any", *, null, *)     = "any"
     * StringUtils.replace("any", "", *, *)       = "any"
     * StringUtils.replace("any", *, *, 0)        = "any"
     * StringUtils.replace("abaa", "a", null, -1) = "abaa"
     * StringUtils.replace("abaa", "a", "", -1)   = "b"
     * StringUtils.replace("abaa", "a", "z", 0)   = "abaa"
     * StringUtils.replace("abaa", "a", "z", 1)   = "zbaa"
     * StringUtils.replace("abaa", "a", "z", 2)   = "zbza"
     * StringUtils.replace("abaa", "a", "z", -1)  = "zbzz"
     * </pre>
     *
     * @param text         text to search and replace in, may be null
     * @param searchString the String to search for, may be null
     * @param replacement  the String to replace it with, may be null
     * @param max          maximum number of values to replace, or <code>-1</code> if no maximum
     * @return the text with any replacements processed,
     * <code>null</code> if null String input
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:57
     */
    public static String replace(String text, String searchString, String replacement, int max) {
        if (isBlank(text) || isBlank(searchString) || replacement == null || max == 0) {
            return text;
        }
        int start = 0;
        int end = text.indexOf(searchString, start);
        if (end == -1) {
            return text;
        }
        int replLength = searchString.length();
        int increase = replacement.length() - replLength;
        increase = (increase < 0 ? 0 : increase);
        increase *= (max < 0 ? 16 : (max > 64 ? 64 : max));
        StringBuffer buf = new StringBuffer(text.length() + increase);
        while (end != -1) {
            buf.append(text.substring(start, end)).append(replacement);
            start = end + replLength;
            if (--max == 0) {
                break;
            }
            end = text.indexOf(searchString, start);
        }
        buf.append(text.substring(start));
        return buf.toString();
    }

    /**
     * @describe: 去除数组中重复和为空的字符串
     * @param: [array]
     * @return: java.lang.String[]
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:57
     */
    public static String[] distinctArray(String[] array) {
        if (array == null || array.length < 1) {
            return array;
        }
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < array.length; i++) {
            String tmp = array[i];
            if (list.contains(tmp) && !StringUtil.isBlank(tmp)) {
                list.add(tmp);
            }
        }
        array = list.toArray(new String[list.size()]);
        return array;
    }

    /**
     * @describe: 字符串左补齐
     * @param: [oldText, length, parse]
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:57
     */
    public static String lpad(String oldText, int length, char parse) {
        return StringUtil.leftPad(oldText, length, parse);
    }

    /**
     * @describe: 字符串右补齐
     * @param: [oldText, length, parse]
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/9 10:56
     */
    public static String rpad(String oldText, int length, char parse) {
        return StringUtil.rightPad(oldText, length, parse);
    }

    public static void main(String[] args) {
        System.out.println(StringUtil.addZeroForNum("123", 6));
    }
}
