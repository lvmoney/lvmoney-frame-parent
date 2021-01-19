package com.lvmoney.frame.cloud.base.util;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.util
 * 版本信息: 版本1.0
 * 日期:2019/9/19
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.cloud.base.vo.DockerInfo;
import com.lvmoney.frame.core.util.ModuleUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.lvmoney.frame.base.core.constant.BaseConstant.DASH_LINE;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/9/19 13:53
 */
public class PomUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PomUtil.class);

    /**
     * 对应pom中的dockerRegistryIp
     */
    private static final String DOCKER_REGISTRYIP = "dockerRegistryIp";
    /**
     * 对应pom中的dockerTagPort
     */
    private static final String DOCKER_TAG_PORT = "dockerTagPort";
    /**
     * 对应pom中的namespace
     */
    private static final String NAMESPACE = "namespace";

    /**
     * 为了pom和代码中docker镜像相关的配置统一，通过工具从pom文件获得docker的配置
     *
     * @throws
     * @return: com.lvmoney.k8s.base.vo.DockerInfo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/19 14:20
     */
    public static DockerInfo getDockerInfo() {
        DockerInfo dockerInfo = new DockerInfo();
        MavenXpp3Reader reader = new MavenXpp3Reader();
        String projPom = ModuleUtil.getModuleRootPath() + File.separator + "pom.xml";
        try {
            Model model = reader.read(new FileReader(projPom));
            String projectArtifactId = model.getArtifactId();
            String projectVersion = ObjectUtils.isNotEmpty(model.getVersion()) ? model.getVersion() : model.getParent().getVersion();
            String dockerRegistryIp = model.getProperties().getProperty(DOCKER_REGISTRYIP);
            String dockerTagPort = model.getProperties().getProperty(DOCKER_TAG_PORT);
            String namespace = model.getProperties().getProperty(NAMESPACE);
            dockerInfo.setDockerFileName(projectArtifactId + DASH_LINE + projectVersion);
            dockerInfo.setDockerImageName(projectArtifactId + ":" + projectVersion);
            dockerInfo.setDockerImageTag(dockerRegistryIp + ":" + dockerTagPort + "/" + namespace + "/" + projectArtifactId + ":" + projectVersion);
        } catch (IOException e) {
            LOGGER.error("获得docker配置信息报错:{}", e);
        } catch (XmlPullParserException e) {
            LOGGER.error("获得docker配置信息报错:{}", e);
        }
        return dockerInfo;
    }

}
