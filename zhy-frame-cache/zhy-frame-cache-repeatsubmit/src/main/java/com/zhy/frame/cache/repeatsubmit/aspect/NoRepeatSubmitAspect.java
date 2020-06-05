package com.zhy.frame.cache.repeatsubmit.aspect;/**
 * 描述:
 * 包名:com.zhy.repeatsubmit.aspect
 * 版本信息: 版本1.0
 * 日期:2019/10/16
 * Copyright 四川******科技有限公司
 */


import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.base.core.util.JsonUtil;
import com.zhy.frame.base.core.util.SupportUtil;
import com.zhy.frame.cache.common.exception.CacheException;
import com.zhy.frame.cache.lock.service.DistributedLockerService;
import com.zhy.frame.cache.repeatsubmit.annotation.NoRepeatSubmit;
import com.zhy.frame.cache.repeatsubmit.constant.RepeatSubmitConstant;
import com.zhy.frame.cache.repeatsubmit.enums.NoRepeatSubmitEnum;
import com.zhy.frame.cache.repeatsubmit.properties.RepeatSubmitConfigProp;
import com.zhy.frame.core.util.FilterMapUtil;
import com.zhy.frame.core.util.SignUtil;
import com.zhy.frame.core.util.SpringBeanUtil;
import com.zhy.frame.core.vo.SignVo;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/10/16 17:58
 */
@Aspect
@Component
public class NoRepeatSubmitAspect {
    @Value("${frame.repeactsubmit.support:false}")
    private String repeatSupport;

    @Autowired
    AbstractHandlerMethodMapping abstractHandlerMethodMapping;

    /**
     * 非token调用服务时应该记录的username，从request获得
     */
    @Value("${log.noToken.username:username}")
    private String username;
    @Value("${repeactsubmit.time:10}")
    private int repeactsubmitTime;

    @Autowired
    DistributedLockerService distributedLockerService;

    @Autowired
    RepeatSubmitConfigProp repeatSubmitConfigProp;

    /**
     * json控制
     */
    private static final String JSON_EMPTY_VALUE = "{}";

    @Value("${spring.application.name:lvmoney}")
    private String serverName;

    /***
     * 定义controller切入点拦截规则，拦截所有的controller方法
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.PostMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.PatchMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.PutMapping)" +
            "||@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void controllerAspect() {
    }

    /**
     * 限制重复提交，判断同一个ip的用户在短时间内请求同一个地址的频率
     * 1、理论上讲应该通过请求数据进行判断，但是基于经验一般重复提交都是用户重复发起同一个请求（一般是insert）导致数据的错误
     * 2、通过时间来限制，可以防止某个单点频繁的请求服务器导致服务器压力
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("controllerAspect()")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed;

        if (!SupportUtil.support(repeatSupport)) {
            throw new BusinessException(CacheException.Proxy.REPEATSUBMIT_SUPPORT_ERROR);
        } else if (BaseConstant.SUPPORT_FALSE.equals(repeatSupport)) {
            proceed = joinPoint.proceed();
            return proceed;
        }


        //获取session中的用户
        HttpServletRequest request = SpringBeanUtil.getHttpServletRequest();
        String servletPath = request.getServletPath();
        Map<String, String> filterChainDefinition = repeatSubmitConfigProp.getFilterChainDefinitionMap();
        if (filterChainDefinition != null && FilterMapUtil.wildcardMatchMapKey(filterChainDefinition, servletPath, RepeatSubmitConstant.REPEAT_SUBMIT_REQUEST_IGNORE)) {
            proceed = joinPoint.proceed();
            return proceed;
        }


        Object object = abstractHandlerMethodMapping.getHandler(request).getHandler();
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        // 从 http 请求头中取出
        String token = request.getHeader(BaseConstant.AUTHORIZATION_TOKEN_KEY);
        String name = "";
        if (StringUtils.isBlank(token)) {
            try {
                String reqName = request.getParameter(username).toString();
                if (StringUtils.isBlank(reqName)) {
                    name = "user not logged";
                } else {
                    name = reqName;
                }
            } catch (Exception e) {
                //1、正常情况下，本系统间调用应该通过token，或者username来记录请求人信息
                //2、当三方系统调用平台时可能不会加token或者username，为了不抛错，所以要给它默认值
                name = "NotLogged";
            }

        } else {
            name = token;
        }
        String ip = request.getRemoteAddr();
        String lockKey = name + BaseConstant.CONNECTOR_UNDERLINE + request.getServletPath() + BaseConstant.CONNECTOR_UNDERLINE + ip;

        // 检查是否有NoRepeatSubmit注释
        if (method.isAnnotationPresent(NoRepeatSubmit.class)) {
            NoRepeatSubmit noRepeatSubmit = method.getAnnotation(NoRepeatSubmit.class);
            if (NoRepeatSubmitEnum.ALLOW.getRequired().equals(noRepeatSubmit.required().getRequired())) {
                proceed = joinPoint.proceed();
                //提取controller中ExecutionResult的属性
                return proceed;
            } else if (NoRepeatSubmitEnum.PARTLY.getRequired().equals(noRepeatSubmit.required().getRequired())) {
                /**
                 * 只要是同一个请求地址，就做了重复提交限制，体验不是很好
                 */
                repeactsubmitTime = noRepeatSubmit.time();

            } else {
                /**
                 * 这里对非file请求，进行签名，生成唯一的hash值，用来作为lockKey，以达到我们请求参数不同被认为是不同的请求
                 */
                String data = JsonUtil.t2JsonString(request.getParameterMap());
                data = JSON_EMPTY_VALUE.equals(data) ? serverName : data;
                SignVo signVo = new SignVo();
                signVo.setData(data);
                lockKey = lockKey + BaseConstant.CONNECTOR_UNDERLINE + SignUtil.signature(name, signVo);
                repeactsubmitTime = noRepeatSubmit.time();
            }
        }

        boolean lock = distributedLockerService.tryLock(lockKey, TimeUnit.SECONDS, 1, repeactsubmitTime);
        if (lock) {
            proceed = joinPoint.proceed();
            return proceed;
        } else {
            throw new BusinessException(CacheException.Proxy.REPEATSUBMIT_REQUEST_ERROR);
        }

    }


}
