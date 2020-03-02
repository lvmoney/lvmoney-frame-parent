package com.zhy.frame.log.server.aspect;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/31
 * Copyright 四川******科技有限公司
 */

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.zhy.frame.base.core.api.ApiResult;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.base.core.exception.CommonException;
import com.zhy.frame.log.server.annotation.ControllerLog;
import com.zhy.frame.log.server.annotation.NotLog;
import com.zhy.frame.log.server.constant.LogConstant;
import com.zhy.frame.log.server.service.LogService;
import com.zhy.frame.log.server.vo.ControllerVo;
import com.zhy.frame.log.server.vo.LogVo;
import com.zhy.frame.log.server.vo.ThrowableVo;
import com.zhy.frame.log.server.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.AbstractHandlerMethodMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Aspect
@Component
public class LogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private LogService logService;
    /**
     * 非token调用服务时应该记录的username，从request获得
     */
    @Value("${log.noToken.username:username}")
    private String username;
    LogVo logVo;

    @Value("${frame.log.support:false}")
    String logSupport;
    @Autowired
    AbstractHandlerMethodMapping abstractHandlerMethodMapping;


    /***
     * 定义controller切入点拦截规则，拦截SystemControllerLog注解的方法
     */
    @Pointcut("@annotation(com.zhy.frame.log.server.annotation.ControllerLog)")
    public void controllerAspect() {
    }

    /**
     * @describe: 拦截控制层的操作日志
     * 如果没有token，一般在登录的时候没有token的，那么需要记录参数中username的值
     * @param: [joinPoint]
     * @return: ResultData
     * @author： lvmoney /四川******科技有限公司
     * 2019/2/1
     */
    @Around("controllerAspect()")
    public ApiResult recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        logVo = new LogVo();
        Object proceed = null;
        proceed = joinPoint.proceed();
        //提取controller中ExecutionResult的属性
        ApiResult result = (ApiResult) proceed;
        if (LogConstant.FRAME_LOG_SUPPORT_FALSE.equals(logSupport)) {
            return result;
        } else if (!LogConstant.FRAME_LOG_SUPPORT_FALSE.equals(logSupport) && !LogConstant.FRAME_LOG_SUPPORT_TRUE.equals(logSupport)) {
            throw new BusinessException(CommonException.Proxy.LOG_SUPPORT_ERROR);
        }

        //获取session中的用户
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object object = abstractHandlerMethodMapping.getHandler(request).getHandler();
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        // 检查是否有NotToken注释，有则跳过认证
        if (method.isAnnotationPresent(NotLog.class)) {
            NotLog notLog = method.getAnnotation(NotLog.class);
            if (notLog.required()) {
                return result;
            }
        }
        // 从 http 请求头中取出
        String token = request.getHeader("token");
        String name = "";
        String userId = "";
        if (!StringUtils.isBlank(token)) {
            UserVo userVo = new UserVo();
            userVo.setToken(token);
            userVo = logService.getUser(userVo);
            if (userVo != null) {
                name = userVo.getUsername();
                userId = userVo.getUserId();
            }
        }
        if (StringUtils.isBlank(name)) {
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

        }
        logVo.setUsername(name);
        logVo.setUserId(userId);
        //获取请求的ip
        String ip = request.getRemoteAddr();
        logVo.setRequestIp(ip);
        //获取执行的方法名
        logVo.setActionMethod(joinPoint.getSignature().getName());
        //获取方法执行前时间
        Date date = new Date();
        logVo.setActionDate(date.getTime());
        ControllerVo controllerVo = getControllerMethodDescription(joinPoint);
        logVo.setDescription(controllerVo.getDescrption());
        logVo.setLogType(controllerVo.getActionType());
        logVo.setParams(getReturnParams(joinPoint));
        logService.saveLog(logVo);
        return result;
    }

    private String getReturnParams(ProceedingJoinPoint joinPoint) {
        Object[] params = joinPoint.getArgs();
        StringBuilder returnStr = new StringBuilder();
        for (Object param : params) {
            if (param instanceof String) {
                returnStr.append(params);
            } else if (param instanceof Integer) {
                returnStr.append(params);
            }
        }
        return returnStr.toString();
    }

    /**
     * @describe: 异常处理
     * @param: [joinPoint, e]
     * @return: void
     * @author： lvmoney /四川******科技有限公司
     * 2019/2/1
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {

        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Throwable.class, "code", "description", "message", "type");
        String throwableStr = JSONObject.toJSONString(e, filter);
        ThrowableVo throwableVo = JSONObject.parseObject(throwableStr, ThrowableVo.class);
        logVo.setExceptionCode(throwableVo.getCode());
        logVo.setExceptionDetail(throwableVo.getMessage());
        logVo.setLogType(throwableVo.getType());
        logService.saveLog(logVo);
    }


    /***
     * 获取controller的操作信息
     * @param point
     * @return
     */
    public ControllerVo getControllerMethodDescription(ProceedingJoinPoint point) throws Exception {
        //获取连接点目标类名
        String targetName = point.getTarget().getClass().getName();
        //获取连接点签名的方法名
        String methodName = point.getSignature().getName();
        //获取连接点参数
        Object[] args = point.getArgs();
        //根据连接点类的名字获取指定类
        Class targetClass = Class.forName(targetName);
        //获取类里面的方法
        Method[] methods = targetClass.getMethods();
        ControllerVo controllerVo = new ControllerVo();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == args.length) {
                    String description = method.getAnnotation(ControllerLog.class).descrption();
                    String actionType = method.getAnnotation(ControllerLog.class).actionType();
                    controllerVo.setDescrption(description);
                    controllerVo.setActionType(actionType);
                    break;
                }
            }
        }
        return controllerVo;
    }

}
