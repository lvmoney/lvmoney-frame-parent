package com.lvmoney.frame.cloud.base.controller;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.function
 * 版本信息: 版本1.0
 * 日期:2019/8/21
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.base.core.util.SupportUtil;
import com.lvmoney.frame.cloud.base.constant.CloudBaseConstant;
import com.lvmoney.frame.cloud.base.util.PomUtil;
import com.lvmoney.frame.cloud.base.vo.req.DockerFileReqVo;
import com.lvmoney.frame.core.util.ModuleUtil;
import com.lvmoney.frame.core.util.SnowflakeIdFactoryUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/21 16:47
 */
@RestController
@RequestMapping(value = "/docker")
public class DockerFileController {
    @Value("${istio.docker.file.temp:/home}")
    private String temp;
    @Value("${istio.docker.java.javaOpts:" + CloudBaseConstant.DEFAULT_JAVA_OPTS + "}")
    private String javaOpts;
    @Value("${skywalking.server:127.0.0.1:11800}")
    private String skywalkingServer;
    @Value("${skywalking.agent:/home/apache-skywalking/agent/skywalking-agent.jar}")
    private String skywalkingAgent;
    /**
     * 服务名称
     */
    @Value("${spring.application.name:lvmoney}")
    String applicationName;
    /**
     * dockerfile 默认路径
     */
    private static final String DOCKERFILE_PATH = "\\src\\main\\resources\\Dockerfile";

    @Value("${frame.skywalking.support:false}")
    String frameSupport;

    @GetMapping(value = "/dockerFile")
    public String build(DockerFileReqVo dockerFileReqVo) {
        try {
            String rootPath = ModuleUtil.getModuleRootPath();
            Path rootLocation = Paths.get(rootPath);
            //data.js是文件
            String dockerFilePath = rootPath + DOCKERFILE_PATH;
            File file = new File(dockerFilePath);
            if (file.exists()) {
                file.delete();
            }
            Path path2 = rootLocation.resolve(dockerFilePath);
            String dockerFile = dockerFile(dockerFileReqVo.getTemp());
            byte[] strToBytes = dockerFile.getBytes();
            try {
                Files.write(path2, strToBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return dockerFile;
        } catch (Exception e) {
            e.printStackTrace();
            return "";

        }

    }

    public String dockerFile(String tempPath) {
        tempPath = StringUtils.isEmpty(tempPath) ? "" : tempPath;
        String dokcerFile = PomUtil.getDockerInfo().getDockerFileName();
        String appJar = "app" + SnowflakeIdFactoryUtil.nextId() + ".jar";
        String from = "FROM java:8";
        String volume = "VOLUME " + temp + tempPath;
        String add = "ADD " + dokcerFile + ".jar " + appJar;
        String run = "RUN sh -c 'touch /" + appJar + "'";
        String env = "ENV JAVA_OPTS=" + javaOpts;
        if (BaseConstant.SUPPORT_TRUE.equals(frameSupport)) {
            String skywalking = "ENV JAVA_AGENT=-javaagent:" + skywalkingAgent + " -Dskywalking.agent.service_name=" + applicationName + " -Dskywalking.collector.backend_service=" + skywalkingServer;
            String entrypoint = "ENTRYPOINT [ \"sh\", \"-c\", \"java $JAVA_OPTS  -Djava.security.egd=file:/dev/./urandom -jar $JAVA_AGENT/" + appJar + "\" ]";
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(from).append("\n").append(volume).append("\n").append(add).append("\n").append(run).append("\n").append(env).append("\n").append(skywalking).append("\n").append(entrypoint);
            return stringBuffer.toString();
        } else {
            String entrypoint = "ENTRYPOINT [ \"sh\", \"-c\", \"java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /" + appJar + "\" ]";
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(from).append("\n").append(volume).append("\n").append(add).append("\n").append(run).append("\n").append(env).append("\n").append(entrypoint);
            return stringBuffer.toString();
        }

    }

    public static void main(String[] args) {
        String rootPath = ModuleUtil.getModuleRootPath();
        Path rootLocation = Paths.get(rootPath);
        //data.js是文件
        String dockerFilePath = rootPath + DOCKERFILE_PATH;
        DockerFileController df = new DockerFileController();
        System.out.println(df.dockerFile(dockerFilePath));
    }
}
