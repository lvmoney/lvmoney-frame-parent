package com.lvmoney.frame.ai.seetaface.jni.natives;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.jni.vo.PointWithMask;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaImageData;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaModelSetting;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaRect;

/**
 * @describe：人脸特征点检测器
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class FaceLandmarkerNative {

    /**
     * 人脸特征点检测器
     *
     * @param setting: 检测器结构参数
     * @throws
     * @return: long FaceLandmarker在c++的持久化Id
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:33
     */
    public static native long init(SeetaModelSetting setting);

    /**
     * 获取模型对应的特征点数组长度
     *
     * @param nativeId: 持久化id
     * @throws
     * @return: int 模型特征点数组长度
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:33
     */
    public static native int number(long nativeId);

    /**
     * 获取人脸特征点
     *
     * @param nativeId: 持久化id
     * @param image:    图像原始数据
     * @param face:     人脸位置
     * @throws
     * @return: PointWithMask[] 人脸特征点数组
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:34
     */
    public static native PointWithMask[] mark(long nativeId, SeetaImageData image, SeetaRect face);

    /**
     * 释放资源
     *
     * @param nativeId: 持久化id
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:34
     */
    public static native void close(long nativeId);

}
