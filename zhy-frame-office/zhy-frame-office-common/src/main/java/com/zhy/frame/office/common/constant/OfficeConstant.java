package com.zhy.frame.office.common.constant;/**
 * 描述:
 * 包名:com.scltzhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/6
 * Copyright 四川******科技有限公司
 */


import java.io.File;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class OfficeConstant {

    public static final String TEMP_SUFFIX = ".docx";

    public static final String FILE_SEPARATOR = File.separator;
    public static final String TEMP_FILE_PATH = System.getProperty("user.dir") + FILE_SEPARATOR + "data" + FILE_SEPARATOR + "tmp";

}
