/**
 * 描述:
 * 包名:com.lvmoney.hotel.util
 * 版本信息: 版本1.0
 * 日期:2018年11月16日  下午3:02:01
 * Copyright XXXXXX科技有限公司
 */

package com.lvmoney.frame.core.util;

import com.lvmoney.frame.base.core.constant.BaseConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年11月16日 下午3:02:01
 */

public class ListUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListUtil.class);
    /**
     * 默认忽略 serialVersionUID
     */
    private static final String IGNORE_SERIAL_VERSION_UID = "serialVersionUID";

    private ListUtil() {
    }

    /**
     * 获得两个list取交集
     *
     * @param res
     * @param compare
     * @return 2018年11月16日下午3:08:54
     * @describe:获得前面list中不包含后面list的字符list
     * @author: lvmoney /四川XXXXXX公司
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static List<String> getDifference(List<String> res, List<String> compare) {
        List<String> result = new ArrayList<String>();
        Collection exists = new ArrayList<String>(res);
        exists.removeAll(compare);
        result.addAll(exists);
        return result;
    }

    /**
     * 实体列表行转列
     *
     * @param t:
     * @throws
     * @return: java.util.Map<java.lang.String, java.util.List < java.lang.String>>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/8/5 16:04
     */
    public static <T> Map<String, List<String>> row2Colum(List<T> t) {
        Field[] fields = t.get(0).getClass().getDeclaredFields();
        Map<String, List<String>> maps = new HashMap(BaseConstant.MAP_DEFAULT_SIZE);
        for (Field field : fields) {
            field.setAccessible(true);
            if (IGNORE_SERIAL_VERSION_UID.equals(field.getName())) {
                continue;
            }
            List<String> strs = new ArrayList();
            for (T t1 : t) {

                try {
                    strs.add(field.get(t1) == null ? null : field.get(t1).toString());
                } catch (IllegalAccessException e) {
                    LOGGER.error("实体列表行转列报错:{}", e);
                    return null;
                }
            }
            maps.put(field.getName(), strs);
        }
        return maps;
    }

}
