package com.zhy.frame.cloud.base.enums;/**
 * 描述:
 * 包名:com.zhy.k8s.base.enums
 * 版本信息: 版本1.0
 * 日期:2019/8/19
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/19 11:13
 */
public enum DockerPull {
    /**
     * docker镜像有就不拉
     */
    IfNotPresent("IfNotPresent"),
    /**
     * docker镜像总是拉
     */
    Always("Always"),
    /**
     * docker镜像总是不拉
     */
    Never("Never");

    private String value;

    DockerPull(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
