package com.zhy.frame.core.util;/**
 * 描述:
 * 包名:com.zhy.frame.core.util
 * 版本信息: 版本1.0
 * 日期:2019/11/29
 * Copyright 四川******科技有限公司
 */


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/11/29 12:25
 */
public class DateUtil {
    /**
     * 时间格式化
     *
     * @param localDateTime:
     * @param pattern:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/12/4 16:55
     */
    public static String format(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 判断是否今天以前
     *
     * @param date:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/12/4 16:55
     */
    public static boolean beforeToday(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date yesterday = calendar.getTime();
        return date.before(yesterday);
    }
}
