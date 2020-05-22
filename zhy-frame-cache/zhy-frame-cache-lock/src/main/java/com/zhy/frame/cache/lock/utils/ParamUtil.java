package com.zhy.frame.cache.lock.utils;/**
 * 描述:
 * 包名:com.zhy.frame.cache.lock.utils
 * 版本信息: 版本1.0
 * 日期:2020/5/19
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.base.core.util.JsonUtil;
import com.zhy.frame.cache.lock.constant.LockConstant;
import com.zhy.frame.core.util.SignUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/19 14:11
 */
public class ParamUtil {

    /**
     * map排序并构造请求格式
     *
     * @param paraMap:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/19 14:11
     */
    public static String buildParam(Map<String, String> paraMap) {
        String content = SignUtil.formatUrlMap(paraMap, false, false);
        return content;
    }

    public static Map<String, String> buildRequestMap(Object req) {
        Map result = new HashMap(BaseConstant.MAP_DEFAULT_SIZE);
        List<HashMap> list = JSON.parseArray(JsonUtil.t2JsonString(req), HashMap.class);
        list.forEach(e -> {
            for (Object key : e.keySet()) {
                String v = e.get(key).toString();
                if (!LockConstant.JSON_EMPTY_VALUE.equals(v)) {
                    result.put(key.toString(), e.get(key).toString());
                }
            }
        });
        return result;
    }

}
