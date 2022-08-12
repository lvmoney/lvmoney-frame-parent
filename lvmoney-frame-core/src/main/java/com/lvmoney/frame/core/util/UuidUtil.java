package com.lvmoney.frame.core.util;
/**
 * 描述:
 * 包名:com.lvmoney.frame.core.util
 * 版本信息: 版本1.0
 * 日期:2019/2/27
 * Copyright 四川******科技有限公司
 */


import java.util.UUID;

import static com.lvmoney.frame.base.core.constant.BaseConstant.DASH_LINE;

/**
 * @describe：uuid工具类
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/7/20 10:38
 */
public class UuidUtil {
    /**
     * 获得一个uuid
     *
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/11/25 11:15
     */
    public static String getUuid() {
        String uuid = UUID.randomUUID().toString().replaceAll(DASH_LINE, "");
        return uuid;
    }

}
