/**
 * 描述:
 * 包名:gateway.test
 * 版本信息: 版本1.0
 * 日期:2018年11月14日  下午5:18:55
 * Copyright xxxx科技有限公司
 */

package com.zhy.frame.core.util;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年11月14日 下午5:18:55
 */


import com.zhy.frame.base.core.constant.BaseConstant;

import java.util.*;

/**
 * @author lvmoney
 * @describe：根据首字母分组，可用于通讯录之类的
 * @creation date 2012-10-25 上午13:01:21
 */
public class SortUtil {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("周杰伦");
        list.add("周润发");
        list.add("kobe");
        list.add("james");
        list.add("jordan");
        list.add("霍元甲");
        Map<String, Object> map = sortDesc(list);


        map.forEach((k, v) -> {
            System.out.println("k=" + k + ",v=" + v);
        });

    }

    /**
     * 字母Z使用了两个标签，这里有２７个值
     * i, u, v都不做声母, 跟随前面的字母
     */
    private static final char[] CHARTABLE = {'啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈', '击', '喀', '垃', '妈', '拿', '哦',
            '啪', '期', '然', '撒', '塌', '塌', '塌', '挖', '昔', '压', '匝', '座'};
    private static final char[] ALPHATABLEB = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final char[] ALPHABLES = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    /**
     * 默认长度
     */
    private static final int ARRAY_LENGTHDEFAULT = 27;
    /**
     * 26个英文字母
     */
    private static final int STRING_LENGTH_MAX = 26;
    /**
     * gbk下字符长度
     */
    private static final int CHAR_GBK_BYTE_LENGTH = 2;

    /**
     * 初始化
     */
    private static final int[] TABLE = new int[27];

    static {
        for (int i = 0; i < ARRAY_LENGTHDEFAULT; ++i) {
            TABLE[i] = gbValue(CHARTABLE[i]);
        }
    }

    /**
     * 主函数, 输入字符, 得到他的声母,
     * 英文字母返回对应的大小写字母
     * 其他非简体汉字返回 '0' 按参数
     *
     * @param ch:
     * @param type:
     * @throws
     * @return: char
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/6 17:44
     */
    public static char char2Alpha(char ch, String type) {
        if (ch >= BaseConstant.CHAR_A_LOWER && ch <= BaseConstant.CHAR_Z_LOWER) {
            // 为了按字母排序先返回大写字母
            return (char) (ch - BaseConstant.CHAR_A_LOWER + BaseConstant.CHAR_A_UPPER);
        }
        if (ch >= BaseConstant.CHAR_A_UPPER && ch <= BaseConstant.CHAR_Z_UPPER) {
            return ch;
        }
        int gb = gbValue(ch);
        if (gb < TABLE[0]) {
            return '0';
        }
        int i;
        for (i = 0; i < STRING_LENGTH_MAX; ++i) {
            if (match(i, gb)) {
                break;
            }
        }
        if (i >= STRING_LENGTH_MAX) {
            return '0';
        } else {

            if (BaseConstant.CHAR_B_LOWER.equals(type)) {
                // 大写
                return ALPHATABLEB[i];
            } else {
                // 小写
                return ALPHABLES[i];
            }
        }
    }


    /**
     * 根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串
     *
     * @param sourceStr:
     * @param type:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/6 17:44
     */
    public String string2Alpha(String sourceStr, String type) {
        String result = "";
        int strLength = sourceStr.length();
        int i;
        try {
            for (i = 0; i < strLength; i++) {
                result += char2Alpha(sourceStr.charAt(i), type);
            }
        } catch (Exception e) {
            result = "";
        }
        return result;
    }

    /**
     * 根据一个包含汉字的字符串返回第一个汉字拼音首字母的字符串
     *
     * @param sourceStr:
     * @param type:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/6 17:45
     */
    public static String string2AlphaFirst(String sourceStr, String type) {
        String result = "";
        try {
            result += char2Alpha(sourceStr.charAt(0), type);
        } catch (Exception e) {
            result = "";
        }
        return result;
    }

    private static boolean match(int i, int gb) {
        if (gb < TABLE[i]) {
            return false;
        }
        int j = i + 1;

        // 字母Z使用了两个标签
        while (j < STRING_LENGTH_MAX && (TABLE[j] == TABLE[i])) {
            ++j;
        }
        if (j == STRING_LENGTH_MAX) {
            return gb <= TABLE[j];
        } else {
            return gb < TABLE[j];
        }
    }

    /**
     * 取出汉字的编码
     *
     * @param ch:
     * @throws
     * @return: int
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/6 17:45
     */
    private static int gbValue(char ch) {
        String str = new String();
        str += ch;
        try {
            byte[] bytes = str.getBytes(BaseConstant.CHARACTER_ENCODE_GBK);
            if (bytes.length < CHAR_GBK_BYTE_LENGTH) {
                return 0;
            }
            return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
        } catch (Exception e) {
            return 0;
        }
    }


    /**
     * mapkey值，升序分组
     *
     * @param list:
     * @throws
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/6 17:45
     */
    public static Map<String, Object> sortAsc(List<String> list) {
        Map<String, Object> map = new TreeMap<>(Comparator.naturalOrder());
        ArrayList<String> arraylist = new ArrayList<String>();
        String[] alphatableb = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
                "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        for (String a : alphatableb) {
            for (int i = 0; i < list.size(); i++) {
                // 为了排序都返回大写字母
                if (a.equals(string2AlphaFirst(list.get(i), "b"))) {
                    arraylist.add(list.get(i));
                }
            }
            if (!arraylist.isEmpty()) {
                // 空值不写入map
                map.put(a, arraylist);
            }
            arraylist = new ArrayList<String>();
        }
        return map;
    }


    /**
     * mapkey值，降序分组
     *
     * @param list:
     * @throws
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/6 17:45
     */

    public static Map<String, Object> sortDesc(List<String> list) {
        Map<String, Object> map = new TreeMap<>(Comparator.reverseOrder());
        ArrayList<String> arraylist = new ArrayList<String>();
        String[] alphatableb = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
                "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        for (String a : alphatableb) {
            for (int i = 0; i < list.size(); i++) {
                // 为了排序都返回大写字母
                if (a.equals(string2AlphaFirst(list.get(i).toString(), "b"))) {
                    arraylist.add(list.get(i).toString());
                }
            }
            if (!arraylist.isEmpty()) {
                // 空值不写入map
                map.put(a, arraylist);
            }
            arraylist = new ArrayList<String>();
        }
        return map;
    }
}
