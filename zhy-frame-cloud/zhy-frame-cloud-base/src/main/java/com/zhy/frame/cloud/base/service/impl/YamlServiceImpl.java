package com.zhy.frame.cloud.base.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/19
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.cloud.base.constant.CloudBaseConstant;
import com.zhy.frame.cloud.base.enums.*;
import com.zhy.frame.cloud.base.properties.RpcServerConfigProp;
import com.zhy.frame.cloud.base.service.YamlService;
import com.zhy.frame.cloud.base.util.PomUtil;
import com.zhy.frame.cloud.base.util.YamlUtil;
import com.zhy.frame.cloud.base.vo.jyaml.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/19 10:53
 */
@org.springframework.stereotype.Service
public class YamlServiceImpl implements YamlService {
    @Value("${custom.tomcat.https.schema:http}")
    private String schema;
    @Value("${secret.tls.serverCertificate:/etc/istio/ingressgateway-certs/tls.crt}")
    private String serverCertificate;
    @Value("${secret.tls.privateKey:/etc/istio/ingressgateway-certs/tls.key}")
    private String privateKey;
    /**
     * 是否覆盖
     */
    @Value("${istio.yaml.cover:true}")
    boolean yamlCover;
    /**
     * 服务名称
     */
    @Value("${spring.application.name:lvmoney}")
    String applicationName;
    /**
     * 命名空间
     */
    @Value("${istio.yaml.namespace:default}")
    String istioNamespace;
    /**
     * 服务端口
     */
    @Value("${server.port:8080}")
    int serverPort;
    /**
     * 发布多少个pod
     */
    @Value("${istio.yaml.replicas:1}")
    int replicas;
    /**
     * 服务版本
     */
    @Value("${istio.yaml.version:v1}")
    String version;
    /**
     * 从docker中拉取镜像方式
     */
    @Value("${istio.docker.image.pull:IfNotPresent}")
    String pullPolicy;
    /**
     * 路由支持
     */
    @Value("${istio.yaml.destination.support:false}")
    boolean destinationSupport;
    /**
     * 版本v1的权重
     */
    @Value("${istio.yaml.destination.v1:50}")
    Integer ratioV1;
    /**
     * 版本v2的权重
     */
    @Value("${istio.yaml.destination.v2:50}")
    Integer ratioV2;
    /**
     * 路由文件前缀
     */
    private static final String ALL_PREFIX = "all";

    /**
     * Prometheus文件前缀
     */
    private static final String PART_PREFIX = "part";
    /**
     * istio master节点ip，所用的服务都以这里为入口
     */
    @Value("${istio.master.ip:127.0.0.1}")
    private String masterIp;


    @Value("${istio.yaml.policy.maxConnections:2048}")
    private Integer maxConnections;
    @Value("${istio.yaml.policy.connectTimeout:3s}")
    private String connectTimeout;
    @Value("${istio.yaml.policy.http1MaxPendingRequests:1024}")
    private Integer http1MaxPendingRequests;
    @Value("${istio.yaml.policy.maxRequestsPerConnection:200}")
    private Integer maxRequestsPerConnection;
    @Value("${istio.yaml.policy.consecutiveErrors:3}")
    private Integer consecutiveErrors;
    @Value("${istio.yaml.policy.interval:3s}")
    private String interval;
    @Value("${istio.yaml.policy.baseEjectionTime:3m}")
    private String baseEjectionTime;
    @Value("${istio.yaml.policy.maxEjectionPercent:100}")
    private Integer maxEjectionPercent;
    @Value("${istio.yaml.policy.maxRetries:3}")
    private Integer maxRetries;
    @Value("${istio.yaml.policy.http2MaxRequests:2048}")
    private Integer http2MaxRequests;


    @Autowired
    RpcServerConfigProp rpcServerConfigProp;
    /**
     * prometheus job_name统一后缀
     */
    private static final String PROMETHEUS_SUFFIX = "prometheus";

    /**
     * prometheus METRICS 地址
     */
    private static final String PROMETHEUS_METRICS_PATH = "/actuator/prometheus";

    /**
     * prometheus.io/scrape,这里用false，istio默认的比如jaeger这个值是true
     * 如果设置成true，会归类到系统自带的prometheus job kubernetes-pods-istio-secure不方便
     * 查看并报非https的错误，因为kubernetes-pods-istio-secure 默认是https的
     */
    private static final boolean PROMETHEUS_IO_SCRAPE_FALSE = false;

