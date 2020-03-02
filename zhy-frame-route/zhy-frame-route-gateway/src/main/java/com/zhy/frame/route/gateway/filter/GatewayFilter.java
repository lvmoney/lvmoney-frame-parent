package com.zhy.frame.route.gateway.filter;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway.filter
 * 版本信息: 版本1.0
 * 日期:2019/8/8
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.core.enums.InternalService;
import com.zhy.frame.core.util.IpUtil;
import com.zhy.frame.core.vo.ServerInfo;
import com.zhy.frame.route.gateway.constant.GatewayConstant;
import com.zhy.frame.route.gateway.exception.GatewayException;
import com.zhy.frame.route.gateway.properties.GatewayConfigProp;
import com.zhy.frame.route.gateway.server.AuthenticationServerConfig;
import com.zhy.frame.route.gateway.service.Gateway2RedisService;
import com.zhy.frame.route.gateway.service.ServerService;
import com.zhy.frame.route.gateway.service.WhiteListService;
import com.zhy.frame.route.gateway.utils.ExceptionUtil;
import com.zhy.frame.route.gateway.utils.FilterMapUtil;
import com.zhy.frame.route.gateway.vo.WhiteListVo;
import com.zhy.frame.route.gateway.vo.resp.Oauth2TokenCheck;
import com.zhy.frame.route.gateway.vo.resp.ShiroAuthorityCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/8 17:14
 */
@Component
public class GatewayFilter implements GlobalFilter, Ordered {
    @Value("${frame.gateway.support:false}")
    String gatewaySupport;
    @Autowired
    private AuthenticationServerConfig authenticationServerConfig;
    @Autowired
    GatewayConfigProp gatewayConfigProp;
    @Autowired
    Gateway2RedisService gateway2RedisService;
    @Autowired
    WhiteListService whiteListService;
    @Value("${frame.white.support:false}")
    String whiteSupport;

    @Value("${frame.auth.support:false}")
    boolean authSupport;


    @Value("${frame.releaseServer.support:false}")
    boolean releaseServerSupport;

    private static final String LOCALHOST_NAME = "localhost";
    /**
     * 开发local本地环境以http或者https开头，http包含了https
     */
    private static final String INTERNAL_SERVICE_PREFIX = "http";

    /**
     * istio环境以www开头
     */
    private static final String EXTERNAL_SERVICE_PREFIX = "www";
    /**
     * 去掉https请求路径的后缀，主要是istio环境下
     */
    private static final String HTTPS_SUBFIX_PORT = ":443";
    @Autowired
    ServerService serverService;

