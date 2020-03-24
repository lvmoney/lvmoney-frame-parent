package com.zhy.frame.cloud.base.vo.jyaml;/**
 * 描述:
 * 包名:com.zhy.k8s.base.vo.jyaml
 * 版本信息: 版本1.0
 * 日期:2019/8/19
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/19 9:40
 */
@Data
public class Route implements Serializable {
    private static final long serialVersionUID = 8349886309358930303L;
    private Destination destination;
    private Integer weight;
    /**
     * 超时时间
     */
    private String timeout;
    /**
     * 流量复制
     */
    private Mirror mirror;
}