    @Override
    public Service buildService() {
        Service service = new Service();
        service.setApiVersion(ApiVersion.v1.getValue());
        service.setKind(YamlKind.Service.getValue());
        Metadata sMetadata = new Metadata();
        sMetadata.setName(applicationName);
        sMetadata.setNamespace(istioNamespace);
        Labels sLabels = new Labels();
        sLabels.setApp(applicationName);
        sLabels.setService(applicationName);
        sMetadata.setLabels(sLabels);
        ServiceSpec serviceSpec = new ServiceSpec();
        Ports sPorts = new Ports();
        sPorts.setPort(serverPort);
        sPorts.setName(applicationName);
        serviceSpec.setPorts(new ArrayList() {{
            add(sPorts);
        }});
        Selector sSelector = new Selector();
        sSelector.setApp(applicationName);
        serviceSpec.setSelector(sSelector);
        service.setMetadata(sMetadata);
        service.setSpec(serviceSpec);
        return service;
    }

    @Override
    public Deployment buildDeployment() {
        Deployment deployment = new Deployment();
        deployment.setApiVersion(ApiVersion.appsv1.getValue());
        deployment.setKind(YamlKind.Deployment.getValue());
        Metadata dMetadata = new Metadata();
        dMetadata.setName(applicationName + "-" + version);
        dMetadata.setNamespace(istioNamespace);
        Labels meLabels = new Labels();
        meLabels.setVersion(version);
        meLabels.setApp(applicationName);
        dMetadata.setLabels(meLabels);
        MatchLabels matchLabels = new MatchLabels();
        matchLabels.setApp(applicationName);
        matchLabels.setVersion(version);
        DeploymentSpecSelector selector = new DeploymentSpecSelector();
        selector.setMatchLabels(matchLabels);
        DeploymentSpec deploymentSpec = new DeploymentSpec();
        deploymentSpec.setReplicas(replicas);
        deploymentSpec.setSelector(selector);
        Template template = new Template();
        Metadata tMetadata = new Metadata();
        tMetadata.setAnnotations(getAnnotations());
        TemplateSpec templateSpec = new TemplateSpec();
        Labels tLabels = new Labels();
        tLabels.setApp(applicationName);
        tLabels.setVersion(version);
        tMetadata.setLabels(tLabels);
        Containers containers = new Containers();
        containers.setName(applicationName);
        String dockerImage = PomUtil.getDockerInfo().getDockerImageTag();
        containers.setImage(dockerImage);
        if (pullPolicy.equals(DockerPull.IfNotPresent.getValue())) {
            containers.setImagePullPolicy(DockerPull.IfNotPresent.getValue());
        } else if (pullPolicy.equals(DockerPull.Always.getValue())) {
            containers.setImagePullPolicy(DockerPull.Always.getValue());
        } else if (pullPolicy.equals(DockerPull.Never.getValue())) {
            containers.setImagePullPolicy(DockerPull.Never.getValue());
        } else {
            containers.setImagePullPolicy(DockerPull.IfNotPresent.getValue());
        }
        Ports tsPorts = new Ports();
        tsPorts.setContainerPort(serverPort);
        containers.setPorts(new ArrayList() {{
            add(tsPorts);
        }});
        List<HostAliases> hostAliasesList = new ArrayList();
        if (rpcServerConfigProp != null && rpcServerConfigProp.getServer() != null) {
            rpcServerConfigProp.getServer().forEach((k, v) -> {
                HostAliases hostAliases = new HostAliases();
                try {///etc/hosts 里面不能用http什么的，所以这里需要获得host
                    URI uri = new URI(v.toString());
                    hostAliases.setHostnames(new ArrayList() {
                        {
                            add(uri.getHost());
                        }
                    });
                } catch (URISyntaxException e) {
                    hostAliases.setHostnames(new ArrayList() {
                        {
                            add(v);
                        }
                    });
                }
                hostAliases.setIp(masterIp);
                hostAliasesList.add(hostAliases);
            });
        }
        templateSpec.setHostAliases(hostAliasesList);
        templateSpec.setContainers(new ArrayList() {{
            add(containers);
        }});
        template.setMetadata(tMetadata);
        template.setSpec(templateSpec);
        deploymentSpec.setTemplate(template);
        deployment.setSpec(deploymentSpec);
        deployment.setMetadata(dMetadata);
        return deployment;
    }

