package com.zhy.frame.cloud.base.vo.jyaml;/**
 * 描述:
 * 包名:com.zhy.k8s.base.vo.jyaml
 * 版本信息: 版本1.0
 * 日期:2019/8/18
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/18 20:51
 */
@Data
public class Ports implements Serializable {
    private static final long serialVersionUID = 1880404112385975340L;
    private String name;
    private Integer port;
    private Integer nodePort;
    private Integer targetPort;
    private Integer containerPort;
    private String protocol;
    private Integer number;
}
