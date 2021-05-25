/**
 * 描述:
 * 包名:com.lvmoney.hotel.util
 * 版本信息: 版本1.0
 * 日期:2018年11月16日  下午3:02:01
 * Copyright XXXXXX科技有限公司
 */

package com.lvmoney.frame.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年11月16日 下午3:02:01
 */

public class ListUtil {

    /**
     * 获得两个list取交集
     *
     * @param res
     * @param compare
     * @return 2018年11月16日下午3:08:54
     * @describe:获得前面list中不包含后面list的字符list
     * @author: lvmoney /XXXXXX科技有限公司
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List<String> getDifference(List<String> res, List<String> compare) {
        List<String> result = new ArrayList<String>();
        Collection exists = new ArrayList<String>(res);
        exists.removeAll(compare);
        result.addAll(exists);
        return result;
    }


}
