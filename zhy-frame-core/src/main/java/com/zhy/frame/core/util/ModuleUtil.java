package com.zhy.frame.core.util;/**
 * 描述:
 * 包名:com.zhy.k8s.base.util
 * 版本信息: 版本1.0
 * 日期:2019/9/19
 * Copyright 四川******科技有限公司
 */


import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.base.core.exception.CommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/9/19 14:09
 */
public class ModuleUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModuleUtil.class);
    private static final String RELACE_TARGET = "target";
    private static final String RELACE_CLASSES = "classes";
    private static final String CLASSPATH = "classpath:";

    /**
     * 获得当前module的项目根路径，以方便获得对应的配置文件
     *
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/19 14:15
     */
    public static String getModuleRootPath() {
        File path = null;
        try {
            path = new File(ResourceUtils.getURL(CLASSPATH).getPath());
            String rootPath = path.getPath().replaceAll(RELACE_TARGET, "").replaceAll(RELACE_CLASSES, "");
            return rootPath;
        } catch (FileNotFoundException e) {
            LOGGER.error("获取当前module的根目录报错:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.MODULE_ROOT_PATH_ERROR);
        }

    }

}
