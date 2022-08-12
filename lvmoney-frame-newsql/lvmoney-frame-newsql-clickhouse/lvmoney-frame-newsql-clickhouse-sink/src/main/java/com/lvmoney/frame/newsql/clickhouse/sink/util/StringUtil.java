package com.lvmoney.frame.newsql.clickhouse.sink.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.newsql.clickhouse.sink.util
 * 版本信息: 版本1.0
 * 日期:2020/6/30
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lvmoney.frame.newsql.clickhouse.sink.constant.ClickHouseSinkConstant;
import com.lvmoney.frame.newsql.clickhouse.sink.vo.LogbackBodyVo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/30 20:27
 */
public class StringUtil {
    /**
     * 匹配字符串驼峰点正则表达式
     */
    private final static String DEFAULT_PATTERN = "[A-Z]|[1-9]";

    public static LogbackBodyVo buildLog(String log) {
        LogbackBodyVo logbackBodyVo = JSON.parseObject(JSON.parse(log).toString(), LogbackBodyVo.class);
        Long num = SnowflakeIdFactoryUtil.nextId();
        DateTimeFormatter df = DateTimeFormatter.ofPattern(ClickHouseSinkConstant.DATE_FORMAT);
        logbackBodyVo.setCreateDate(LocalDateTime.now().format(df));
        logbackBodyVo.setId(String.valueOf(num));
        return logbackBodyVo;
    }

    public static Map buildKafka(String log) {
        Map<String, Object> result = new HashMap(ClickHouseSinkConstant.DEFAULT_MAP_SIZE);
        JSONObject jsonObject = JSONObject.parseObject(JSON.parse(log).toString());
        result = (Map) jsonObject;
        Long num = SnowflakeIdFactoryUtil.nextId();
        DateTimeFormatter df = DateTimeFormatter.ofPattern(ClickHouseSinkConstant.DATE_FORMAT);
        result.put("createDate", LocalDateTime.now().format(df));
        result.put("id", String.valueOf(num));
        return result;
    }

