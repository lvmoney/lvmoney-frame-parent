package com.lvmoney.frame.cloud.base.vo.jyaml;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.vo.jyaml
 * 版本信息: 版本1.0
 * 日期:2019/8/19
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/19 9:14
 */
@Data
public class DeploymentSpec implements Serializable {
    private static final long serialVersionUID = -3547019119340747457L;
    private int replicas;
    private DeploymentSpecSelector selector;
    private Template template;
}
