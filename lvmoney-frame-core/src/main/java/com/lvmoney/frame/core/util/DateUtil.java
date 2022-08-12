package com.lvmoney.frame.core.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.core.util
 * 版本信息: 版本1.0
 * 日期:2019/11/29
 * Copyright 四川******科技有限公司
 */


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    /**
     * 长整型时间戳转化为LocalDateTime
     *
     * @param timestamp:
     * @throws
     * @return: LocalDateTime
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/12/4 16:55
     */
    public static LocalDateTime timestamp2LocalDateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * LocalDateTime 转时间戳
     *
     * @param localDateTime:
     * @throws
     * @return: java.lang.Long
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/8/7 23:04
     */

    public static Long LocalDateTime2Timestamp(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

    /**
     * LocalDateTime 格式化
     *
     * @param localDateTime:
     * @param formatter:
     * @throws
     * @return: LocalDateTime
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/12/4 16:55
     */
    public static LocalDateTime localDateTimeFormatter(LocalDateTime localDateTime, String formatter) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(formatter);
        String localTime = df.format(localDateTime);
        LocalDateTime ldt = LocalDateTime.parse(localTime, df);
        return ldt;
    }

    /**
     * 获得当前时间 类型为Long
     *
     * @throws
     * @return: java.lang.Long
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/11/22 16:39
     */

    public static Long getNowFormatLong() {
        return LocalDateTime2Timestamp(LocalDateTime.now());
    }
}
