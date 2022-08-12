package com.lvmoney.frame.newsql.clickhouse.sink.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.newsql.clickhouse.sink.util
 * 版本信息: 版本1.0
 * 日期:2020/6/30
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/30 10:03
 */
public class JsonUtil {
    public static <T> String t2JsonString(T t) {
        return JSON.toJSONString(t);
    }
}
