package com.lvmoney.frame.ai.seetaface.jni.config;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.seetaface.jni.config
 * 版本信息: 版本1.0
 * 日期:2022/2/20
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/2/20 11:49
 */
@Data
public class SeetafaceProp implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(SeetafaceBase.class);
    private static final long serialVersionUID = 7701457279203569778L;
    /**
     * seetaface dll 路径
     */
    private String seetafaceDllPath;
    /**
     * seetaface 的 dll
     * <pre>
     * 1. 下载路径:https://github.com/seetafaceengine/SeetaFace6
     * 2. 拖到下面介绍有一个模块叫<strong>下载地址</strong>
     * 3. 找到windows下载开发包
     * 4. 下载完成后解压 将下面地址指向 %解压的路径%/sf6.0_windows/lib/x64
     * </pre>
     */
    private String[] seetafaceDlls;
    /**
     * seetaface 的 model
     * CSTA基础路径
     * <pre>
     * 1. 下载路径:https://github.com/seetafaceengine/SeetaFace6
     * 2. 拖到下面介绍有一个模块叫<strong>下载地址</strong>
     * 3. 找到模型文件 下载Part1
     * 4. 下载完成后解压 将下面地址指向 %解压的路径%/sf3.0_models
     * </pre>
     */
    private String seetafaceModelPath;

    /**
     * 框架生成的jni dll
     */
    private String seetafaceJni;

    /**
     * 获取配置文件地址
     *
     * @throws
     * @return: java.util.Properties
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:43
     */
    private static Properties getConfig() {
        Properties properties = new Properties();
        String location = "classpath:/seetaface.properties";
        try (InputStream is = new DefaultResourceLoader().getResource(location).getInputStream()) {
            properties.load(is);
            LOGGER.debug("seetaface config: {}", properties.toString());
        } catch (IOException ex) {
            LOGGER.error("Could not load property file:" + location, ex);
        }
        return properties;
    }

    /**
     * 获得默认配置
     *
     * @throws
     * @return: SeetafaceProp
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 12:05
     */
    public static SeetafaceProp getInstance() {
        Properties prop = getConfig();
        String dllPath = prop.getProperty("dll.path", "");
        String[] dllList = prop.getProperty("dll.list", "").split(",");
        String modelPath = prop.getProperty("model.path", "");
        String jniName = prop.getProperty("jni.name", "");
        SeetafaceProp result = new SeetafaceProp();
        result.setSeetafaceDllPath(dllPath);
        result.setSeetafaceDlls(dllList);
        result.setSeetafaceModelPath(modelPath);
        result.setSeetafaceJni(jniName);
        return result;
    }

}
