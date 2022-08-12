/**
 * 描述:
 * 包名:com.lvmoney.hotel.util
 * 版本信息: 版本1.0
 * 日期:2018年12月4日  下午1:57:49
 * Copyright XXXXXX科技有限公司
 */

package com.lvmoney.frame.base.core.util;

import com.lvmoney.frame.base.core.constant.BaseConstant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年12月4日 下午1:57:49
 */

public class CoreStringUtil {
    /**
     * 首字母小写
     *
     * @param str:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/8/20 12:34
     */

    public static String lowerFirstCode(String str) {
        char[] ch = str.toCharArray();

        if (ch[0] >= BaseConstant.CHAR_A_UPPER && ch[0] <= BaseConstant.CHAR_Z_UPPER) {
            ch[0] = (char) (ch[0] + 32);
        }
        return new String(ch);

    }
}
