package com.lvmoney.frame.cloud.base.service;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.service
 * 版本信息: 版本1.0
 * 日期:2019/8/17
 * Copyright XXXXXX科技有限公司
 */


import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.api.model.NodeList;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/17 10:01
 */
public interface K8sService {
    /**
     * 获得所有的命名空间
     *
     * @throws
     * @return: io.fabric8.kubernetes.api.model.NamespaceList
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:49
     */
    NamespaceList listNamespace();

    /**
     * 获得所有节点
     *
     * @throws
     * @return: io.fabric8.kubernetes.api.model.NodeList
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 20:49
     */
    NodeList listNode();
}
