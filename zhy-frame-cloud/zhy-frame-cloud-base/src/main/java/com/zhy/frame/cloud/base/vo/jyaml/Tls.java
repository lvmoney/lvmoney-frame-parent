package com.zhy.frame.cloud.base.vo.jyaml;/**
 * 描述:
 * 包名:com.zhy.k8s.base.vo.jyaml
 * 版本信息: 版本1.0
 * 日期:2019/10/22
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/10/22 16:08
 */
@Data
public class Tls implements Serializable {
    /**
     * 简单模式（仅需客户端验证服务端Https证书，不是双向验证（MUTUAL））
     */
    private String node;
    /**
     * 挂载服务端证书（与之前定义的secret tls istio-ingressgateway-certs --cert对应）
     */
    private String serverCertificate;
    /**
     * 挂载服务端私钥（与之前定义的secret tls istio-ingressgateway-certs --key对应）
     */
    private String privateKey;
}
