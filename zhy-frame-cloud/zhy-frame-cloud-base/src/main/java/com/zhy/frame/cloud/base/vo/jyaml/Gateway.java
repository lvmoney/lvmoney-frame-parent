package com.zhy.frame.cloud.base.vo.jyaml;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.vo.jyaml
 * 版本信息: 版本1.0
 * 日期:2019/8/19
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

/**
 * @describe：让服务网格的服务，可以被全世界看到
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/19 9:22
 */
@Data
public class Gateway extends K8sYamlCommon {
    private static final long serialVersionUID = -2006971551398064919L;
    private GatewatSpec spec;
}
