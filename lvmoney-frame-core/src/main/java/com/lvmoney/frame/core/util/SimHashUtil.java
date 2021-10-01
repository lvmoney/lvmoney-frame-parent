package com.lvmoney.frame.core.util;/**
 * 描述:
 * 包名:com.lvmoney.platform.product.manager.internal.util
 * 版本信息: 版本1.0
 * 日期:2021/9/15
 * Copyright XXXXXX科技有限公司
 */

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.corpus.tag.Nature;
import com.hankcs.hanlp.seg.common.Term;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import static com.lvmoney.frame.base.core.constant.BaseConstant.PLACEHOLDER_BLANK_SPACE;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/9/15 19:56
 */
public class SimHashUtil {
    /**
     * 默认64位，即将一个文本转换为64bit数据
     */
    private static int HASH_BITS = 64;

    private static Pattern CHINES_PATTERIN = Pattern.compile("^[\u4e80-\u9fa5]+$");
    /**
     * 默认权重
     */
    private static final int DW_EIGHT = 1;

    /**
     * 默认数字1
     */
    private static final String STR_ONE = "1";
    /**
     * 默认数字0
     */
    private static final String STR_ZERO = "0";
    /**
     * 默认数字-1
     */
    private static final String STR_ONE_MINUS = "-1";
    /**
     * 默认数字-2
     */
    private static final String STR_TWO_MINUS = "-2";
    /**
     * 默认乘数
     */
    private static final String DEFAULT_MULTIPLY_VALUE = "1000003";

    /**
     * 默认数字2
     */
    private static final String STR_TWO = "2";
    /**
     * 字符ns
     */
    private static final String STR_NS = "ns";


    public SimHashUtil() {
        super();
    }

    public SimHashUtil(int HASH_BITS) {
        super();
        this.HASH_BITS = HASH_BITS;
    }


    /**
     * 将Simhash签名值平均分割为4等份
     *
     * @param signature 字符串
     * @return List<String> 返回分割的4等份字符串
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/9/16 8:45
     */
    public static List<String> splitFourEqual(String signature) {
        int length = signature.length();
        int m = 4;
        int num = length / m;
        List<String> list = new ArrayList();
        for (int i = 0; i < m; i++) {
            list.add(signature.substring(i * num, (i + 1) * num));
        }
        return list;
    }


    /**
     * 计算SimHash签名值
     *
     * @param text:
     * @return String 返回HASH_BITS位二进制字符串的SimHash签名值
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/9/16 8:46
     */
    public static String getSimHashSignatureValue(String text) {
        // 1、分词
        List<Term> terms = HanLP.segment(text);
        List<String> list = new ArrayList<String>();
        for (Term term : terms) {
            // 2.权重
            if ("w".equals(term.nature.toString())) {// 排除停用词性为w:标点
                continue;
            }
            Integer num = calculateWeight(term);
            // 3.hash值和加权
            list.add(getStrSimHash(term.word, num));
        }
        // 4.合并
        int[] merge = new int[HASH_BITS];
        for (String str : list) {
            String[] split = str.split(PLACEHOLDER_BLANK_SPACE);
            for (int i = 0, len = split.length; i < len; i++) {
                merge[i] += Integer.parseInt(split[i]);
            }
        }
        // 5.降维
        StringBuilder rd = new StringBuilder();
        for (int i : merge) {
            // 对于n-bit签名的累加结果，如果大于0则置1，否则置0，从而得到该语句的simhash值
            if (i > 0) {
                rd.append(STR_ONE);
            } else {
                rd.append(STR_ZERO);
            }
        }
        return rd.toString();
    }


    /**
     * 计算分词权重
     *
     * @param term
     * @return int 返回分词权重
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/9/16 8:47
     */
    public static int calculateWeight(Term term) {
        // 汉字数
        int num = countChinese(term.word);
        // 大于3个汉字，权重增加
        int value = num >= 3 ? 2 + (num - 3) / 2 : DW_EIGHT;
        // 专属词，如果有两个字至少要最小分是2分
        if (term.nature == Nature.nz && value <= DW_EIGHT) {
            value = DW_EIGHT + 1;
        }
        return value > 5 ? 5 : value;
    }

    /**
     * 统计汉字数
     *
     * @param text 字符串
     * @return int 返回汉字数
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/9/16 8:47
     */
    public static int countChinese(String text) {
        if (StringUtils.isEmpty(text)) return 0;
        int amount = 0;
        char[] chs = text.toCharArray();
        for (char c : chs) {
            amount = CHINES_PATTERIN.matcher(String.valueOf(c)).matches() ? ++amount : amount;
        }
        return amount;
    }

    /**
     * 加权处理方法
     *
     * @param tokens 每个分词文本
     * @param weight 权重
     * @return String 返回加权处理后的HASH_BITS位的二进制字符串
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/9/16 8:48
     */
    public static String getStrSimHash(String tokens, Integer weight) {
        int[] v = new int[HASH_BITS];
        StringTokenizer stringTokens = new StringTokenizer(tokens);

        while (stringTokens.hasMoreTokens()) {
            String temp = stringTokens.nextToken();
            BigInteger t = hash(temp);
            for (int i = 0; i < v.length; i++) {
                BigInteger bitmask = new BigInteger(STR_ONE).shiftLeft(i);
                if (t.and(bitmask).signum() != 0) {
                    v[i] += 1;
                } else {
                    v[i] -= 1;
                }
            }
        }
        // 加权处理
        // 每一个分词的 hashcode 中，对应位上如果为1，则该位加上权重w，这里权重为1，即+1,；对应位上如果不为1，则该位减去权重w，这里即-1
        StringBuffer simHashBuffer = new StringBuffer();
        for (int i = 0; i < v.length; i++) {
            if (v[i] >= 0) {
                simHashBuffer.append(1 * weight).append(PLACEHOLDER_BLANK_SPACE);
            } else {
                simHashBuffer.append(-1 * weight).append(PLACEHOLDER_BLANK_SPACE);
            }
        }

        return simHashBuffer.toString().trim();
    }


