package com.zhy.frame.ops.docker.vo.req;/**
 * 描述:
 * 包名:com.zhy.k8s.base.vo.req
 * 版本信息: 版本1.0
 * 日期:2019/8/21
 * Copyright 四川******科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/8/21 20:46
 */
@Data
public class DockerFileReqVo implements Serializable {
    private static final long serialVersionUID = 8656559701529337778L;
    private String temp;
}