    private Map<String, Object> getAnnotations() {
        Map<String, Object> annotations = new HashMap(5) {{
            put(PrometheusIo.PATH.getValue(), PROMETHEUS_METRICS_PATH);
            put(PrometheusIo.PORT.getValue(), serverPort);
            put(PrometheusIo.CRAPE.getValue(), PROMETHEUS_IO_SCRAPE_FALSE);
        }};
        return annotations;
    }

    @Override
    public Gateway buildGateway() {
        Gateway gateway = new Gateway();
        gateway.setApiVersion(ApiVersion.v1alpha3.getValue());
        gateway.setKind(YamlKind.Gateway.getValue());
        Metadata gMetadata = new Metadata();
        gMetadata.setName(applicationName);
        gMetadata.setNamespace(istioNamespace);
        GatewatSpec gatewatSpec = new GatewatSpec();
        Selector selector = new Selector();
        selector.setIstio(Istio.ingressgateway.getValue());
        gatewatSpec.setSelector(selector);


        Ports servicesPort = new Ports();
        servicesPort.setNumber(CloudBaseConstant.INGRESS_DEFAULT_PORT);
        servicesPort.setName(HttpProtocol.HTTP.getValue().toLowerCase());
        servicesPort.setProtocol(HttpProtocol.HTTP.getValue());
        Servers servers = new Servers();
        servers.setPort(servicesPort);
        List serverList = new ArrayList();
        serverList.add(servers);

        if (HttpProtocol.HTTPS.getValue().toLowerCase().equals(schema.toLowerCase())) {
            Ports servicesPortHttps = new Ports();
            servicesPortHttps.setNumber(CloudBaseConstant.INGRESS_DEFAULT_HTTPS_PORT);
            servicesPortHttps.setName(HttpProtocol.HTTPS.getValue().toLowerCase());
            servicesPortHttps.setProtocol(HttpProtocol.HTTPS.getValue());
            Tls tls = new Tls();
            tls.setNode(TlsNode.SIMPLE.getValue());
            tls.setPrivateKey(privateKey);
            tls.setServerCertificate(serverCertificate);

            Servers serversHttps = new Servers();
            serversHttps.setPort(servicesPortHttps);
            serversHttps.setTls(tls);
            serverList.add(serversHttps);
        }
        gatewatSpec.setServers(serverList);
        servers.setHosts(new ArrayList() {{
            add("*." + applicationName + CloudBaseConstant.WEBSITE_SUFFIX);
        }});
        gateway.setMetadata(gMetadata);
        gateway.setSpec(gatewatSpec);
        return gateway;
    }

    @Override
    public VirtualService buildVirtualService() {
        VirtualService virtualService = new VirtualService();
        virtualService.setApiVersion(ApiVersion.v1alpha3.getValue());
        virtualService.setKind(YamlKind.VirtualService.getValue());
        Metadata vsMetadata = new Metadata();
        vsMetadata.setName(applicationName);
        vsMetadata.setNamespace(istioNamespace);
        virtualService.setMetadata(vsMetadata);
        VirtualServiceSpec virtualServiceSpec = new VirtualServiceSpec();
        virtualServiceSpec.setHosts(new ArrayList() {{
            add(CloudBaseConstant.WEBSITE_PREFIX + applicationName + CloudBaseConstant.WEBSITE_SUFFIX);
        }});
        virtualServiceSpec.setGateways(new ArrayList() {{
            add(applicationName);
        }});
        Http http = new Http();
//        Match match = new Match();
//        Uri uri = new Uri();
//        uri.setExact(CloudBaseConstant.VIRTUAL_SERVICE_EXAC);
//        match.setUri(uri);
//        http.setPrefix(new ArrayList() {{
//            add(match);
//        }});
        http.setTimeout(CloudBaseConstant.ISTIO_SERVICE_TIMEOUT);

        Retries retries = new Retries();
        retries.setAttempts(maxRetries);
        retries.setPerTryTimeout(CloudBaseConstant.ISTIO_SERVICE_PERTRYTIMEOUT);
        http.setRetries(retries);
        if (destinationSupport) {
            //金丝雀测试，灰度发布
            Route routeV1 = new Route();
            Destination destinationV1 = new Destination();
            destinationV1.setHost(applicationName);
            Ports desPortsV1 = new Ports();
            desPortsV1.setNumber(serverPort);
            destinationV1.setPort(desPortsV1);
            destinationV1.setSubset(CloudBaseConstant.VERSION_V1);
            routeV1.setDestination(destinationV1);
            routeV1.setWeight(ratioV1);

            Route routeV2 = new Route();
            Destination destinationV2 = new Destination();
            destinationV2.setHost(applicationName);
            Ports desPortsV2 = new Ports();
            desPortsV2.setNumber(serverPort);
            destinationV2.setPort(desPortsV2);
            destinationV2.setSubset(CloudBaseConstant.VERSION_V2);
            routeV2.setDestination(destinationV2);
            routeV2.setWeight(ratioV2);

            http.setRoute(new ArrayList() {{
                add(routeV1);
                add(routeV2);
            }});

        } else {
            Route route = new Route();
            Destination destination = new Destination();
            destination.setHost(applicationName);
            Ports desPorts = new Ports();
            desPorts.setNumber(serverPort);
            destination.setPort(desPorts);
            destination.setSubset(CloudBaseConstant.VERSION_V1);
            route.setDestination(destination);
            http.setRoute(new ArrayList() {{
                add(route);
            }});
        }
        virtualServiceSpec.setHttp(new ArrayList() {{
            add(http);
        }});
        virtualService.setSpec(virtualServiceSpec);
        return virtualService;
    }

