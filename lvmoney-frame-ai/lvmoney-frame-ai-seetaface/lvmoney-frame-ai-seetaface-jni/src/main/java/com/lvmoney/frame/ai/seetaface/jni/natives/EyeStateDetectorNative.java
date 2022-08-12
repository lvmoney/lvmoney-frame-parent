package com.lvmoney.frame.ai.seetaface.jni.natives;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.ai.seetaface.jni.vo.EyeState;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaImageData;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaModelSetting;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaPointF;

/**
 * @describe：眼睛状态检测器 眼睛检测器要求输入原始图像数据和人脸特征点，返回左眼和右眼的状态
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class EyeStateDetectorNative {

    /**
     * 初始化一个EyeStateDetector
     *
     * @param setting: 配置
     * @throws
     * @return: long FaceDetector在c++持久化的序列号
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:16
     */
    public static native long init(SeetaModelSetting setting);

    /**
     * 输入原始图像数据和人脸特征点，返回左眼和右眼的状态。
     *
     * @param nativeId: 持久化ID
     * @param image:    原始图像数据
     * @param points:   人脸特征点数组
     * @throws
     * @return: EyeState 眼睛状态
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:17
     */
    public static native EyeState detect(long nativeId, SeetaImageData image, SeetaPointF[] points);

    /**
     * 设置相关属性值
     *
     * @param nativeId: 持久化ID
     * @param property: 属性类型 参考EyeStateDetector.Property.ordinal()
     * @param value:    设置的属性值
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:18
     */
    public static native void set(long nativeId, int property, double value);

    /**
     * @param nativeId: 持久化ID
     * @param property: 属性类型 参考EyeStateDetector.Property.ordinal()
     * @throws
     * @return: double 属性值
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:18
     */
    public static native double get(long nativeId, int property);

    /**
     * 释放资源
     *
     * @param nativeId: 持久化ID
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:19
     */
    public static native void close(long nativeId);

}
