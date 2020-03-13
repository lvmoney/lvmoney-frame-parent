package com.zhy.frame.ops.docker.vo;/**
 * 描述:
 * 包名:com.zhy.k8s.base.vo
 * 版本信息: 版本1.0
 * 日期:2019/9/19
 * Copyright 四川******科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/9/19 13:53
 */
@Data
public class DockerInfo implements Serializable {
    private static final long serialVersionUID = 2855131271391571971L;
    /**
     * docker私服中镜像tag
     */
    private String dockerImageTag;
    /**
     * 镜像初始名称
     */
    private String dockerImageName;
    /**
     * dockerFile 中 jar名称
     */
    private String dockerFileName;
}
