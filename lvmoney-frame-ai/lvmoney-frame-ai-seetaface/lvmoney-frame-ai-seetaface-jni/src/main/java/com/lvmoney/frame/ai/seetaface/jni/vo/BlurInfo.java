package com.lvmoney.frame.ai.seetaface.jni.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

/**
 * @describe：模糊信息
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class BlurInfo {

    /**
     * 亮度返回结果，暂不推荐使用该返回结果
     */
    public int light;

    /**
     * 结果返回 0 说明人脸是清晰的, 为 1 说明人脸是模糊的。
     */
    public int blur;

    /**
     * 是否有噪声返回结果，暂不推荐使用该返回结果
     */
    public int noise;

}
