package com.lvmoney.frame.core.util;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/4/8
 * Copyright 四川******科技有限公司
 */


import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月10日 下午4:23:28
 */

public class FilterMapUtil {
    /**
     * 判断请求地址是否在配置文件中有效
     *
     * @param map:
     * @param path:
     * @param eq:
     * @throws
     * @return: boolean
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/11/13 11:33
     */
    public static boolean wildcardMatchMapKey(Map<String, String> map, String path, String eq) {
        boolean result = false;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (WildcardUtil.simpleWildcardMatch(key, path) && value.equals(eq)) {
                result = true;
                break;
            }
        }
        return result;
    }
}

