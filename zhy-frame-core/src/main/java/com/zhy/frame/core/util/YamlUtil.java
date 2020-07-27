package com.zhy.frame.core.util;/**
 * 描述:
 * 包名:com.zhy.k8s.base.util
 * 版本信息: 版本1.0
 * 日期:2019/8/18
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.base.core.util.JsonUtil;
import com.zhy.frame.core.vo.YamlBuildVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/18 18:53
 */
public class YamlUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(YamlUtil.class);

    /**
     * @describe: 根据传入实体构造不同yaml
     * @param: [yamlBuildVo]
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/8/19 10:33
     */
    public static void buildYaml(YamlBuildVo yamlBuildVo) {
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        dumperOptions.setDefaultScalarStyle(DumperOptions.ScalarStyle.PLAIN);
        dumperOptions.setPrettyFlow(false);
        File filePath = new File(yamlBuildVo.getPath());
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        File fileName = new File(yamlBuildVo.getPath() + BaseConstant.FILE_SEPARATOR + yamlBuildVo.getName());
        if (fileName.exists() && yamlBuildVo.isCover()) {
            fileName.delete();
        } else if (fileName.exists() && !yamlBuildVo.isCover()) {
            return;
        }
        Yaml yaml = new Yaml(dumperOptions);
        FileWriter fw = null;
        try {
            fw = new FileWriter(fileName);
        } catch (IOException e) {
            LOGGER.error("构造yaml报错:{}", e);
        }
        List<Object> yamlData = new ArrayList();
        yamlBuildVo.getData().stream().forEach(e -> {
            Map<String, Object> map = (Map<String, Object>) yaml.load(JsonUtil.t2JsonString(3));
            yamlData.add(map);
        });
        yaml.dumpAll(yamlData.iterator(), fw);
    }
}
