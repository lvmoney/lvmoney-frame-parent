package com.lvmoney.frame.cloud.base.constant;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.constant
 * 版本信息: 版本1.0
 * 日期:2019/8/16
 * Copyright XXXXXX科技有限公司
 */


import static com.lvmoney.frame.base.core.constant.BaseConstant.FILE_SEPARATOR;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/16 15:20
 */
public class CloudBaseConstant {

    /**
     * Yaml 文件后缀
     */
    public static final String YAML_SUFFIX = ".yaml";

    public static final String YAML_FILE_PATH = System.getProperty("user.dir") + FILE_SEPARATOR + "data" + FILE_SEPARATOR + "yaml";


    /**
     * ingress 默认端口
     */
    public static final int INGRESS_DEFAULT_PORT = 80;

    /**
     * ingress 默认https端口
     */
    public static final int INGRESS_DEFAULT_HTTPS_PORT = 433;

    /**
     * 虚拟服务映射路径默认为根
     */
    public static final String VIRTUAL_SERVICE_EXAC = "/";

    /**
     * 服务版本:v1
     */
    public static final String VERSION_V1 = "v1";
    /**
     * 服务版本:v2
     */
    public static final String VERSION_V2 = "v2";


    /**
     * 服务超时时间
     */
    public static final String ISTIO_SERVICE_TIMEOUT = "9s";
    /**
     * 每次尝试持续时间
     */
    public static final String ISTIO_SERVICE_PERTRYTIMEOUT = "3s";
    /**
     * 时间单位:秒
     */
    public static final String TIME_UNIT_S = "s";
    /**
     * 时间单位:毫秒
     */
    public static final String TIME_UNIT_MS = "ms";

    /**
     * 默认 jvm配置
     */
    public static final String DEFAULT_JAVA_OPTS = "-XX:MetaspaceSize=128m\n" +
            " -XX:MaxMetaspaceSize=128m\n" +
            " -Xms1024m -Xmx1024m -Xmn256m -Xss256k\n" +
            " -XX:SurvivorRatio=8\n" +
            "-XX:+UseConcMarkSweepGC\n";
}
