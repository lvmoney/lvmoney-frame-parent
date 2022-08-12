package com.lvmoney.frame.core.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.core.util
 * 版本信息: 版本1.0
 * 日期:2021/9/1
 * Copyright XXXXXX科技有限公司
 */


import cn.hutool.core.util.HexUtil;
import org.hashids.Hashids;

import java.util.Arrays;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/9/1 16:08
 */
public class HashidsUtil {

    /**
     * 编码获得hashids
     *
     * @param salt: 盐
     * @param size: 长度
     * @param res:  需要编码的值
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/9/1 16:40
     */
    public static String encode(String salt, Integer size, Long res) {
        Hashids hashids = new Hashids(salt, size);
        String encryptString = hashids.encode(res);
        return encryptString;
    }

    /**
     * 解码hashids获得未编码的数据
     *
     * @param salt: 盐
     * @param size: 长度
     * @param res:  需要解码的值
     * @throws
     * @return: java.lang.Long
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/9/1 16:41
     */
    public static Long decode(String salt, Integer size, String res) {

        Hashids hashids = new Hashids(salt, size);
        long[] decodeNumbers = hashids.decode(res);

        return Arrays.stream(decodeNumbers).findFirst().getAsLong();

    }

    /**
     * 编码获得hashids 16进制
     *
     * @param salt: 盐
     * @param size: 长度
     * @param res:  需要编码的值
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/9/1 16:43
     */
    public static String encodeHex(String salt, Integer size, String res) {
        Hashids hashids = new Hashids(salt, size);
        String encryptString = hashids.encodeHex(HexUtil.encodeHexStr(res));
        return encryptString;
    }

    /**
     * 解码hashids获得未编码的数据 16进制
     *
     * @param salt:盐
     * @param size:长度
     * @param res:    需要解码的值
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/9/1 16:45
     */
    public static String decodeHex(String salt, Integer size, String res) {

        Hashids hashids = new Hashids(salt, size);
        String decodeNumbers = hashids.decodeHex(res);

        return HexUtil.decodeHexStr(decodeNumbers);

    }

    /**
     * 编码获得hashids
     *
     * @param salt:     盐
     * @param size:     长度
     * @param res:      需要编码的值
     * @param alphabet: 自定义字符
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/9/1 16:40
     */
    public String encodeCustomize(String salt, Integer size, Long res, String alphabet) {
        Hashids hashids = new Hashids(salt, size, alphabet);
        String encryptString = hashids.encode(res);
        return encryptString;
    }

    /**
     * 解码hashids获得未编码的数据
     *
     * @param salt:     盐
     * @param size:     长度
     * @param res:      需要解码的值
     * @param alphabet: 自定义字符
     * @throws
     * @return: java.lang.Long
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/9/1 16:41
     */
    public static Long decodeCustomize(String salt, Integer size, String res, String alphabet) {

        Hashids hashids = new Hashids(salt, size, alphabet);
        long[] decodeNumbers = hashids.decode(res);

        return Arrays.stream(decodeNumbers).findFirst().getAsLong();

    }

}