    @Override
    public Prometheus buildPrometheus() {
        Prometheus prometheus = new Prometheus();
        PrometheusJob prometheusJob = new PrometheusJob();
        prometheusJob.setJob_name(applicationName + "-" + PROMETHEUS_SUFFIX);
        prometheus.setScrape_configs(new ArrayList() {{
            add(prometheusJob);
        }});
        return prometheus;
    }

    @Override
    public DestinationRule buildDestinationRule() {
        DestinationRule destinationRule = new DestinationRule();
        destinationRule.setApiVersion(ApiVersion.v1alpha3.getValue());
        destinationRule.setKind(YamlKind.DestinationRule.getValue());
        Metadata metadata = new Metadata();
        metadata.setName(applicationName);
        metadata.setNamespace(istioNamespace);
        destinationRule.setMetadata(metadata);
        DestinationRuleSpec destinationRuleSpec = new DestinationRuleSpec();
        destinationRuleSpec.setHost(applicationName);
        Tcp tcp = new Tcp();
        tcp.setMaxConnections(maxConnections);
        tcp.setConnectTimeout(connectTimeout);
        Http http = new Http();
        http.setHttp1MaxPendingRequests(http1MaxPendingRequests);
        http.setMaxRequestsPerConnection(maxRequestsPerConnection);
        http.setHttp2MaxRequests(http2MaxRequests);
        http.setMaxRetries(maxRetries);
        ConnectionPool connectionPool = new ConnectionPool();
        connectionPool.setHttp(http);
        connectionPool.setTcp(tcp);
        OutlierDetection outlierDetection = new OutlierDetection();
        outlierDetection.setBaseEjectionTime(baseEjectionTime);
        outlierDetection.setConsecutiveErrors(consecutiveErrors);
        outlierDetection.setInterval(interval);
        outlierDetection.setMaxEjectionPercent(maxEjectionPercent);
        TrafficPolicy trafficPolicy = new TrafficPolicy();
        trafficPolicy.setConnectionPool(connectionPool);
        trafficPolicy.setOutlierDetection(outlierDetection);
        destinationRuleSpec.setTrafficPolicy(trafficPolicy);
        if (destinationSupport) {
            //生成两个版本的subset，目前只需要支持两种就行
            List subsetslist = new ArrayList();
            Subsets subsetsV1 = new Subsets();
            subsetsV1.setName(CloudBaseConstant.VERSION_V1);
            Labels labelsV1 = new Labels();
            labelsV1.setVersion(CloudBaseConstant.VERSION_V1);
            subsetsV1.setLabels(labelsV1);
            subsetslist.add(subsetsV1);
            Subsets subsetsV2 = new Subsets();
            subsetsV2.setName(CloudBaseConstant.VERSION_V2);
            Labels labelsV2 = new Labels();
            labelsV2.setVersion(CloudBaseConstant.VERSION_V2);
            subsetsV2.setLabels(labelsV2);
            subsetslist.add(subsetsV2);
            destinationRuleSpec.setSubsets(subsetslist);
        } else {
            List subsetslist = new ArrayList();
            Subsets subsetsV1 = new Subsets();
            subsetsV1.setName(CloudBaseConstant.VERSION_V1);
            Labels labelsV1 = new Labels();
            labelsV1.setVersion(CloudBaseConstant.VERSION_V1);
            subsetsV1.setLabels(labelsV1);
            subsetslist.add(subsetsV1);
            destinationRuleSpec.setSubsets(subsetslist);
        }
        destinationRule.setSpec(destinationRuleSpec);
        return destinationRule;
    }

