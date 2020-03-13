package com.zhy.frame.cloud.base.vo.jyaml;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.vo.jyaml
 * 版本信息: 版本1.0
 * 日期:2019/8/19
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/19 8:58
 */
@Data
public class Deployment extends K8sYamlCommon {
    private static final long serialVersionUID = 6041035673278327344L;
    private DeploymentSpec spec;
}
