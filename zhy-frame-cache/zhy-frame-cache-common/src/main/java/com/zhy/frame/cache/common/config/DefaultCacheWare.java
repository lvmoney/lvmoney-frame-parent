package com.zhy.frame.cache.common.config;/**
 * 描述:
 * 包名:com.zhy.frame.cache.common.config
 * 版本信息: 版本1.0
 * 日期:2019/11/27
 * Copyright 四川******科技有限公司
 */


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/11/27 9:39
 */

public class DefaultCacheWare {

    /**
     * 默认的cache类型
     */
    public final static String DEFAULT_CACHE_WARE;

    static {
        DEFAULT_CACHE_WARE = "";
    }
}