    @Override
    public void createDeploy() {
        if (!version.equals(CloudBaseConstant.VERSION_V1) && !version.equals(CloudBaseConstant.VERSION_V2)) {
            version = CloudBaseConstant.VERSION_V1;
        }
        List<Object> iDeploy = new ArrayList<>();
        if (version.equals(CloudBaseConstant.VERSION_V2)) {
            iDeploy.add(this.buildDeployment());
        } else {
            iDeploy.add(this.buildService());
            iDeploy.add(this.buildDeployment());
        }
        YamlBuild yamlBuild = new YamlBuild();
        yamlBuild.setCover(yamlCover);
        yamlBuild.setData(iDeploy);
        yamlBuild.setPath(CloudBaseConstant.YAML_FILE_PATH);
        yamlBuild.setName(version + "-" + YamlType.IDeploy.getValue() + "-" + applicationName + CloudBaseConstant.YAML_SUFFIX);
        YamlUtil.buildYaml(yamlBuild);
    }

    @Override
    public void createGateway() {
        if (!version.equals(CloudBaseConstant.VERSION_V1) && !version.equals(CloudBaseConstant.VERSION_V2)) {
            version = CloudBaseConstant.VERSION_V1;
        }
        List<Object> iDeploy = new ArrayList<>();
        iDeploy.add(this.buildGateway());
        iDeploy.add(this.buildVirtualService());
        iDeploy.add(this.buildDestinationRule());
        YamlBuild yamlBuild = new YamlBuild();
        yamlBuild.setCover(yamlCover);
        yamlBuild.setData(iDeploy);
        yamlBuild.setPath(CloudBaseConstant.YAML_FILE_PATH);
        yamlBuild.setName(ALL_PREFIX + "-" + YamlType.IGateway.getValue() + "-" + applicationName + CloudBaseConstant.YAML_SUFFIX);
        YamlUtil.buildYaml(yamlBuild);
    }

    @Override
    public void createPrometheus() {
        List<Object> iPrometheus = new ArrayList<>();
        iPrometheus.add(this.buildPrometheus());
        YamlBuild yamlBuild = new YamlBuild();
        yamlBuild.setCover(yamlCover);
        yamlBuild.setData(iPrometheus);
        yamlBuild.setPath(CloudBaseConstant.YAML_FILE_PATH);
        yamlBuild.setName(PART_PREFIX + "-" + YamlType.IPrometheus.getValue() + "-" + applicationName + CloudBaseConstant.YAML_SUFFIX);
        YamlUtil.buildYaml(yamlBuild);
    }


    /**
     * @describe: 灰度发布
     * @param: [http]
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/29 11:34
     */
    private void destinationRule(Http http) {
        //灰度发布
        if (destinationSupport) {
            //金丝雀测试，灰度发布
            Route routeV1 = new Route();
            Destination destinationV1 = new Destination();
            destinationV1.setHost(applicationName);
            Ports desPortsV1 = new Ports();
            desPortsV1.setNumber(serverPort);
            destinationV1.setPort(desPortsV1);
            destinationV1.setSubset(CloudBaseConstant.VERSION_V1);
            routeV1.setDestination(destinationV1);
            routeV1.setWeight(ratioV1);

            Route routeV2 = new Route();
            Destination destinationV2 = new Destination();
            destinationV2.setHost(applicationName);
            Ports desPortsV2 = new Ports();
            desPortsV2.setNumber(serverPort);
            destinationV2.setPort(desPortsV2);
            destinationV2.setSubset(CloudBaseConstant.VERSION_V2);
            routeV2.setDestination(destinationV2);
            routeV2.setWeight(ratioV2);

            http.setRoute(new ArrayList() {{
                add(routeV1);
                add(routeV2);
            }});

        } else {
            Route route = new Route();
            Destination destination = new Destination();
            destination.setHost(applicationName);
            Ports desPorts = new Ports();
            desPorts.setNumber(serverPort);
            destination.setPort(desPorts);
            route.setDestination(destination);
            http.setRoute(new ArrayList() {{
                add(route);
            }});
        }
    }

    /**
     * @describe: 流量复制
     * @param: []
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/29 11:33
     */
    private void mirror(Http http) {

    }

    private void fault(Http http) {

    }
}
