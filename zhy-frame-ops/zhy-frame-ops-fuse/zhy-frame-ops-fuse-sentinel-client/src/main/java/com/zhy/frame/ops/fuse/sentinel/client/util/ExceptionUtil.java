package com.zhy.frame.ops.fuse.sentinel.client.util;/**
 * 描述:
 * 包名:com.zhy.frame.ops.fuse.sentinel.client.util
 * 版本信息: 版本1.0
 * 日期:2020/4/10
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/10 10:02
 */
public final class ExceptionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionUtil.class);

    public static void handleException(BlockException ex) {
        LOGGER.info("Oops: " + ex.getClass().getCanonicalName());
    }
}
