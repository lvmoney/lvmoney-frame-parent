package com.lvmoney.frame.cloud.base.vo.jyaml;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.vo.jyaml
 * 版本信息: 版本1.0
 * 日期:2019/8/19
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

/**
 * @describe：实现请求路由的功能
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/19 9:31
 */
@Data
public class VirtualService extends K8sYamlCommon {
    private VirtualServiceSpec spec;
}
