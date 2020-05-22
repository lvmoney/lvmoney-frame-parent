package com.zhy.frame.cache.lock.interceptor;/**
 * 描述:
 * 包名:com.zhy.frame.cache.lock.interceptor
 * 版本信息: 版本1.0
 * 日期:2020/5/19
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.base.core.util.JsonUtil;
import com.zhy.frame.base.core.util.SupportUtil;
import com.zhy.frame.cache.caffeine.service.CaffeineService;
import com.zhy.frame.cache.common.exception.CacheException;
import com.zhy.frame.cache.lock.annotion.HotRequest;
import com.zhy.frame.cache.lock.constant.LockConstant;
import com.zhy.frame.cache.lock.service.DistributedLockerService;
import com.zhy.frame.cache.lock.service.HotRequestService;
import com.zhy.frame.cache.lock.utils.ParamUtil;
import com.zhy.frame.cache.redis.constant.RedisConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/19 9:06
 */
public class HotRequestInterceptor extends HandlerInterceptorAdapter {
    /**
     * 服务名
     */
    @Value("${spring.application.name:lvmoney}")
    private String serverName;
    private static final Logger LOGGER = LoggerFactory.getLogger(HotRequestInterceptor.class);

    @Value("${frame.redis.hotRequest.support:false}")
    private boolean hotRequestSupport;
    @Autowired
    HotRequestService hotRequestService;

    @Autowired
    CaffeineService caffeineService;

    @Autowired
    DistributedLockerService distributedLockerService;

    /**
     * 获得热点服务的数据
     * 1、如果能够通过 caffeine获得数据直接返回数据不再进行后续流程
     * 2、同一请求（url和参数一样的请求）计数器的变更加了分布式锁，这里会当caffeine里面没有数据同时马上就达到阀值的某些请求会到控制器获得数据
     * 3、2中的考虑是一个极端考虑，在高热点访问就算在caffeine没有数据某些请求发到控制器也是可以接受的。
     *
     * @param httpServletRequest:
     * @param httpServletResponse:
     * @param object:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/19 17:34
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                             Object object) throws Exception {
        if (!SupportUtil.support(hotRequestSupport)) {
            throw new BusinessException(CacheException.Proxy.REDIS_HOT_REQUEST_SUPPORT_ERROR);
        } else if (BaseConstant.SUPPORT_FALSE_BOOL == hotRequestSupport) {
            return super.preHandle(httpServletRequest, httpServletResponse, object);
        }
        HandlerMethod handlerMethod;
        try {
            handlerMethod = (HandlerMethod) object;
        } catch (Exception e) {
            return super.preHandle(httpServletRequest, httpServletResponse, object);
        }
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(HotRequest.class)) {
            HotRequest hotRequest = method.getAnnotation(HotRequest.class);
            if (!hotRequest.required()) {
                return super.preHandle(httpServletRequest, httpServletResponse, object);
            } else {
                if (!hotRequest.required()) {
                    return super.preHandle(httpServletRequest, httpServletResponse, object);
                }
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                String servletPath = request.getServletPath();
                Object obj = caffeineService.get(LockConstant.HOT_REQUEST_CAFFEINE_CACHE_NAME, RedisConstant.HOT_REQUEST_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + serverName + BaseConstant.CONNECTOR_UNDERLINE + servletPath + BaseConstant.CONNECTOR_UNDERLINE + ParamUtil.buildParam(getReqVo(request)));
                if (obj != null) {
                    HttpServletResponse httpResponse = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
                    httpResponse.setContentType("application/json;charset=utf-8");
                    httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
                    String json = JsonUtil.t2JsonString(obj);
                    try {
                        httpResponse.getWriter().print(json);
                        return false;
                    } catch (IOException e) {
                        LOGGER.error("其他错误处理response返回处理报错：{}", e.getMessage());
                    }
                } else {
                    return super.preHandle(httpServletRequest, httpServletResponse, object);
                }

            }
        } else {
            return super.preHandle(httpServletRequest, httpServletResponse, object);
        }
        return super.preHandle(httpServletRequest, httpServletResponse, object);
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }

    /**
     * 获得请求参数k-v
     *
     * @param request:
     * @throws
     * @return: java.util.Map
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/19 17:32
     */
    private Map getReqVo(HttpServletRequest request) {
        Map reqVo = new HashMap(BaseConstant.MAP_DEFAULT_SIZE);
        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String value = enumeration.nextElement();
            String v = request.getParameter(value);
            if (!LockConstant.JSON_EMPTY_VALUE.equals(v)) {
                reqVo.put(value, request.getParameter(value));
            }
        }
        return reqVo;
    }

    /**
     * 获得每个请求统一分的布式锁key
     *
     * @param request:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/19 17:32
     */
    private String getLockKey(HttpServletRequest request) {
        String servletPath = request.getServletPath();
        return RedisConstant.HOT_REQUEST_INTERCEPTOR_LOCK_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + serverName + BaseConstant.CONNECTOR_UNDERLINE + servletPath + BaseConstant.CONNECTOR_UNDERLINE + ParamUtil.buildParam(getReqVo(request));
    }

}
