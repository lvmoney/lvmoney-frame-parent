package com.zhy.frame.cloud.base.listener;/**
 * 描述:
 * 包名:com.zhy.k8s.base.handler
 * 版本信息: 版本1.0
 * 日期:2019/8/16
 * Copyright XXXXXX科技有限公司
 */

import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.base.core.enums.OperatingEnvironmentType;
import com.zhy.frame.cloud.base.annotations.NotAuthority;
import com.zhy.frame.cloud.base.annotations.NotToken;
import com.zhy.frame.cloud.base.annotations.ReleaseServer;
import com.zhy.frame.cloud.base.service.CloudBaseService;
import com.zhy.frame.core.ro.ServerBaseInfoRo;
import com.zhy.frame.core.vo.ServerInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/16 15:38
 */
@Component
public class ServerInfoListener implements ApplicationListener<WebServerInitializedEvent> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerInfoListener.class);
    private int serverPort;
    @Value("${spring.application.name}")
    private String serverName;
    @Value("${custom.tomcat.https.schema:https}")
    private String schema;
    @Autowired
    CloudBaseService baseService;
    @Value("${operating.environment:local}")
    String operatingEnvironment;
    @Value("${operating.internal:internal}")
    String operatingInternal;
    @Value("${operating.simpleRoute:false}")
    boolean simpleRoute;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    /**
     * 为当前服务构造基础数据，主要用在gateway中白名单，是否公布出去被访问等等
     *
     * @throws
     * @return: com.zhy.common.vo.ServerInfo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/17 16:59
     */
    public ServerInfo getServerInfo() {
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            LOGGER.error("获得访问address报错:{}", e.getMessage());
        }
        ServerInfo serverInfo = new ServerInfo();
        serverInfo.setIp(address.getHostAddress());
        serverInfo.setPort(this.serverPort);
        serverInfo.setServerName(BaseConstant.WEBSITE_PREFIX + this.serverName + BaseConstant.WEBSITE_SUFFIX);
        if (operatingEnvironment.equals(OperatingEnvironmentType.local.getValue())) {
            serverInfo.setUri(schema + "://" + address.getHostAddress() + ":" + this.serverPort + "/");
        } else if (operatingEnvironment.equals(OperatingEnvironmentType.istio.getValue())) {
            serverInfo.setUri(schema + "://" + BaseConstant.WEBSITE_PREFIX + this.serverName + BaseConstant.WEBSITE_SUFFIX + "/");
        } else if (operatingEnvironment.equals(OperatingEnvironmentType.nacos.getValue())) {
            serverInfo.setUri(BaseConstant.NACOS_PREFIX + this.serverName);
        }
        serverInfo.setHttpScheme(this.schema);
        serverInfo.setReleaseServer(this.getReleaseServer());
        serverInfo.setNotAuthority(this.getNotAuthority());
        serverInfo.setNotToken(this.getNotToken());
        serverInfo.setInternalService(operatingInternal);
        serverInfo.setSimpleRoute(simpleRoute);
        ServerBaseInfoRo serverBaseInfoRo = new ServerBaseInfoRo();
        Map<String, ServerInfo> stringServerInfoMap = new HashMap(BaseConstant.MAP_DEFAULT_SIZE) {{
            put(serverInfo.getServerName(), serverInfo);
        }};
        serverBaseInfoRo.setData(stringServerInfoMap);
        baseService.saveServerInfo(serverBaseInfoRo);
        return serverInfo;
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
        this.getServerInfo();
    }

    /**
     * 通过注解获得该服务被允许其他服务调用的接口
     *
     * @throws
     * @return: java.util.Set<java.lang.String>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/17 16:58
     */
    private Set<String> getReleaseServer() {
        Set<String> result = new HashSet();
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            RequestMappingInfo info = m.getKey();
            HandlerMethod handlerMethod = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            Method method = handlerMethod.getMethod();
            if (method.isAnnotationPresent(ReleaseServer.class)) {
                ReleaseServer shiroResouce = method.getAnnotation(ReleaseServer.class);
                if (shiroResouce.release() == true) {
                    for (String url : p.getPatterns()) {
                        result.add(url);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 通过注解获得该服务被允许其他服务调用的接口
     *
     * @throws
     * @return: java.util.Set<java.lang.String>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/17 16:58
     */
    private Set<String> getNotToken() {
        Set<String> result = new HashSet();
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            RequestMappingInfo info = m.getKey();
            HandlerMethod handlerMethod = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            Method method = handlerMethod.getMethod();
            if (method.isAnnotationPresent(NotToken.class)) {
                NotToken notToken = method.getAnnotation(NotToken.class);
                if (notToken.required() == true) {
                    for (String url : p.getPatterns()) {
                        result.add(url);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 通过注解获得该服务被允许其他服务调用的接口
     *
     * @throws
     * @return: java.util.Set<java.lang.String>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/17 16:58
     */
    private Set<String> getNotAuthority() {
        Set<String> result = new HashSet();
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            RequestMappingInfo info = m.getKey();
            HandlerMethod handlerMethod = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            Method method = handlerMethod.getMethod();
            if (method.isAnnotationPresent(NotAuthority.class)) {
                NotAuthority notAuthority = method.getAnnotation(NotAuthority.class);
                if (notAuthority.required() == true) {
                    for (String url : p.getPatterns()) {
                        result.add(url);
                    }
                }
            }
        }
        return result;
    }


}
