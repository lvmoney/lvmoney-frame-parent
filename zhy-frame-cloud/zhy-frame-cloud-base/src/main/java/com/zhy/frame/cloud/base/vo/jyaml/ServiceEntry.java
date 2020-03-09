package com.zhy.frame.cloud.base.vo.jyaml;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.vo.jyaml
 * 版本信息: 版本1.0
 * 日期:2019/8/24
 * Copyright XXXXXX科技有限公司
 */


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @describe：让服务网格内的服务，可以看到外面的世界
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/24 17:10
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceEntry extends K8sYamlCommon {
    private ServiceEntrySpec spec;
}
