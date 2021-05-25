package com.lvmoney.frame.cloud.base.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/8/19
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.cloud.base.constant.CloudBaseConstant;
import com.lvmoney.frame.cloud.base.enums.*;
import com.lvmoney.frame.cloud.base.properties.RpcServerConfigProp;
import com.lvmoney.frame.cloud.base.service.YamlService;
import com.lvmoney.frame.cloud.base.util.PomUtil;
import com.lvmoney.frame.core.util.YamlUtil;
import com.lvmoney.frame.cloud.base.vo.jyaml.*;
import com.lvmoney.frame.core.vo.YamlBuildVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import static com.lvmoney.frame.base.core.constant.BaseConstant.DASH_LINE;

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
     * 版本v2的权重
     */
    @Value("${istio.yaml.resource.limits.cpu:2048}")
    Integer limitsCpu;

    /**
     * limits的memory
     */
    @Value("${istio.yaml.resource.limits.memory:2048}")
    Integer limitsMemory;


    /**
     * requests的cpu
     */
    @Value("${istio.yaml.resource.requests.cpu:1024}")
    Integer requestsCpu;

    /**
     * requests的memory
     */
    @Value("${istio.yaml.resource.requests.memory:1024}")
    Integer requestsMemory;


    @Value("${istio.docker.imagePullSecrets:regsecret}")
    String imagePullSecrets;
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

    @Value("${istio.yaml.skywalking.InitContainersImage:192.168.10.59:8080/frame/skywalking-agent:v1}")
    private String skywalkingInitContainersImage;

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
    /**
     * 默认cpu 单位 m
     */
    private static final String DEFAULT_CPU_UNIT = "m";

    /**
     * 默认cpu 单位 m
     */
    private static final String DEFAULT_MEMORY_UNIT = "Mi";
    /**
     * env LIMITS_MEMORY
     */
    private static final String DEFAULT_LIMITS_MEMORY_NAME = "LIMITS_MEMORY";

    /**
     * env JAVA_OPTS
     */
    private static final String DEFAULT_JAVA_OPTS_NAME = "JAVA_OPTS";

    /**
     * k8s pod 中java默认参数
     */
    private static final String DEFAULT_JAVA_OPS_VALUE = "-Xmx$(" + DEFAULT_LIMITS_MEMORY_NAME + ")m -XshowSettings:vm -Duser.timezone=Asia/Shanghai";
    /**
     * 整合skywaking initContainers name;
     */
    private static final String SKYWALKING_CONTAINER_NAME = "sidecar";
    /**
     * cp
     */
    private static final String ININT_CONTAINER_COMMAND_CP = "cp";

    /**
     * -r
     */
    private static final String ININT_CONTAINER_COMMAND_R = "-r";

    /**
     * 镜像内skywaking agent 目录
     */
    private static final String ININT_CONTAINER_COMMAND_AGENT_SOURCE = "/opt/skywalking/agent";


    /**
     * 新镜像拷贝目录
     */
    private static final String ININT_CONTAINER_COMMAND_AGENT_TARGET = "/sidecar";

    /**
     * https访问时 secret 名称后缀
     */
    private static final String HTTPS_GATEWAY_SECRET_SUFFIX = "-certs";


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
        dMetadata.setName(applicationName + DASH_LINE + version);
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
        VolumeMounts volumeMounts = new VolumeMounts();
        volumeMounts.setMountPath(BaseConstant.BACKSLASH + SKYWALKING_CONTAINER_NAME);
        volumeMounts.setName(SKYWALKING_CONTAINER_NAME);
        Containers containers = new Containers();
        containers.setVolumeMounts(new ArrayList() {{
            add(volumeMounts);
        }});
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

        Resources resources = new Resources();

        Requests requests = new Requests();
        requests.setCpu(requestsCpu + DEFAULT_CPU_UNIT);
        requests.setMemory(requestsMemory + DEFAULT_MEMORY_UNIT);
        Limits limits = new Limits();
        limits.setCpu(limitsCpu + DEFAULT_CPU_UNIT);
        limits.setMemory(limitsMemory + DEFAULT_MEMORY_UNIT);
        resources.setLimits(limits);
        resources.setRequests(requests);
        containers.setResources(resources);


        ResourceFieldRef resourceFieldRef = new ResourceFieldRef();
        resourceFieldRef.setDivisor("1Mi");
        resourceFieldRef.setResource("limits.memory");
        Env evn = new Env();
        evn.setName(DEFAULT_LIMITS_MEMORY_NAME);
        ValueFrom valueFrom = new ValueFrom();
        valueFrom.setResourceFieldRef(resourceFieldRef);
        evn.setValueFrom(valueFrom);
        Env javaOps = new Env();
        javaOps.setName(DEFAULT_JAVA_OPTS_NAME);
        javaOps.setValue(DEFAULT_JAVA_OPS_VALUE);

        containers.setEnv(new ArrayList() {{
            add(evn);
            add(javaOps);
        }});

        Ports tsPorts = new Ports();
        tsPorts.setContainerPort(serverPort);
        containers.setPorts(new ArrayList() {{
            add(tsPorts);
        }});
        List<HostAliases> hostAliasesList = new ArrayList();
        if (rpcServerConfigProp != null && rpcServerConfigProp.getServer() != null) {
            rpcServerConfigProp.getServer().forEach((k, v) -> {
                HostAliases hostAliases = new HostAliases();
                List<String> hostnames = new ArrayList<>();
                try {
                    //etc/hosts 里面不能用http什么的，所以这里需要获得host
                    URI uri = new URI(v.toString());
                    if (ObjectUtils.isNotEmpty(uri.getHost())) {
                        hostnames.add(uri.getHost());
                    } else {
                        hostnames.add(v.toString());
                    }

                } catch (URISyntaxException e) {
                    hostnames.add(v.toString());
                }
                hostAliases.setIp(masterIp);
                hostAliases.setHostnames(hostnames);
                hostAliasesList.add(hostAliases);
            });
        }
        templateSpec.setHostAliases(hostAliasesList);
        templateSpec.setContainers(new ArrayList() {{
            add(containers);
        }});
        ImagePullSecrets secrets = new ImagePullSecrets();
        secrets.setName(imagePullSecrets);
        templateSpec.setImagePullSecrets(new ArrayList() {{
            add(secrets);
        }});

        List<InitContainers> initContainersList = new ArrayList();
        VolumeMounts initContainersVolumeMounts = new VolumeMounts();
        initContainersVolumeMounts.setMountPath(BaseConstant.BACKSLASH + SKYWALKING_CONTAINER_NAME);
        initContainersVolumeMounts.setName(SKYWALKING_CONTAINER_NAME);

        InitContainers initContainers = new InitContainers();
        initContainers.setVolumeMounts(new ArrayList() {{
            add(initContainersVolumeMounts);
        }});
        initContainers.setName(SKYWALKING_CONTAINER_NAME);
        initContainers.setImage(skywalkingInitContainersImage);
        if (pullPolicy.equals(DockerPull.IfNotPresent.getValue())) {
            initContainers.setImagePullPolicy(DockerPull.IfNotPresent.getValue());
        } else if (pullPolicy.equals(DockerPull.Always.getValue())) {
            initContainers.setImagePullPolicy(DockerPull.Always.getValue());
        } else if (pullPolicy.equals(DockerPull.Never.getValue())) {
            initContainers.setImagePullPolicy(DockerPull.Never.getValue());
        } else {
            initContainers.setImagePullPolicy(DockerPull.IfNotPresent.getValue());
        }
        initContainers.setCommand(new ArrayList() {{
            add(ININT_CONTAINER_COMMAND_CP);
            add(ININT_CONTAINER_COMMAND_R);
            add(ININT_CONTAINER_COMMAND_AGENT_SOURCE);
            add(ININT_CONTAINER_COMMAND_AGENT_TARGET);

        }});
        initContainersList.add(initContainers);
        List<Volumes> volumesList = new ArrayList();
        Volumes volumes = new Volumes();
        volumes.setName(SKYWALKING_CONTAINER_NAME);
        volumes.setEmptyDir(new HashMap(BaseConstant.MAP_DEFAULT_SIZE));
        volumesList.add(volumes);
        templateSpec.setInitContainers(initContainersList);
        templateSpec.setVolumes(volumesList);
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

        List serverList = new ArrayList();

        Servers servers = new Servers();
        servers.setHosts(new ArrayList() {{
            add("*." + applicationName + BaseConstant.WEBSITE_SUFFIX);
        }});
        if (HttpProtocol.HTTPS.getValue().toLowerCase().equals(schema.toLowerCase())) {
            Ports servicesPortHttps = new Ports();
            servicesPortHttps.setNumber(CloudBaseConstant.INGRESS_DEFAULT_HTTPS_PORT);
            servicesPortHttps.setName(HttpProtocol.HTTPS.getValue().toLowerCase());
            servicesPortHttps.setProtocol(HttpProtocol.HTTPS.getValue());
            Tls tls = new Tls();
            tls.setMode(TlsNode.SIMPLE.getValue());
//            tls.setPrivateKey(privateKey);
//            tls.setServerCertificate(serverCertificate);
            tls.setCredentialName(applicationName + HTTPS_GATEWAY_SECRET_SUFFIX);
            servers.setPort(servicesPortHttps);
            servers.setTls(tls);
            //为了满足https&http均可访问
            Servers httpServers = new Servers();
            httpServers.setHosts(new ArrayList() {{
                add("*." + applicationName + BaseConstant.WEBSITE_SUFFIX);
            }});
            Ports servicesPort = new Ports();
            servicesPort.setNumber(CloudBaseConstant.INGRESS_DEFAULT_PORT);
            servicesPort.setName(HttpProtocol.HTTP.getValue().toLowerCase());
            servicesPort.setProtocol(HttpProtocol.HTTP.getValue());
            httpServers.setPort(servicesPort);
            serverList.add(httpServers);
        } else {
            Ports servicesPort = new Ports();
            servicesPort.setNumber(CloudBaseConstant.INGRESS_DEFAULT_PORT);
            servicesPort.setName(HttpProtocol.HTTP.getValue().toLowerCase());
            servicesPort.setProtocol(HttpProtocol.HTTP.getValue());
            servers.setPort(servicesPort);
        }
        serverList.add(servers);
        gatewatSpec.setServers(serverList);
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
            add(BaseConstant.WEBSITE_PREFIX + applicationName + BaseConstant.WEBSITE_SUFFIX);
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
        prometheusJob.setJob_name(applicationName + DASH_LINE + PROMETHEUS_SUFFIX);
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
        YamlBuildVo yamlBuildVo = new YamlBuildVo();
        yamlBuildVo.setCover(yamlCover);
        yamlBuildVo.setData(iDeploy);
        yamlBuildVo.setPath(CloudBaseConstant.YAML_FILE_PATH);
        yamlBuildVo.setName(version + DASH_LINE + YamlType.IDeploy.getValue() + DASH_LINE + applicationName + CloudBaseConstant.YAML_SUFFIX);
        YamlUtil.buildYaml(yamlBuildVo);
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
        YamlBuildVo yamlBuildVo = new YamlBuildVo();
        yamlBuildVo.setCover(yamlCover);
        yamlBuildVo.setData(iDeploy);
        yamlBuildVo.setPath(CloudBaseConstant.YAML_FILE_PATH);
        yamlBuildVo.setName(ALL_PREFIX + DASH_LINE + YamlType.IGateway.getValue() + DASH_LINE + applicationName + CloudBaseConstant.YAML_SUFFIX);
        YamlUtil.buildYaml(yamlBuildVo);
    }

    @Override
    public void createPrometheus() {
        List<Object> iPrometheus = new ArrayList<>();
        iPrometheus.add(this.buildPrometheus());
        YamlBuildVo yamlBuildVo = new YamlBuildVo();
        yamlBuildVo.setCover(yamlCover);
        yamlBuildVo.setData(iPrometheus);
        yamlBuildVo.setPath(CloudBaseConstant.YAML_FILE_PATH);
        yamlBuildVo.setName(PART_PREFIX + DASH_LINE + YamlType.IPrometheus.getValue() + DASH_LINE + applicationName + CloudBaseConstant.YAML_SUFFIX);
        YamlUtil.buildYaml(yamlBuildVo);
    }

    @Override
    public void createDestinationRule() {
        List<Object> iDestinationRule = new ArrayList<>();
        DestinationRule destinationRule = this.buildDestinationRule();
        iDestinationRule.add(destinationRule);
        YamlBuildVo yamlBuildVo = new YamlBuildVo();
        yamlBuildVo.setCover(yamlCover);
        yamlBuildVo.setData(iDestinationRule);
        yamlBuildVo.setPath(CloudBaseConstant.YAML_FILE_PATH);
        yamlBuildVo.setName(version + DASH_LINE + YamlType.IDestinationRule.getValue() + DASH_LINE + applicationName + CloudBaseConstant.YAML_SUFFIX);
        YamlUtil.buildYaml(yamlBuildVo);
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
