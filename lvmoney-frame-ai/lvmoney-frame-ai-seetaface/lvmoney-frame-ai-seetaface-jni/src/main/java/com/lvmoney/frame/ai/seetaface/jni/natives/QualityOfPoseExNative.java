package com.lvmoney.frame.ai.seetaface.jni.natives;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.jni.vo.*;

/**
 * @describe：深度学习的人脸姿态评估器
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class QualityOfPoseExNative {

    /**
     * 人脸姿态评估器构造函数
     *
     * @param setting: 构造评估器需要传入的结构体参数
     * @throws
     * @return: long QualityOfPoseEx在c++的序列化
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 22:03
     */
    public static native long init(SeetaModelSetting setting);

    /**
     * 检测人脸姿态
     *
     * @param nativeId: QualityOfPoseEx在c++的序列化
     * @param image:    原始图像数据
     * @param face:     人脸位置
     * @param points:   人脸5个特征点数组
     * @throws
     * @return: QualityResult 人脸姿态检测结果
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 22:04
     */
    public static native QualityResult check(long nativeId, SeetaImageData image, SeetaRect face, SeetaPointF[] points);

    /**
     * 设置相关属性
     *
     * @param nativeId: QualityOfPoseEx在c++的序列化
     * @param property: 属性 参考 QualityOfPose.Property.getValue()
     * @param value:    值
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 22:04
     */
    public static native void set(long nativeId, int property, double value);

    /**
     * 获取相关属性值
     *
     * @param nativeId: QualityOfPoseEx在c++的序列化
     * @param property: 属性 参考 QualityOfPose.Property.getValue()
     * @throws
     * @return: double 值
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 22:05
     */
    public static native double get(long nativeId, int property);

    /**
     * 释放资源
     *
     * @param nativeId: QualityOfPoseEx在c++的序列化
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 22:05
     */
    public static native void close(long nativeId);
}
