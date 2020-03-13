package com.zhy.frame.route.gateway.utils;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway.util
 * 版本信息: 版本1.0
 * 日期:2019/8/14
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.core.util.WildcardUtil;

import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/14 14:40
 */
public class FilterMapUtil {
    /**
     * 通过eq判断是否map中包含path
     *
     * @param map
     * @param path
     * @param eq
     * @return
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
