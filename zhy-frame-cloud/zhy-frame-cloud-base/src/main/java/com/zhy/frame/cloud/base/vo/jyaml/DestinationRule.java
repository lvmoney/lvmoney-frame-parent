package com.zhy.frame.cloud.base.vo.jyaml;/**
 * 描述:
 * 包名:com.zhy.k8s.base.vo.jyaml
 * 版本信息: 版本1.0
 * 日期:2019/8/19
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

/**
 * @describe：可以实现服务发现和负载均衡、故障处理和故障注入的功能
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/19 10:19
 */
@Data
public class DestinationRule extends K8sYamlCommon {
    private static final long serialVersionUID = 8794270369416284083L;
    private DestinationRuleSpec spec;
}
