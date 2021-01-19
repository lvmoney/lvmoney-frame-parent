package com.lvmoney.frame.base.core.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.base.core.util
 * 版本信息: 版本1.0
 * 日期:2020/3/8
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.constant.BaseConstant;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/8 10:53
 */
public class SupportUtil {
    /**
     * 判断字段是否是true or false
     *
     * @param support:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/3/8 10:54
     */
    public static boolean support(String support) {
        if (BaseConstant.SUPPORT_FALSE.equals(support) || BaseConstant.SUPPORT_TRUE.equals(support)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean support(boolean support) {
        if (BaseConstant.SUPPORT_FALSE_BOOL == support || BaseConstant.SUPPORT_TRUE_BOOL == support) {
            return true;
        } else {
            return false;
        }
    }
}
