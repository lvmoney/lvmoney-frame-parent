package com.lvmoney.frame.ai.seetaface.jni.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class SeetaModelSetting {

    public static final int SEETA_DEVICE_AUTO = 0;

    public static final int SEETA_DEVICE_CPU = 1;

    public static final int SEETA_DEVICE_GPU = 2;

    /**
     * SEETA_DEVICE_AUTO = 0
     * SEETA_DEVICE_CPU  = 1
     * SEETA_DEVICE_GPU  = 2
     */
    public int device = -1;

    /**
     * 当device是SEETA_DEVICE_GPU, id为使用GPU的id
     */
    public int id = -1;

    /**
     * csta的具体路径
     */
    public String model;

    public SeetaModelSetting(String cstaPath) {
        this.model = cstaPath;
    }

}
