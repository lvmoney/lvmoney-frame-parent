package com.lvmoney.frame.ai.seetaface.jni.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

/**
 * @describe：人脸信息
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class SeetaTrackingFaceInfo {

    /**
     * 人脸位置
     */
    public SeetaRect pos;

    /**
     * 人脸置信分数
     */
    public float score;

    /**
     * 视频帧的索引
     */
    public int frameNo;

    /**
     * 跟踪的人脸标识id
     */
    public int pid;

    public int step;

}
