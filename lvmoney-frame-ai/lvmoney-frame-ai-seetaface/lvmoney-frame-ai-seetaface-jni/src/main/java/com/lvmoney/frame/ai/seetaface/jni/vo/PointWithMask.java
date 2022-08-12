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
public class PointWithMask {

    /**
     * 人脸特征点
     */
    public SeetaPointF point;
    /**
     * false表示未被遮挡,true表示被遮挡
     */
    public boolean mask;

}