    public static void main(String[] args) {
        System.out.println(JsonUtil.t2JsonString(StringUtil.buildLog("{\"sysName\":\"frameSimple\",\"thread\":\"http-nio-10000-exec-1\",\"exeDate\":\"2020-07-01 16:33:01\",\"level\":\"ERROR\",\"clazz\":\"com.lvmoney.frame.base.rm.advice.FrameExceptionHandlerAdvice\",\"msg\":\"base64 encoding Unsupported。error详情:{\\\"@type\\\":\\\"com.lvmoney.frame.base.core.exception.BusinessException\\\",\\\"code\\\":8006,\\\"description\\\":\\\"base64 encoding Unsupported\\\",\\\"localizedMessage\\\":\\\"base64 encoding Unsupported\\\",\\\"message\\\":\\\"base64 encoding Unsupported\\\",\\\"stackTrace\\\":[{\\\"className\\\":\\\"com.lvmoney.frame.demo.simple.controller.SimpleDemoController\\\",\\\"fileName\\\":\\\"SimpleDemoController.java\\\",\\\"lineNumber\\\":31,\\\"methodName\\\":\\\"error\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"sun.reflect.NativeMethodAccessorImpl\\\",\\\"fileName\\\":\\\"NativeMethodAccessorImpl.java\\\",\\\"lineNumber\\\":-2,\\\"methodName\\\":\\\"invoke0\\\",\\\"nativeMethod\\\":true},{\\\"className\\\":\\\"sun.reflect.NativeMethodAccessorImpl\\\",\\\"fileName\\\":\\\"NativeMethodAccessorImpl.java\\\",\\\"lineNumber\\\":62,\\\"methodName\\\":\\\"invoke\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"sun.reflect.DelegatingMethodAccessorImpl\\\",\\\"fileName\\\":\\\"DelegatingMethodAccessorImpl.java\\\",\\\"lineNumber\\\":43,\\\"methodName\\\":\\\"invoke\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"java.lang.reflect.Method\\\",\\\"fileName\\\":\\\"Method.java\\\",\\\"lineNumber\\\":498,\\\"methodName\\\":\\\"invoke\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.method.support.InvocableHandlerMethod\\\",\\\"fileName\\\":\\\"InvocableHandlerMethod.java\\\",\\\"lineNumber\\\":215,\\\"methodName\\\":\\\"doInvoke\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.method.support.InvocableHandlerMethod\\\",\\\"fileName\\\":\\\"InvocableHandlerMethod.java\\\",\\\"lineNumber\\\":142,\\\"methodName\\\":\\\"invokeForRequest\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.servlet.mvc.method.annotations.ServletInvocableHandlerMethod\\\",\\\"fileName\\\":\\\"ServletInvocableHandlerMethod.java\\\",\\\"lineNumber\\\":102,\\\"methodName\\\":\\\"invokeAndHandle\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.servlet.mvc.method.annotations.RequestMappingHandlerAdapter\\\",\\\"fileName\\\":\\\"RequestMappingHandlerAdapter.java\\\",\\\"lineNumber\\\":895,\\\"methodName\\\":\\\"invokeHandlerMethod\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.servlet.mvc.method.annotations.RequestMappingHandlerAdapter\\\",\\\"fileName\\\":\\\"RequestMappingHandlerAdapter.java\\\",\\\"lineNumber\\\":800,\\\"methodName\\\":\\\"handleInternal\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter\\\",\\\"fileName\\\":\\\"AbstractHandlerMethodAdapter.java\\\",\\\"lineNumber\\\":87,\\\"methodName\\\":\\\"handle\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.servlet.DispatcherServlet\\\",\\\"fileName\\\":\\\"DispatcherServlet.java\\\",\\\"lineNumber\\\":1038,\\\"methodName\\\":\\\"doDispatch\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.servlet.DispatcherServlet\\\",\\\"fileName\\\":\\\"DispatcherServlet.java\\\",\\\"lineNumber\\\":942,\\\"methodName\\\":\\\"doService\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.servlet.FrameworkServlet\\\",\\\"fileName\\\":\\\"FrameworkServlet.java\\\",\\\"lineNumber\\\":998,\\\"methodName\\\":\\\"processRequest\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.servlet.FrameworkServlet\\\",\\\"fileName\\\":\\\"FrameworkServlet.java\\\",\\\"lineNumber\\\":890,\\\"methodName\\\":\\\"doGet\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"javax.servlet.http.HttpServlet\\\",\\\"fileName\\\":\\\"HttpServlet.java\\\",\\\"lineNumber\\\":634,\\\"methodName\\\":\\\"service\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.servlet.FrameworkServlet\\\",\\\"fileName\\\":\\\"FrameworkServlet.java\\\",\\\"lineNumber\\\":875,\\\"methodName\\\":\\\"service\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"javax.servlet.http.HttpServlet\\\",\\\"fileName\\\":\\\"HttpServlet.java\\\",\\\"lineNumber\\\":741,\\\"methodName\\\":\\\"service\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.core.ApplicationFilterChain\\\",\\\"fileName\\\":\\\"ApplicationFilterChain.java\\\",\\\"lineNumber\\\":231,\\\"methodName\\\":\\\"internalDoFilter\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.core.ApplicationFilterChain\\\",\\\"fileName\\\":\\\"ApplicationFilterChain.java\\\",\\\"lineNumber\\\":166,\\\"methodName\\\":\\\"doFilter\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.tomcat.websocket.server.WsFilter\\\",\\\"fileName\\\":\\\"WsFilter.java\\\",\\\"lineNumber\\\":53,\\\"methodName\\\":\\\"doFilter\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.core.ApplicationFilterChain\\\",\\\"fileName\\\":\\\"ApplicationFilterChain.java\\\",\\\"lineNumber\\\":193,\\\"methodName\\\":\\\"internalDoFilter\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.core.ApplicationFilterChain\\\",\\\"fileName\\\":\\\"ApplicationFilterChain.java\\\",\\\"lineNumber\\\":166,\\\"methodName\\\":\\\"doFilter\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.filter.CharacterEncodingFilter\\\",\\\"fileName\\\":\\\"CharacterEncodingFilter.java\\\",\\\"lineNumber\\\":200,\\\"methodName\\\":\\\"doFilterInternal\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.filter.OncePerRequestFilter\\\",\\\"fileName\\\":\\\"OncePerRequestFilter.java\\\",\\\"lineNumber\\\":107,\\\"methodName\\\":\\\"doFilter\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.core.ApplicationFilterChain\\\",\\\"fileName\\\":\\\"ApplicationFilterChain.java\\\",\\\"lineNumber\\\":193,\\\"methodName\\\":\\\"internalDoFilter\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.core.ApplicationFilterChain\\\",\\\"fileName\\\":\\\"ApplicationFilterChain.java\\\",\\\"lineNumber\\\":166,\\\"methodName\\\":\\\"doFilter\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.core.StandardWrapperValve\\\",\\\"fileName\\\":\\\"StandardWrapperValve.java\\\",\\\"lineNumber\\\":199,\\\"methodName\\\":\\\"invoke\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.core.StandardContextValve\\\",\\\"fileName\\\":\\\"StandardContextValve.java\\\",\\\"lineNumber\\\":96,\\\"methodName\\\":\\\"invoke\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.authenticator.AuthenticatorBase\\\",\\\"fileName\\\":\\\"AuthenticatorBase.java\\\",\\\"lineNumber\\\":490,\\\"methodName\\\":\\\"invoke\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.core.StandardHostValve\\\",\\\"fileName\\\":\\\"StandardHostValve.java\\\",\\\"lineNumber\\\":139,\\\"methodName\\\":\\\"invoke\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.valves.ErrorReportValve\\\",\\\"fileName\\\":\\\"ErrorReportValve.java\\\",\\\"lineNumber\\\":92,\\\"methodName\\\":\\\"invoke\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.core.StandardEngineValve\\\",\\\"fileName\\\":\\\"StandardEngineValve.java\\\",\\\"lineNumber\\\":74,\\\"methodName\\\":\\\"invoke\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.connector.CoyoteAdapter\\\",\\\"fileName\\\":\\\"CoyoteAdapter.java\\\",\\\"lineNumber\\\":343,\\\"methodName\\\":\\\"service\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.coyote.http11.Http11Processor\\\",\\\"fileName\\\":\\\"Http11Processor.java\\\",\\\"lineNumber\\\":408,\\\"methodName\\\":\\\"service\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.coyote.AbstractProcessorLight\\\",\\\"fileName\\\":\\\"AbstractProcessorLight.java\\\",\\\"lineNumber\\\":66,\\\"methodName\\\":\\\"process\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.coyote.AbstractProtocol$ConnectionHandler\\\",\\\"fileName\\\":\\\"AbstractProtocol.java\\\",\\\"lineNumber\\\":770,\\\"methodName\\\":\\\"process\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.tomcat.util.net.NioEndpoint$SocketProcessor\\\",\\\"fileName\\\":\\\"NioEndpoint.java\\\",\\\"lineNumber\\\":1415,\\\"methodName\\\":\\\"doRun\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.tomcat.util.net.SocketProcessorBase\\\",\\\"fileName\\\":\\\"SocketProcessorBase.java\\\",\\\"lineNumber\\\":49,\\\"methodName\\\":\\\"run\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"java.util.concurrent.ThreadPoolExecutor\\\",\\\"fileName\\\":\\\"ThreadPoolExecutor.java\\\",\\\"lineNumber\\\":1149,\\\"methodName\\\":\\\"runWorker\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"java.util.concurrent.ThreadPoolExecutor$Worker\\\",\\\"fileName\\\":\\\"ThreadPoolExecutor.java\\\",\\\"lineNumber\\\":624,\\\"methodName\\\":\\\"run\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.tomcat.util.threads.TaskThread$WrappingRunnable\\\",\\\"fileName\\\":\\\"TaskThread.java\\\",\\\"lineNumber\\\":61,\\\"methodName\\\":\\\"run\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"java.lang.Thread\\\",\\\"fileName\\\":\\\"Thread.java\\\",\\\"lineNumber\\\":748,\\\"methodName\\\":\\\"run\\\",\\\"nativeMethod\\\":false}],\\\"type\\\":\\\"BASE64_ENCODING_ERROR\\\"}\"}")));

        System.out.println(humpToLine("createDate"));
    }

    /**
     * 驼峰命名转化成下划线
     *
     * @param str:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/7/22 11:20
     */
    public static String humpToLine(String str) {
        Pattern humpPattern = Pattern.compile(DEFAULT_PATTERN);
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}
