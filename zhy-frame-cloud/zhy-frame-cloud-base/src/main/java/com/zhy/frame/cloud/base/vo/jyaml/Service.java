package com.zhy.frame.cloud.base.vo.jyaml;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.vo.jyaml
 * 版本信息: 版本1.0
 * 日期:2019/8/18
 * Copyright XXXXXX科技有限公司
 */


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/18 20:17
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Service extends K8sYamlCommon {

    private static final long serialVersionUID = 276787794182459892L;
    private ServiceSpec spec;
}

