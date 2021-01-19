package com.lvmoney.autocode.code.constant;/**
 * 描述:
 * 包名:com.lvmoney.autocode.code.constant
 * 版本信息: 版本1.0
 * 日期:2020/6/19
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/19 9:28
 */
public class CodeConstant {
    /**
     * 类title
     */
    public static final String DEFAULT_CLASS_TITLE = "/**\n" +
            " * 描述:\n" +
            " * 包名:{0}\n" +
            " * 版本信息: 版本1.0\n" +
            " * 日期:{1}\n" +
            " * Copyright {2}\n" +
            " */";
    /**
     * 类 描述
     */
    public static final String DEFAULT_CLASS_DESCRIBE = "/**\n" +
            " * @describe：\n" +
            " * @author: {0}/{1}\n" +
            " * @version:v1.0 {2}\n" +
            " */";
    /**
     * import 关键字
     */
    public static final String IMPORT = "import";
    /**
     * public 关键字
     */
    public static final String PUBLIC = "public";
    /**
     * package 关键字
     */
    public static final String PACKAGE = "package";
    /**
     * 注解 符号
     */
    public static final String ANNOTATIONS = "@";
    /**
     * java 类文件后缀
     */
    public static final String JAVA_FILE_SUFFIX = ".java";

    /**
     * api 前缀
     */
    public static final String DEFAULT_API_INTERFACE_PREFIX = "I";
    /**
     * controller 后缀
     */
    public static final String DEFAULT_CONTROLLER_SUFFIX = "Controller";
    /**
     * 关键字extends
     */
    public static final String EXTENDS = "extends";
    /**
     * 关键字 implements
     */
    public static final String IMPLEMENTS = "implements";
    /**
     * 控制器注解
     */
    public static final String CONTROLLER_ANNOTATIONS = "RestController";

    /**
     * 控制器import
     */
    public static final String CONTROLLER_IMPORT = "org.springframework.web.bind.annotations.RestController";
}
