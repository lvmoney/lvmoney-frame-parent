package com.zhy.frame.cache.lock.aspect;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/31
 * Copyright 四川******科技有限公司
 */

import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.base.core.util.SupportUtil;
import com.zhy.frame.cache.caffeine.service.CaffeineService;
import com.zhy.frame.cache.common.exception.CacheException;
import com.zhy.frame.cache.lock.annotion.HotRequest;
import com.zhy.frame.cache.lock.constant.LockConstant;
import com.zhy.frame.cache.lock.ro.HotRequestRo;
import com.zhy.frame.cache.lock.service.DistributedLockerService;
import com.zhy.frame.cache.lock.service.HotRequestService;
import com.zhy.frame.cache.lock.utils.ParamUtil;
import com.zhy.frame.core.util.SpringBeanUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Aspect
@Component
public class HotRequestAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(HotRequestAspect.class);

    @Autowired
    HotRequestService hotRequestService;
    @Value("${frame.redis.hotRequest.support:false}")
    private boolean hotRequestSupport;
    @Autowired
    CaffeineService caffeineService;
    @Value("${spring.application.name:lvmoney}")
    private String serverName;
    @Autowired
    AbstractHandlerMethodMapping abstractHandlerMethodMapping;
    @Autowired
    DistributedLockerService distributedLockerService;

    /***
     * 定义controller切入点拦截规则，拦截SystemControllerLog注解的方法
     */
    @Pointcut("@annotation(com.zhy.frame.cache.lock.annotion.HotRequest)")
    private void controllerAspect() {
    }

    /**
     * @describe: 获得请求的参数和返回结果
     * 如果没有token，一般在登录的时候没有token的，那么需要记录参数中username的值
     * @param: [joinPoint]
     * @return: ResultData
     * @author： lvmoney /四川******科技有限公司
     * 2019/2/1
     */
    @Around("controllerAspect()")
    public Object recordHotRequest(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = SpringBeanUtil.getHttpServletRequest();
        Object object = abstractHandlerMethodMapping.getHandler(request).getHandler();
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        long threshold = LockConstant.HOT_REQUEST_THRESHOLD;
        int section = LockConstant.HOT_REQUEST_SECTION;
        long expired = LockConstant.HOT_REQUEST_EXPIRED;
        //返回值
        Object rep = joinPoint.proceed();
        if (method.isAnnotationPresent(HotRequest.class)) {
            HotRequest hotRequest = method.getAnnotation(HotRequest.class);
            if (!hotRequest.required()) {
                return rep;
            }
            threshold = hotRequest.threshold();
            section = hotRequest.section();
            expired = hotRequest.expired();
        }
        if (!SupportUtil.support(hotRequestSupport)) {
            throw new BusinessException(CacheException.Proxy.REDIS_HOT_REQUEST_SUPPORT_ERROR);
        } else if (BaseConstant.SUPPORT_FALSE_BOOL == hotRequestSupport) {
            return rep;
        }
        String servletPath = request.getServletPath();
        //请求值
        Object req = joinPoint.getArgs();
        HotRequestRo hotRequestRo = hotRequestService.getHotRequestRo(servletPath, ParamUtil.buildRequestMap(req));
        if (ObjectUtils.isEmpty(hotRequestRo)) {
            hotRequestRo = new HotRequestRo();
            hotRequestRo.setCounter(1L);
            hotRequestRo.setStart(System.currentTimeMillis());
            hotRequestRo.setUrl(servletPath);
            hotRequestRo.setQ(req);
            hotRequestRo.setR(rep);
            hotRequestRo.setExpired(expired);
            hotRequestService.save(hotRequestRo);
        } else {
            hotRequestRo.setQ(req);
            hotRequestRo.setR(rep);
            hotRequestRo.setExpired(expired);
            hotRequestService.update(hotRequestRo, threshold, section);
        }
        return rep;
    }
}
