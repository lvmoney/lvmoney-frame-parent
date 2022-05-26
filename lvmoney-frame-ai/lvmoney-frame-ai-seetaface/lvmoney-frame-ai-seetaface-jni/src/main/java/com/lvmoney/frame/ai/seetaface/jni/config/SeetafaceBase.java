package com.lvmoney.frame.ai.seetaface.jni.config;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.seetaface.jni.config
 * 版本信息: 版本1.0
 * 日期:2022/2/20
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.ai.seetaface.common.exception.SeetafaceException;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.base.core.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/2/20 11:49
 */
public class SeetafaceBase {
    /**
     * os.name
     */
    private static final String OS_NAME = "os.name";
    /**
     * os.name 默认值
     */
    private static final String OS_NAME_VALUE = "WINDOWS";
    /**
     * os.arch
     */
    private static final String OS_ARCH = "os.arch";
    /**
     * os.arch默认值
     */
    private static final String OS_ARCH_VALUE = "64";

    private static final Logger LOGGER = LoggerFactory.getLogger(SeetafaceBase.class);


    static {
        SeetafaceProp instance = SeetafaceProp.getInstance();

        String dllPath = instance.getSeetafaceDllPath();
        String[] seetafaceDlls = instance.getSeetafaceDlls();
        for (String seetafaceDll : seetafaceDlls) {
            //加载dll
            System.load(dllPath + BaseConstant.FILE_SEPARATOR + seetafaceDll);
        }
        Properties pro = System.getProperties();
        // 仅支持
        String os = pro.getProperty(OS_NAME).toUpperCase();
        if (!os.contains(OS_NAME_VALUE)) {
            LOGGER.error("操作系统不支持:{}", OS_NAME_VALUE);
            throw new BusinessException(SeetafaceException.Proxy.OS_NOT_SUPPORT);
        }
        String arch = pro.getProperty(OS_ARCH);
        if (!arch.contains(OS_ARCH_VALUE)) {
            LOGGER.error("不支持64位操作系统:{}", OS_ARCH_VALUE);
            throw new BusinessException(SeetafaceException.Proxy.ARCH_NOT_SUPPORT);
        }
        String path = SeetafaceBase.class.getResource(instance.getSeetafaceJni()).getPath();
        System.load(path);
    }


}
