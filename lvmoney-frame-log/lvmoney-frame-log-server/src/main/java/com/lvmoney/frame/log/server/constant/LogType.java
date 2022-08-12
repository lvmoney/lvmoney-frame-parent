package com.lvmoney.frame.log.server.constant;/**
 * 描述:
 * 包名:com.lvmoney.log.constant
 * 版本信息: 版本1.0
 * 日期:2019/9/6
 * Copyright 四川******科技有限公司
 */


/**
 * @describe：系统操作业务日志记录类型， 不用枚举是为了方便业务系统拓展
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/9/6 11:17
 */
public class LogType {
    /**
     * 增加
     */
    public static final String LOG_ADD = "add";
    /**
     * 删除
     */
    public static final String LOG_DELETE = "delete";
    /**
     * 查询
     */
    public static final String LOG_SEARCH = "search";
    /**
     * 更该
     */
    public static final String LOG_UPDATE = "update";
    /**
     * 上传
     */
    public static final String LOG_UPLOAD = "upload";
    /**
     * 下载
     */
    public static final String LOG_DOWNLOAD = "download";
    /**
     * 其他
     */
    public static final String LOG_OTHER = "other";

}
