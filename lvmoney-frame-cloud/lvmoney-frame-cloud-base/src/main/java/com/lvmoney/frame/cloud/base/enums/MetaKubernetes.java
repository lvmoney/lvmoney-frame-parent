package com.lvmoney.frame.cloud.base.enums;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.enums
 * 版本信息: 版本1.0
 * 日期:2019/10/18
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：meta_kubernetes 元数据标签
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/10/18 14:41
 */
public enum MetaKubernetes {
    /**
     * 端点对象的命名空间，在不同对象上这个标签的含义不同，在角色是endpoints中这个是端点对象的名称空间
     */
    namespace("__meta_kubernetes_namespace"),
    /**
     * 端点对象的服务名称
     */
    serviceName("__meta_kubernetes_service_name"),
    /**
     * 端点的端口名称
     */
    endpointPortName("__meta_kubernetes_endpoint_port_name"),
    /**
     * 暴露自定义的应用的端口，就是把地址和你在service中定义的 "prometheus.io/port = <port>" 声明做一个拼接
     */
    serviceAnnotationPrometheusIoPort("__meta_kubernetes_service_annotation_prometheus_io_port"),
    /**
     * Prometheus 监控地址
     */
    serviceAnnotationPrometheusIoPath("__meta_kubernetes_service_annotation_prometheus_io_path"),


    ;
    private String value;

    MetaKubernetes(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