    /**
     * @describe:1、是否需要gateway支持====>2、白名单校验=====>3、是否被允许调用======>4、token校验=======>5、权限校验=======>6、服务访问
     * @param: [exchange, chain]
     * @return: reactor.core.publisher.Mono<java.lang.Void>
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/20 11:40
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        if (gatewaySupport.equals(GatewayConstant.FRAME_GATEWAY_SUPPORT_FALSE)) {
            // 在这里做判断
            return chain.filter(exchange);
        } else if (!GatewayConstant.FRAME_GATEWAY_SUPPORT_FALSE.equals(gatewaySupport) && !GatewayConstant.FRAME_GATEWAY_SUPPORT_TRUE.equals(gatewaySupport)) {
            return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.GATEWAY_SUPPORT_ERROR))));
        }
        if (!whiteSupport.equals(GatewayConstant.FRAME_WHITE_SUPPORT_TRUE) && !whiteSupport.equals(GatewayConstant.FRAME_WHITE_SUPPORT_FALSE)) {
            return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.GATEWAY_SUPPORT_ERROR))));
        } else if (!isWhite(exchange)) {
            //白名单校验
            return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.GATEWAY_WHITE_CHECK_ERROR))));
        }
        String realPath = realPath(exchange);
        if (!isRelease(exchange, realPath)) {
            return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.GATEWAY_INTERNAL_CHECK_ERROR))));
        }
        String requestPath = exchange.getRequest().getPath().toString();


        if (GatewayConstant.FRAME_AUTH_SUPPORT_TRUE.equals(authSupport) && !GatewayConstant.FRAME_AUTH_SUPPORT_FALSE.equals(authSupport)) {
            return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.AUTH_SUPPORT_ERROR))));
        } else if (!authSupport) {
            return chain.filter(exchange);
        } else {
            Map<String, String> filterChainDefinition = gatewayConfigProp.getfilterChainDefinitionMap();
            /**
             * 判断真实的请求地址是否被允许直接访问，不需要鉴权
             */
            if (filterChainDefinition != null && FilterMapUtil.wildcardMatchMapKey(filterChainDefinition, requestPath, GatewayConstant.GATEWAY_REQUEST_IGNORE)) {
                return chain.filter(exchange);
            }
            String token = exchange.getRequest().getHeaders().getFirst("token");
            ApiResult<Oauth2TokenCheck> oauth2TokenCheck = authenticationServerConfig.authenticationServer(token).oauth2CheckToken();
            if (!oauth2TokenCheck.isSuccess() || !oauth2TokenCheck.getData().isAdopt()) {
                return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.GATEWAY_TOKEN_CHECK_ERROR))));
            }
            ApiResult<ShiroAuthorityCheck> shiroAuthorityCheck = authenticationServerConfig.authenticationServer(token).shiroCheckAuthority(realPath);
            if (!shiroAuthorityCheck.isSuccess() || !shiroAuthorityCheck.getData().isPass()) {
                return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.GATEWAY_SHIRO_CHECK_ERROR))));
            }

            return chain.filter(exchange);
        }
    }

    @Override
    public int getOrder() {
        return -9999;
    }

    /**
     * @describe:通过路由规则获得真正的请求的地址
     * @param: [exchange]
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/15 11:49
     */
    private String realPath(ServerWebExchange exchange) {

        Route route = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        String routeId = route.getId();
        RouteDefinition routeDefinition = gateway2RedisService.getRouteDefinition(routeId);
        String path = exchange.getRequest().getPath().toString();
        String newPath = "";
        final String[] skip = {""};
        routeDefinition.getFilters().stream()
                .filter(
                        e -> e.getName().equals(GatewayConstant.ROUTE_DEFINITION_FILTER)).limit(1).forEach(ex -> ex.getArgs().entrySet().forEach(map -> skip[0] = map.getValue()));
        Long step = Long.parseLong(skip[0]);
        newPath = "/" + (String) Arrays.stream(StringUtils.tokenizeToStringArray(path, "/")).skip(step).collect(Collectors.joining("/"));
        newPath = newPath + (newPath.length() > 1 && path.endsWith("/") ? "/" : "");
        return newPath;
    }

    /**
     * @describe: 1、判断请求ip是否在白名单，该校验用于内网环境服务可信任调度，白名单内的ip可访问
     * 2、如果是外部可访问的服务，那么该服务只能被外网访问，也就是白名单外的ip访问
     * 3、根据ServerInfo中的internalService进行判断
     * 4、需要白名单支持，但是服务名称不在redis中，说明该服务不需要白名单校验
     * @param: [exchange]
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/20 10:20
     */
    private boolean isWhite(ServerWebExchange exchange) {
        Route route = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        ServerInfo serverInfo = getServerInfo(route);
        String serverName = serverInfo.getServerName();
        if (whiteSupport.equals(GatewayConstant.FRAME_WHITE_SUPPORT_FALSE)) {
            //不需要白名单支持
            return true;
        } else if (GatewayConstant.FRAME_WHITE_SUPPORT_TRUE.equals(gatewaySupport)
                && !whiteListService.isExist(serverName)) {
            //需要白名单支持，但是服务名称不在redis中，说明该服务不需要白名单校验
            return true;
        }
        String internalService = serverInfo.getInternalService();
        if (InternalService.external.getValue().equals(internalService)) {
            //判断请求ip是否在白名单，该校验用于内网环境服务可信任调度，白名单内的ip可访问
            ServerHttpRequest request = exchange.getRequest();
            String ip = request.getURI().getHost();
            if (LOCALHOST_NAME.equals(ip) || ip.equals(BaseConstant.LOCALHOST_IP)) {
                ip = IpUtil.getLocalhostIp();
            }
            WhiteListVo whiteListVo = whiteListService.getWhiteList(serverName);
            boolean result = false;
            Set<String> white = whiteListVo.getNetworkSegment();
            for (
                    String e : white) {
                if (IpUtil.isInRange(ip, e)) {
                    result = true;
                    break;
                }

            }
            return result;
        } else if (InternalService.internal.getValue().equals(internalService)) {
            //如果是外部可访问的服务，那么该服务只能被外网访问，也就是白名单外的ip访问
            ServerHttpRequest request = exchange.getRequest();
            String ip = request.getURI().getHost();
            if (LOCALHOST_NAME.equals(ip) || ip.equals(BaseConstant.LOCALHOST_IP)) {
                ip = IpUtil.getLocalhostIp();
            }
            WhiteListVo whiteListVo = whiteListService.getWhiteList(serverName);
            boolean result = true;
            Set<String> white = whiteListVo.getNetworkSegment();
            for (String e : white) {
                if (IpUtil.isInRange(ip, e)) {
                    result = false;
                    break;
                }
            }
            return result;
        } else {
            return false;
        }


    }

    /**
     * 通过白名单校验后，判断访问的接口是否被允许其他服务调用
     * 1、首先判断是否支持该校验
     * 2、根据ServerInfo的internalService去判断是否是内网服务
     * 3、判断是否被允许访问
     *
     * @param exchange:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/17 17:05
     */
    private boolean isRelease(ServerWebExchange exchange, String realPath) {
        if (!releaseServerSupport) {
            //如果不需要支持直接返回true
            return true;
        } else {
            Route route = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
            ServerInfo serverInfo = getServerInfo(route);
            String internalService = serverInfo.getInternalService();
            if (InternalService.external.getValue().equals(internalService)) {
                //如果是外部c端可访问的全部放开
                return true;
            } else if (InternalService.internal.getValue().equals(internalService)) {
                //处理内部服务的相互调用
                Set<String> releaseServer = serverInfo.getReleaseServer();
                if (releaseServer.contains(realPath)) {
                    //如果请求的url在被公布的里面返回true
                    return true;
                }
                return false;
            } else {
                return false;
            }
        }
    }

    /**
     * 根据请求的地址获得服务信息
     *
     * @param route:
     * @throws
     * @return: com.lvmoney.common.vo.ServerInfo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/18 14:56
     */
    private ServerInfo getServerInfo(Route route) {
        String routeUrl = route.getUri().toString();
        if (routeUrl.contains(HTTPS_SUBFIX_PORT)) {
            routeUrl = routeUrl.replaceAll(HTTPS_SUBFIX_PORT, "");
        }
        ServerInfo serverInfo = new ServerInfo();
        if (routeUrl.startsWith(INTERNAL_SERVICE_PREFIX)) {
            //本地开发测试环境
            serverInfo = serverService.getServerInfo(routeUrl);

        } else if (routeUrl.startsWith(EXTERNAL_SERVICE_PREFIX)) {
            //istio 环境
            serverInfo = serverService.getServerInfo(route.getUri());
        }
        return serverInfo;
    }


}
