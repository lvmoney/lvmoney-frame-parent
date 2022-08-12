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
public class PreFrameScore {

    /**
     * 人脸质量分数/清晰度阈值 当设置时越高要求输入的图像质量越高 默认0.3
     */
    float clarity;

    /**
     * 真实度/活体阈值 当设置时越高对识别要求越严格 0.8
     */
    float reality;

}
