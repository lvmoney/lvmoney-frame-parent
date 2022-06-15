package com.lvmoney.frame.base.core.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.base.core.util
 * 版本信息: 版本1.0
 * 日期:2022/5/13
 * Copyright XXXXXX科技有限公司
 */


import java.util.Arrays;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/13 16:50
 */
public class ArrayUtil {
    /**
     * String 多个数组合并
     *
     * @param first:
     * @param rest:
     * @throws
     * @return: T[]
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/6/8 10:01
     */
    public static <T> T[] merge(T[] first, T[]... rest) {
        int totalLength = first.length;
        for (T[] array : rest) {
            totalLength += array.length;
        }
        T[] result = Arrays.copyOf(first, totalLength);
        int offset = first.length;
        for (T[] array : rest) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }
}
