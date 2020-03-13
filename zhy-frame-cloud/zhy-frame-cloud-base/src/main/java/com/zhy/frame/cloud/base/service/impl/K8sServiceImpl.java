package com.zhy.frame.cloud.base.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/17
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.util.JsonUtil;
import com.zhy.frame.cloud.base.service.K8sService;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.NamespaceList;
import io.fabric8.kubernetes.api.model.NodeList;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/17 10:01
 */
@Service
public class K8sServiceImpl implements K8sService {
    private static final Logger LOGGER = LoggerFactory.getLogger(K8sServiceImpl.class);
    /**
     * k8s api封装库调用
     */

    private static KubernetesClient kubernetesClient;
    private static Config config;

    @PostConstruct
    public void k8sServiceImpl() {
        config = new ConfigBuilder().withMasterUrl("http://192.168.1.181:8001").build();
//            config.setOauthToken("eyJhbGciOiJSUzI1NiIsImtpZCI6IiJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImRlZmF1bHQtdG9rZW4tZDZkYzIiLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiZGVmYXVsdCIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6Ijg0MTdmMjMxLThiNjUtMTFlOS04ZGE1LTAwMGMyOTNlMzBkYSIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDpkZWZhdWx0OmRlZmF1bHQifQ.zVh906RSgMJH7kGo8AhmDKg8hEGSlLfzdlZEap7wOWurBUMct35vMvnDhXLG2P_s7_1qGx0NCAgxigGe3EnydEfFBdo2c_4BYmEOvaD3oe2qSVS9rIwfujqvXNGQKOMYiVnD42bK7l7LVixSIj4TkZ88WD23uVw5pxAA1j2pmDriVjXOySmVPer0aWWPS20A1oYfiO2a8mV-z_v3wzhXCvG36eerWvCd5zxbrUKFYaFbYe4pHtW-zWB2yIHYpVDEgJOaECMvEmB7aNdkN0IAB9N-f3GnEOvgzw5aRmd-_8T7caCA7SV6hQ6M0YCRG1JYs9kx30v9Xi9cUu1DQv3CbA");

        kubernetesClient = new DefaultKubernetesClient(config);
    }

    /**
     * @describe: 列出当前命名空间
     * @param: []
     * @return: io.fabric8.kubernetes.api.model.NamespaceList
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:19
     */
    @Override
    public NamespaceList listNamespace() {
        NamespaceList namespaceList = new NamespaceList();
        try {
            namespaceList = kubernetesClient.namespaces().list();
            System.out.println("list sucess");
        } catch (Exception e) {
            System.out.println("list failed");
            LOGGER.error("获得所有的命名空间报错:{}", e.getMessage());

        }
        return namespaceList;
    }

    /**
     * @describe: 列出当前可用节点
     * @param: []
     * @return: io.fabric8.kubernetes.api.model.NodeList
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:19
     */
    @Override
    public NodeList listNode() {
        NodeList nodeList = new NodeList();
        try {
            nodeList = kubernetesClient.nodes().list();

            System.out.println(JsonUtil.t2JsonString(kubernetesClient.services().inNamespace("default").withLabelIn("app", "wudebao-web").list()));
            String sfile = "D:\\file\\test\\data.Yaml";
            File file = new File(sfile);

            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                List<HasMetadata> result = kubernetesClient.load(fis).createOrReplace();
                System.out.println("result:" + result);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (Exception e2) {
                        LOGGER.error("获得所有的节点报错:{}", e2.getMessage());
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("获得所有的节点报错:{}", e.getMessage());
        }
        return nodeList;
    }

}
