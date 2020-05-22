package com.zhy.frame.ops.docker.controller;/**
 * 描述:
 * 包名:com.zhy.k8s.base.function
 * 版本信息: 版本1.0
 * 日期:2019/8/21
 * Copyright 四川******科技有限公司
 */


import com.zhy.frame.core.util.ModuleUtil;
import com.zhy.frame.core.util.SnowflakeIdFactoryUtil;
import com.zhy.frame.ops.docker.util.PomUtil;
import com.zhy.frame.ops.docker.vo.req.DockerFileReqVo;
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
 * @describe：通用接口，方便自动生成dockerFile
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/8/21 16:47
 */
@RestController
@RequestMapping(value = "/docker")
public class DockerFileController {
    @Value("${istio.docker.file.temp:/home}")
    String temp;

    @GetMapping(value = "/dockerFile")
    public String build(DockerFileReqVo dockerFileReqVo) {
        try {
            String rootPath = ModuleUtil.getModuleRootPath();
            Path rootLocation = Paths.get(rootPath);
            //data.js是文件
            String dockerFilePath = rootPath + "\\src\\main\\resources\\Dockerfile";
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

    private String dockerFile(String tempPath) {
        tempPath = StringUtils.isEmpty(tempPath) ? "" : tempPath;
        SnowflakeIdFactoryUtil snowflakeIdFactoryUtil = new SnowflakeIdFactoryUtil(1, 2);
        String dokcerFile = PomUtil.getDockerInfo().getDockerFileName();
        String appJar = "app" + snowflakeIdFactoryUtil.nextId() + ".jar";
        String from = "FROM java:8";
        String volume = "VOLUME " + temp + tempPath;
        String add = "ADD " + dokcerFile + ".jar " + appJar;
        String run = "RUN sh -c 'touch /" + appJar + "'";
        String env = "ENV JAVA_OPTS=\"\"";
        String entrypoint = "ENTRYPOINT [ \"sh\", \"-c\", \"java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /" + appJar + "\" ]";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(from).append("\n").append(volume).append("\n").append(add).append("\n").append(run).append("\n").append(env).append("\n").append(entrypoint);
        return stringBuffer.toString();
    }
}
