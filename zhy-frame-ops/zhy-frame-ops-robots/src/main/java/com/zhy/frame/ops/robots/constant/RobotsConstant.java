package com.zhy.frame.ops.robots.constant;/**
 * 描述:
 * 包名:com.zhy.robots.constant
 * 版本信息: 版本1.0
 * 日期:2019/10/29
 * Copyright XXXXXX科技有限公司
 */


import static com.zhy.frame.base.core.constant.BaseConstant.FILE_SEPARATOR;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/10/29 16:50
 */
public class RobotsConstant {
    /**
     * robots redis key
     */
    public static final String ROBOTS_KEY = "robots_key";
    /**
     * robots.txt 存放路径
     */
    public static final String ROBOTS_FILE_PATH = System.getProperty("user.dir") + FILE_SEPARATOR + "data" + FILE_SEPARATOR + "robots";
    /**
     * 换行符
     */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator", "\n");

    /**
     * 爬虫名称
     */
    public static final String USER_AGENT = "User-agent:";
    /**
     * 被允许访问的key
     */
    public static final String DISALLOW = "Disallow:";

    /**
     * robots.txt 名字
     */
    public static final String ROBOTS_NAME_ALL = "robots.txt";

}
