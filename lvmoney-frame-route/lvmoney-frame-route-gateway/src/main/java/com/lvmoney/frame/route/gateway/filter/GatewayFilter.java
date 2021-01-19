package com.lvmoney.frame.route.gateway.filter;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway.filter
 * 版本信息: 版本1.0
 * 日期:2019/8/8
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.authentication.api.ao.ShiroCheckAo;
import com.lvmoney.frame.authentication.api.vo.JwtCheckVo;
import com.lvmoney.frame.authentication.api.vo.ShiroCheckVo;
import com.lvmoney.frame.authentication.service.service.UserCommonService;
import com.lvmoney.frame.authentication.util.util.JwtUtil;
import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.base.core.util.SupportUtil;
import com.lvmoney.frame.cache.common.exception.CacheException;
import com.lvmoney.frame.cache.lock.service.DistributedLockerService;
import com.lvmoney.frame.cloud.common.service.AuthorizedService;
import com.lvmoney.frame.cloud.common.vo.AuthorizedVo;
import com.lvmoney.frame.core.enums.InternalService;
import com.lvmoney.frame.core.util.IpUtil;
import com.lvmoney.frame.core.util.SignUtil;
import com.lvmoney.frame.core.vo.ServerInfo;
import com.lvmoney.frame.core.vo.SignVo;
import com.lvmoney.frame.core.vo.UserVo;
import com.lvmoney.frame.route.gateway.constant.GatewayConstant;
import com.lvmoney.frame.route.gateway.enums.NoRepeatSubmitEnum;
import com.lvmoney.frame.route.gateway.exception.GatewayException;
import com.lvmoney.frame.route.gateway.feign.IAuthorityCheckClient;
import com.lvmoney.frame.route.gateway.service.Gateway2RedisService;
import com.lvmoney.frame.route.gateway.service.RepeatSubmitService;
import com.lvmoney.frame.route.gateway.service.ServerService;
import com.lvmoney.frame.route.gateway.service.WhiteListService;
import com.lvmoney.frame.route.gateway.utils.ExceptionUtil;
import com.lvmoney.frame.route.gateway.vo.RepeatSubmitVo;
import com.lvmoney.frame.route.gateway.vo.WhiteListVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.concurrent.TimeUnit;
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
    Gateway2RedisService gateway2RedisService;
    @Autowired
    WhiteListService whiteListService;
    @Value("${frame.white.support:false}")
    String whiteSupport;

    @Value("${frame.allowable.support:false}")
    boolean allowableSupport;

    /**
     * 简单路由
     */
    @Value("${frame.simpleRoute.support:false}")
    boolean simpleRouteSupport;
    @Value("${frame.jwt.support:false}")
    boolean jwtSupport;

    @Value("${frame.shiro.support:false}")
    boolean shiroSupport;


    @Value("${frame.releaseServer.support:false}")
    boolean releaseServerSupport;

    @Value("${frame.repeatSubmit.support:false}")
    private String repeatSupport;

    private static final String LOCALHOST_NAME = "localhost";
    /**
     * 开发local本地环境以http或者https开头，http包含了https
     */
    private static final String INTERNAL_SERVICE_PREFIX = "http://";

    /**
     * istio环境以www开头
     */
    private static final String EXTERNAL_SERVICE_PREFIX = "www.";
    /**
     * nacos 环境以lb开头
     */
    private static final String NACOS_SERVICE_PREFIX = "lb://";
    /**
     * 去掉https请求路径的后缀，主要是istio环境下
     */
    private static final String HTTPS_SUFFIX_PORT = ":443";
    @Autowired
    ServerService serverService;
    @Autowired
    AuthorizedService authorizedService;
    @Autowired
    UserCommonService userCommonService;
    @Autowired
    IAuthorityCheckClient iAuthorityCheckClient;
    @Autowired
    RepeatSubmitService repeatSubmitService;

    @Value("${repeatsubmit.time:10}")
    private int repeatSubmitTime;
    /**
     * json空值
     */
    private static final String JSON_EMPTY_VALUE = "{}";

    @Autowired
    DistributedLockerService distributedLockerService;

    /**
     * @describe:1、直接能够访问的接口都是一些常用、不重要、不需要校验的接口 考虑到很多情况都是遭遇到非信任系统的调用攻击，所以需要校验发起调用的服务是否已被授权调用
     * 2、同一个网络环境的内部服务间调用通过白名单校验，白名单校验通过，就可以直接访问
     * 外网用户的访问先到这里经过层层考验再转发到对应的服务
     * 3、基于多种情况的考虑，如果是jwt校验不需要shiro校验，需调用jwt校验服务。
     * 如果是需要shiro校验，那么必须开启jwt校验，需要调用一个服务即校验jwt也要校验shiro，
     * <p>
     * 1、是否需要gateway支持====>2、白名单校验=====>3、sysId是否被授权====>4、是否被允许调用======>5、权限校验=======>6、服务访问
     * ====》7、幂等性校验
     * @param: [exchange, chain]
     * @return: reactor.core.publisher.Mono<java.lang.Void>
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/20 11:40
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse serverHttpResponse = exchange.getResponse();
        //1、gateway start
        if (!SupportUtil.support(gatewaySupport)) {
            serverHttpResponse.setStatusCode(HttpStatus.CONFLICT);
            return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.GATEWAY_SUPPORT_ERROR))));
        } else if (gatewaySupport.equals(BaseConstant.SUPPORT_FALSE)) {
            return chain.filter(exchange);
        }
        //2、是否是简单的路由 start
        Route route = exchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        ServerInfo serverInfo = getServerInfo(route);
        if (!SupportUtil.support(simpleRouteSupport)) {
            serverHttpResponse.setStatusCode(HttpStatus.CONFLICT);
            return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.SIMPLE_ROUTE_SUPPORT_ERROR))));
        } else if (isSimpleRoute(simpleRouteSupport, serverInfo)) {
            // 在这里做判断
            return chain.filter(exchange);
        }
        //3、白名单 start
        if (!SupportUtil.support(whiteSupport)) {
            serverHttpResponse.setStatusCode(HttpStatus.CONFLICT);
            return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.WHITE_SUPPORT_ERROR))));
        } else if (!isWhite(exchange, serverInfo)) {
            serverHttpResponse.setStatusCode(HttpStatus.CONFLICT);
            return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.GATEWAY_WHITE_CHECK_ERROR))));
        }
        //4、是否被允许调用 start
        String realPath = realPath(exchange);
        if (!isRelease(realPath, serverInfo)) {
            serverHttpResponse.setStatusCode(HttpStatus.CONFLICT);
            return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.GATEWAY_INTERNAL_CHECK_ERROR))));
        }
        String token = exchange.getRequest().getHeaders().getFirst(BaseConstant.AUTHORIZATION_TOKEN_KEY);
        UserVo userVo = new UserVo();
        try {
            //由于gateway和普通的servlet请求不同，通过throw的方式抛错不得行，所以需做如下处理
            userVo = userCommonService.getUserVo(token);
        } catch (Exception e) {
            userVo = null;
        }
        //5、系统id是否被允许访问 start
        if (ObjectUtils.isEmpty(userVo)) {
            token = "";
        }
        if (!SupportUtil.support(allowableSupport)) {
            serverHttpResponse.setStatusCode(HttpStatus.CONFLICT);
            return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.ALLOWABLE_SUPPORT_ERROR))));
        } else if (BaseConstant.SUPPORT_TRUE_BOOL == allowableSupport) {
            boolean result = isAllowable(token, serverInfo, realPath);
            if (!result) {
                serverHttpResponse.setStatusCode(HttpStatus.CONFLICT);
                return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.ALLOWABLE_SYS_ID_NOT_EXIST))));
            }
        }
        //6、jwt校验 start
        if (!SupportUtil.support(jwtSupport)) {
            serverHttpResponse.setStatusCode(HttpStatus.CONFLICT);
            return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.JWT_SUPPORT_ERROR))));
        } else if (!isJwt(realPath, token, serverInfo)) {
            serverHttpResponse.setStatusCode(HttpStatus.CONFLICT);
            return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.TOKEN_CHECK_ERROR))));
        }
        //7、shiro校验 start
        if (!SupportUtil.support(shiroSupport)) {
            serverHttpResponse.setStatusCode(HttpStatus.CONFLICT);
            return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.SHIRO_SUPPORT_ERROR))));
        } else if (!isShiro(realPath, token, serverInfo)) {
            serverHttpResponse.setStatusCode(HttpStatus.CONFLICT);
            return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.SHIRO_CHECK_ERROR))));
        }
        //8、重复提交
        if (!SupportUtil.support(repeatSupport)) {
            serverHttpResponse.setStatusCode(HttpStatus.CONFLICT);
            return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.REPEAT_SUBMIT_SUPPORT_ERROR))));
        } else if (BaseConstant.SUPPORT_FALSE.equals(repeatSupport)) {
            return chain.filter(exchange);
        } else {
            if (isRepeatSubmit(token, realPath, exchange, serverInfo)) {
                serverHttpResponse.setStatusCode(HttpStatus.CONFLICT);
                return serverHttpResponse.writeWith(Flux.just(ExceptionUtil.filterExceptionHandle(serverHttpResponse, new BusinessException(GatewayException.Proxy.REPEAT_SUBMIT_ERROR))));
            }
        }
        return chain.filter(exchange);
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
    private boolean isWhite(ServerWebExchange exchange, ServerInfo serverInfo) {
        String serverName = serverInfo.getServerName();
        if (whiteSupport.equals(BaseConstant.SUPPORT_FALSE)) {
            //不需要白名单支持
            return true;
        } else if (BaseConstant.SUPPORT_TRUE.equals(gatewaySupport)
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
     * @param realPath:
     * @param serverInfo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/17 17:05
     */
    private boolean isRelease(String realPath, ServerInfo serverInfo) {
        if (!releaseServerSupport) {
            //如果不需要支持直接返回true
            return true;
        } else {
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

        if (routeUrl.contains(LOCALHOST_NAME)) {
            String ip = IpUtil.getLocalhostIp();
            routeUrl = routeUrl.replace(LOCALHOST_NAME, ip);
        }
        if (routeUrl.contains(HTTPS_SUFFIX_PORT)) {
            routeUrl = routeUrl.replaceAll(HTTPS_SUFFIX_PORT, "");
        }
        ServerInfo serverInfo = new ServerInfo();
        if (routeUrl.startsWith(INTERNAL_SERVICE_PREFIX)) {
            //本地开发测试环境
            serverInfo = serverService.getServerInfo(routeUrl);

        } else if (routeUrl.startsWith(EXTERNAL_SERVICE_PREFIX)) {
            //istio 环境
            serverInfo = serverService.getServerInfo(route.getUri());
        } else if (routeUrl.startsWith(NACOS_SERVICE_PREFIX)) {
            //nacos 环境
            serverInfo = serverService.getServerInfo(routeUrl);

        }

        return serverInfo;
    }

    /**
     * 1、从redis获得请求的路径是否不需要校验
     * 2、当前用户token校验,调用远程服务
     *
     * @param path:
     * @param token:
     * @param serverInfo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/3/8 16:08
     */
    private boolean isJwt(String path, String token, ServerInfo serverInfo) {
        if (!jwtSupport) {
            //如果不需要支持直接返回true
            return true;
        } else {
            if (serverInfo.getNotToken().contains(path)) {
                return true;
            }
            if (StringUtils.isEmpty(token)) {
                return false;
            }
            ApiResult<JwtCheckVo> apiResult = iAuthorityCheckClient.checkToken(token);

            if (apiResult.getData().isResult()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 1、从redis获得请求的路径是否不需要校验
     * 2、判断当前用户是否被允许调用请求的url,调用远程服务
     *
     * @param path:
     * @param token:
     * @param serverInfo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/3/8 16:09
     */
    private boolean isShiro(String path, String token, ServerInfo serverInfo) {
        if (!shiroSupport) {
            //如果不需要支持直接返回true
            return true;
        } else {
            if (serverInfo.getNotAuthority().contains(path)) {
                return true;
            }
            if (StringUtils.isEmpty(token)) {
                return false;
            }
            ShiroCheckAo shiroCheckAo = new ShiroCheckAo(path, token);
            ApiResult<ShiroCheckVo> apiResult = iAuthorityCheckClient.checkAuthority(shiroCheckAo);
            if (apiResult.getData().isResult()) {
                return true;
            }
        }
        return false;
    }

    /**
     * 从redis中校验是否被sysId能访问
     * 如果是不需要权限校验的url，直接返回校验通过。
     *
     * @param token:
     * @param serverInfo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/3/8 16:10
     */
    private boolean isAllowable(String token, ServerInfo serverInfo, String path) {
        //如果用户没有登录且在在不需要鉴权的数据中
        if (serverInfo.getNotAuthority().contains(path) || serverInfo.getNotToken().contains(path)) {
            return true;
        }
        String serverName = serverInfo.getServerName();
        AuthorizedVo authorizedVo = authorizedService.getSysIdByServer(serverName);
        if (StringUtils.isEmpty(token)) {
            return false;
        }
        UserVo userVo = JwtUtil.getUserVo(token);
        return authorizedVo.getSysId().contains(userVo.getSysId());
    }

    /**
     * 判断某个路由是否是简单的route，没有Serverinfo 的route被认为是简单的route
     * 如果serverinfo 的simpleRoute=false也被认为是简单的route
     * 如果是直接路由，如果不是就行后续的各种校验
     *
     * @param simpleRouteSupport:
     * @param serverInfo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/3/14 21:24
     */
    private boolean isSimpleRoute(boolean simpleRouteSupport, ServerInfo serverInfo) {
        if (simpleRouteSupport) {
            if (ObjectUtils.isEmpty(serverInfo)) {
                return true;
            } else if (serverInfo.isSimpleRoute()) {
                return true;
            } else {
                return false;
            }
        } else {
            if (ObjectUtils.isEmpty(serverInfo)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 幂等性校验
     * 如果token为空直接返回不是重复提交
     * 如果在实体中没有获得重复提交的策略直接返回不是重复提交
     * 一种以时间来锁请求的url，一种更严格加时间来锁请求的url+请求的数据
     * 锁的时间可以通过请求的实体配置，如果时间没有配置那就以配置的全局时间为准
     *
     * @param token:
     * @param realPath:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/7/16 9:10
     */
    private boolean isRepeatSubmit(String token, String realPath, ServerWebExchange exchange, ServerInfo serverInfo) {
        if (StringUtils.isEmpty(token)) {
            return false;
        }

        String serverName = serverInfo.getServerName();
        RepeatSubmitVo repeatSubmitVo = getRepeatSubmitVoByPath(serverName, realPath);
        if (ObjectUtils.isEmpty(repeatSubmitVo)) {
            return false;
        } else {
            ServerHttpRequest request = exchange.getRequest();
            String ip = request.getURI().getHost();
            String lockKey = token + BaseConstant.CONNECTOR_UNDERLINE + realPath + BaseConstant.CONNECTOR_UNDERLINE + ip;

            if (NoRepeatSubmitEnum.ALLOW.equals(repeatSubmitVo.getRepeatSubmitEnum())) {
                return false;
            } else if (NoRepeatSubmitEnum.NOT_ALLOW.equals(repeatSubmitVo.getRepeatSubmitEnum())) {
                /**
                 * 这里对非file请求，进行签名，生成唯一的hash值，用来作为lockKey，以达到我们请求参数不同被认为是不同的请求
                 */
                String data = JsonUtil.t2JsonString(request.getQueryParams());
                data = JSON_EMPTY_VALUE.equals(data) ? serverName : data;
                SignVo signVo = new SignVo();
                signVo.setData(data);
                lockKey = lockKey + BaseConstant.CONNECTOR_UNDERLINE + SignUtil.signature(token, signVo);
            }
            int submitTime = 0;
            if (repeatSubmitVo.getRepeatSubmitTime() == null) {
                submitTime = repeatSubmitTime;
            } else {
                submitTime = repeatSubmitVo.getRepeatSubmitTime();
            }
            boolean lock = distributedLockerService.tryLock(lockKey, TimeUnit.SECONDS, 1, submitTime);
            if (lock) {
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * 获取请求路径的策略
     *
     * @param serverName:
     * @param path:
     * @throws
     * @return: com.lvmoney.frame.route.gateway.vo.RepeatSubmitVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/7/16 9:57
     */
    private RepeatSubmitVo getRepeatSubmitVoByPath(String serverName, String path) {
        List<RepeatSubmitVo> repeatSubmitVoList = repeatSubmitService.getRepeatSubmitVoByServerName(serverName);
        try {
            return repeatSubmitVoList.stream().filter(s -> s.getPath().equals(path)).findFirst().get();
        } catch (Exception e) {
            return null;
        }
    }


}
