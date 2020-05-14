package com.zhy.frame.ops.fuse.sentinel.client.config;/**
 * 描述:
 * 包名:com.zhy.frame.ops.fuse.sentinel.client.config
 * 版本信息: 版本1.0
 * 日期:2020/4/11
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlCleaner;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/11 10:33
 */
@Component
public class FrameUrlCleaner implements UrlCleaner {

    @Override
    public String clean(String originUrl) {
        String[] split = originUrl.split("/");

        // 将数字转换为特定的占位标识符
        return Arrays.stream(split)
                .map(s -> NumberUtils.isCreatable(s) ? "{number}" : s)
                .reduce((a, b) -> a + "/" + b)
                .orElse("");
    }
}