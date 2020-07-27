package com.zhy.frame.newsql.clickhouse.sink.sink;/**
 * 描述:
 * 包名:com.zhy.frame.newsql.clickhouse.sink.sink
 * 版本信息: 版本1.0
 * 日期:2020/6/30
 * Copyright XXXXXX科技有限公司
 */

import com.zhy.frame.newsql.clickhouse.sink.util.JsonUtil;
import com.zhy.frame.newsql.clickhouse.sink.util.StringUtil;
import com.zhy.frame.newsql.clickhouse.sink.vo.LogbackBodyVo;
import ru.yandex.clickhouse.BalancedClickhouseDataSource;
import ru.yandex.clickhouse.ClickHouseConnectionImpl;
import ru.yandex.clickhouse.ClickHouseStatement;
import ru.yandex.clickhouse.domain.ClickHouseFormat;
import ru.yandex.clickhouse.settings.ClickHouseProperties;
import ru.yandex.clickhouse.settings.ClickHouseQueryParam;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.zhy.frame.newsql.clickhouse.sink.constant.ClickHouseSinkConstant.*;
import static com.zhy.frame.newsql.clickhouse.sink.constant.ClickHouseSinkConstant.CLICK_HOUSE_PREFIX;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/30 9:17
 */
public class ClickHouseUtil {

    private BalancedClickhouseDataSource dataSource = null;
    private String host = "10.20.128.235";
    private String port = "8123";
    private String user = "default";
    private String password = "";
    private String database = "default";
    private String table = "sys_log";

    public void start() {
        if (!this.host.startsWith(CLICK_HOUSE_PREFIX)) {
            this.host = CLICK_HOUSE_PREFIX + this.host;
        }
        String jdbcUrl = String.format("%s:%s/%s", this.host, this.port, this.database);
        ClickHouseProperties properties = new ClickHouseProperties().withCredentials(this.user, this.password);
        //properties.setUseServerTimeZone(false);
        this.dataSource = new BalancedClickhouseDataSource(jdbcUrl, properties);
    }