    /**
     * 对单个的分词进行hash计算
     *
     * @param source 分词
     * @return BigInteger 返回分词对应的hash值
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/9/16 8:48
     */
    private static BigInteger hash(String source) {
        if (source == null || source.length() == 0) {
            return new BigInteger(STR_ZERO);
        }
        /**
         * 当sourece 的长度过短，会导致hash算法失效，因此需要对过短的词补偿
         */
//        while (source.length() < 3) {
//            source = source + source.charAt(0);
//        }
        char[] sourceArray = source.toCharArray();
        BigInteger x = BigInteger.valueOf(((long) sourceArray[0]) << 7);
        BigInteger m = new BigInteger(DEFAULT_MULTIPLY_VALUE);
        BigInteger mask = new BigInteger(STR_TWO).pow(HASH_BITS).subtract(new BigInteger(STR_ONE));

        for (int i = 0; i < sourceArray.length; i++) {
            char item = sourceArray[i];
            BigInteger temp = BigInteger.valueOf(item);
            x = x.multiply(m).xor(temp).and(mask);
        }
        x = x.xor(new BigInteger(String.valueOf(source.length())));
        if (x.equals(new BigInteger(STR_ONE_MINUS))) {
            x = new BigInteger(STR_TWO_MINUS);
        }
        return x;
    }

    /**
     * 求两个二进制字符串之间的海明距离
     *
     * @param str1 SimHash签名值(64位二进制字符串)
     * @param str2 SimHash签名值(64位二进制字符串)
     * @return int 返回海明距离值(海明距离在3以内的可认为相似度比较高)
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/9/16 8:49
     */
    public int getDistance(String str1, String str2) {
        int distance;
        if (str1.length() != str2.length()) {
            distance = -1;
        } else {
            distance = 0;
            for (int i = 0; i < str1.length(); i++) {
                if (str1.charAt(i) != str2.charAt(i)) {
                    distance++;
                }
            }
        }
        return distance;
    }

    /**
     * 比较海明距离
     *
     * @param str1 SimHash签名值(64位二进制字符串)
     * @param str2 SimHash签名值(64位二进制字符串)
     * @return int 返回海明距离值(海明距离在3以内的可认为相似度比较高)
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/9/16 8:49
     */
    public static int comparisonHammingDistance(String str1, String str2) {
        if (StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2)) {
            return -1;
        }
        int distance = 0;
        char[] chs1 = str1.toCharArray();
        char[] chs2 = str2.toCharArray();
        int len = Math.min(chs1.length, chs2.length);// 计算最小值，防止遍历数组下标溢出
        for (int i = 0; i < len; i++) {// 遍历长度有待深究
            if ((chs1[i] ^ chs2[i]) == 1) {// 异或判断
                distance++;
            }
        }
        return distance;
    }

    /**
     * 根据文本计算出地名和出现的次数
     *
     * @param text 一段文本
     * @return Map<String, Integer> 返回{地名：出现次数}
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/9/16 8:49
     */
    public static Map<String, Integer> getWeightToPlaceName(String... text) {
        Map<String, Integer> placeNameAndWeightMap = new HashMap(BaseConstant.MAP_DEFAULT_SIZE);
        for (String str : text) {
            List<Term> terms = HanLP.segment(str);
            for (Term term : terms) {
                // 2.权重
                if (STR_NS.equals(term.nature.toString())) {// ns:地名
                    placeNameAndWeightMap.compute(term.word, (k, v) -> {
                        if (v == null) {
                            v = 1;
                        } else {
                            v += 1;
                        }
                        return v;
                    });
                }
            }
        }
        return sortMapByValue(placeNameAndWeightMap);
    }

    /**
     * 按值排序
     *
     * @param oriMap 需要排序的对象
     * @return Map<String, Integer> 返回排序后的对象
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/9/16 8:50
     */
    public static Map<String, Integer> sortMapByValue(Map<String, Integer> oriMap) {
        Map<String, Integer> sortedMap = new LinkedHashMap(BaseConstant.MAP_DEFAULT_SIZE);
        if (oriMap != null && !oriMap.isEmpty()) {
            List<Entry<String, Integer>> entryList = new ArrayList<Entry<String, Integer>>(oriMap.entrySet());
            Collections.sort(entryList, new Comparator<Entry<String, Integer>>() {
                public int compare(Entry<String, Integer> entry1, Entry<String, Integer> entry2) {
                    return entry2.getValue() - entry1.getValue();
                }
            });
            Iterator<Entry<String, Integer>> iter = entryList.iterator();
            Entry<String, Integer> tmpEntry = null;
            while (iter.hasNext()) {
                tmpEntry = iter.next();
                sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
            }
        }
        return sortedMap;
    }
}