    public void exec(String input) {
        start();
        ClickHouseConnectionImpl conn = null;
        try {
            conn = (ClickHouseConnectionImpl) dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<LogbackBodyVo> insertData = new ArrayList<>();
        insertData.add(StringUtil.buildLog(input));

        ClickHouseStatement sth = null;
        try {
            sth = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String inputString = JsonUtil.t2JsonString(insertData);
            sth.write().table(String.format(" %s.%s", database, table)).data(new ByteArrayInputStream(inputString.getBytes()), ClickHouseFormat.JSONEachRow).addDbParam(ClickHouseQueryParam.MAX_PARALLEL_REPLICAS, MAX_PARALLEL_REPLICAS_VALUE).send();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ClickHouseUtil clickHouseUtil = new ClickHouseUtil();
//        clickHouseUtil.exec("[{\"id\":1111,\"name\":\"chenxi\",\"value\":\"lvmoney\",\"create_date\":\"2020-06-29\"},{\"id\":2222,\"name\":\"chenxi\",\"value\":\"lvmoney\",\"create_date\":\"2020-06-29\"}]");
        clickHouseUtil.exec("{\"sysName\":\"frameSimple\",\"thread\":\"http-nio-10000-exec-1\",\"exeDate\":\"2020-07-01 16:33:01\",\"level\":\"ERROR\",\"clazz\":\"com.zhy.frame.base.rm.advice.FrameExceptionHandlerAdvice\",\"msg\":\"base64 encoding Unsupported。error详情:{\\\"@type\\\":\\\"com.zhy.frame.base.core.exception.BusinessException\\\",\\\"code\\\":8006,\\\"description\\\":\\\"base64 encoding Unsupported\\\",\\\"localizedMessage\\\":\\\"base64 encoding Unsupported\\\",\\\"message\\\":\\\"base64 encoding Unsupported\\\",\\\"stackTrace\\\":[{\\\"className\\\":\\\"com.zhy.frame.demo.simple.controller.SimpleDemoController\\\",\\\"fileName\\\":\\\"SimpleDemoController.java\\\",\\\"lineNumber\\\":31,\\\"methodName\\\":\\\"error\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"sun.reflect.NativeMethodAccessorImpl\\\",\\\"fileName\\\":\\\"NativeMethodAccessorImpl.java\\\",\\\"lineNumber\\\":-2,\\\"methodName\\\":\\\"invoke0\\\",\\\"nativeMethod\\\":true},{\\\"className\\\":\\\"sun.reflect.NativeMethodAccessorImpl\\\",\\\"fileName\\\":\\\"NativeMethodAccessorImpl.java\\\",\\\"lineNumber\\\":62,\\\"methodName\\\":\\\"invoke\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"sun.reflect.DelegatingMethodAccessorImpl\\\",\\\"fileName\\\":\\\"DelegatingMethodAccessorImpl.java\\\",\\\"lineNumber\\\":43,\\\"methodName\\\":\\\"invoke\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"java.lang.reflect.Method\\\",\\\"fileName\\\":\\\"Method.java\\\",\\\"lineNumber\\\":498,\\\"methodName\\\":\\\"invoke\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.method.support.InvocableHandlerMethod\\\",\\\"fileName\\\":\\\"InvocableHandlerMethod.java\\\",\\\"lineNumber\\\":215,\\\"methodName\\\":\\\"doInvoke\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.method.support.InvocableHandlerMethod\\\",\\\"fileName\\\":\\\"InvocableHandlerMethod.java\\\",\\\"lineNumber\\\":142,\\\"methodName\\\":\\\"invokeForRequest\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod\\\",\\\"fileName\\\":\\\"ServletInvocableHandlerMethod.java\\\",\\\"lineNumber\\\":102,\\\"methodName\\\":\\\"invokeAndHandle\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter\\\",\\\"fileName\\\":\\\"RequestMappingHandlerAdapter.java\\\",\\\"lineNumber\\\":895,\\\"methodName\\\":\\\"invokeHandlerMethod\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter\\\",\\\"fileName\\\":\\\"RequestMappingHandlerAdapter.java\\\",\\\"lineNumber\\\":800,\\\"methodName\\\":\\\"handleInternal\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter\\\",\\\"fileName\\\":\\\"AbstractHandlerMethodAdapter.java\\\",\\\"lineNumber\\\":87,\\\"methodName\\\":\\\"handle\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.servlet.DispatcherServlet\\\",\\\"fileName\\\":\\\"DispatcherServlet.java\\\",\\\"lineNumber\\\":1038,\\\"methodName\\\":\\\"doDispatch\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.servlet.DispatcherServlet\\\",\\\"fileName\\\":\\\"DispatcherServlet.java\\\",\\\"lineNumber\\\":942,\\\"methodName\\\":\\\"doService\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.servlet.FrameworkServlet\\\",\\\"fileName\\\":\\\"FrameworkServlet.java\\\",\\\"lineNumber\\\":998,\\\"methodName\\\":\\\"processRequest\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.servlet.FrameworkServlet\\\",\\\"fileName\\\":\\\"FrameworkServlet.java\\\",\\\"lineNumber\\\":890,\\\"methodName\\\":\\\"doGet\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"javax.servlet.http.HttpServlet\\\",\\\"fileName\\\":\\\"HttpServlet.java\\\",\\\"lineNumber\\\":634,\\\"methodName\\\":\\\"service\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.servlet.FrameworkServlet\\\",\\\"fileName\\\":\\\"FrameworkServlet.java\\\",\\\"lineNumber\\\":875,\\\"methodName\\\":\\\"service\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"javax.servlet.http.HttpServlet\\\",\\\"fileName\\\":\\\"HttpServlet.java\\\",\\\"lineNumber\\\":741,\\\"methodName\\\":\\\"service\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.core.ApplicationFilterChain\\\",\\\"fileName\\\":\\\"ApplicationFilterChain.java\\\",\\\"lineNumber\\\":231,\\\"methodName\\\":\\\"internalDoFilter\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.core.ApplicationFilterChain\\\",\\\"fileName\\\":\\\"ApplicationFilterChain.java\\\",\\\"lineNumber\\\":166,\\\"methodName\\\":\\\"doFilter\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.tomcat.websocket.server.WsFilter\\\",\\\"fileName\\\":\\\"WsFilter.java\\\",\\\"lineNumber\\\":53,\\\"methodName\\\":\\\"doFilter\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.core.ApplicationFilterChain\\\",\\\"fileName\\\":\\\"ApplicationFilterChain.java\\\",\\\"lineNumber\\\":193,\\\"methodName\\\":\\\"internalDoFilter\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.core.ApplicationFilterChain\\\",\\\"fileName\\\":\\\"ApplicationFilterChain.java\\\",\\\"lineNumber\\\":166,\\\"methodName\\\":\\\"doFilter\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.filter.CharacterEncodingFilter\\\",\\\"fileName\\\":\\\"CharacterEncodingFilter.java\\\",\\\"lineNumber\\\":200,\\\"methodName\\\":\\\"doFilterInternal\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.springframework.web.filter.OncePerRequestFilter\\\",\\\"fileName\\\":\\\"OncePerRequestFilter.java\\\",\\\"lineNumber\\\":107,\\\"methodName\\\":\\\"doFilter\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.core.ApplicationFilterChain\\\",\\\"fileName\\\":\\\"ApplicationFilterChain.java\\\",\\\"lineNumber\\\":193,\\\"methodName\\\":\\\"internalDoFilter\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.core.ApplicationFilterChain\\\",\\\"fileName\\\":\\\"ApplicationFilterChain.java\\\",\\\"lineNumber\\\":166,\\\"methodName\\\":\\\"doFilter\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.core.StandardWrapperValve\\\",\\\"fileName\\\":\\\"StandardWrapperValve.java\\\",\\\"lineNumber\\\":199,\\\"methodName\\\":\\\"invoke\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.core.StandardContextValve\\\",\\\"fileName\\\":\\\"StandardContextValve.java\\\",\\\"lineNumber\\\":96,\\\"methodName\\\":\\\"invoke\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.authenticator.AuthenticatorBase\\\",\\\"fileName\\\":\\\"AuthenticatorBase.java\\\",\\\"lineNumber\\\":490,\\\"methodName\\\":\\\"invoke\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.core.StandardHostValve\\\",\\\"fileName\\\":\\\"StandardHostValve.java\\\",\\\"lineNumber\\\":139,\\\"methodName\\\":\\\"invoke\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.valves.ErrorReportValve\\\",\\\"fileName\\\":\\\"ErrorReportValve.java\\\",\\\"lineNumber\\\":92,\\\"methodName\\\":\\\"invoke\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.core.StandardEngineValve\\\",\\\"fileName\\\":\\\"StandardEngineValve.java\\\",\\\"lineNumber\\\":74,\\\"methodName\\\":\\\"invoke\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.catalina.connector.CoyoteAdapter\\\",\\\"fileName\\\":\\\"CoyoteAdapter.java\\\",\\\"lineNumber\\\":343,\\\"methodName\\\":\\\"service\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.coyote.http11.Http11Processor\\\",\\\"fileName\\\":\\\"Http11Processor.java\\\",\\\"lineNumber\\\":408,\\\"methodName\\\":\\\"service\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.coyote.AbstractProcessorLight\\\",\\\"fileName\\\":\\\"AbstractProcessorLight.java\\\",\\\"lineNumber\\\":66,\\\"methodName\\\":\\\"process\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.coyote.AbstractProtocol$ConnectionHandler\\\",\\\"fileName\\\":\\\"AbstractProtocol.java\\\",\\\"lineNumber\\\":770,\\\"methodName\\\":\\\"process\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.tomcat.util.net.NioEndpoint$SocketProcessor\\\",\\\"fileName\\\":\\\"NioEndpoint.java\\\",\\\"lineNumber\\\":1415,\\\"methodName\\\":\\\"doRun\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.tomcat.util.net.SocketProcessorBase\\\",\\\"fileName\\\":\\\"SocketProcessorBase.java\\\",\\\"lineNumber\\\":49,\\\"methodName\\\":\\\"run\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"java.util.concurrent.ThreadPoolExecutor\\\",\\\"fileName\\\":\\\"ThreadPoolExecutor.java\\\",\\\"lineNumber\\\":1149,\\\"methodName\\\":\\\"runWorker\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"java.util.concurrent.ThreadPoolExecutor$Worker\\\",\\\"fileName\\\":\\\"ThreadPoolExecutor.java\\\",\\\"lineNumber\\\":624,\\\"methodName\\\":\\\"run\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"org.apache.tomcat.util.threads.TaskThread$WrappingRunnable\\\",\\\"fileName\\\":\\\"TaskThread.java\\\",\\\"lineNumber\\\":61,\\\"methodName\\\":\\\"run\\\",\\\"nativeMethod\\\":false},{\\\"className\\\":\\\"java.lang.Thread\\\",\\\"fileName\\\":\\\"Thread.java\\\",\\\"lineNumber\\\":748,\\\"methodName\\\":\\\"run\\\",\\\"nativeMethod\\\":false}],\\\"type\\\":\\\"BASE64_ENCODING_ERROR\\\"}\"}");

    }
}
