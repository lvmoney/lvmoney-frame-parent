package com.lvmoney.frame.ai.seetaface.jni.natives;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaFaceInfoArray;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaImageData;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaModelSetting;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class FaceDetectorNative {

    /**
     * 初始化一个FaceDetector
     *
     * @param setting: 配置
     * @throws
     * @return: long FaceDetector在c++持久化的序列号
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:29
     */
    public static native long init(SeetaModelSetting setting);

    /**
     * 输入彩色图像，检测其中的人脸。
     *
     * @param nativeId: init接口获取的持久化序列号
     * @param image:    输入的图像数据
     * @throws
     * @return: SeetaFaceInfoArray 人脸信息数组
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:29
     */
    public static native SeetaFaceInfoArray detect(long nativeId, SeetaImageData image);

    /**
     * 设置人脸检测器相关属性值 property来自{@linkplain FaceDetector.Property}
     *
     * @param nativeId: init接口获取的持久化序列号
     * @param property: 属性类型
     * @param value:    设置的属性值
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:29
     */
    public static native void set(long nativeId, int property, double value);

    /**
     * 获取人脸检测器相关属性值 property来自{@linkplain FaceDetector.Property}
     *
     * @param nativeId: init接口获取的持久化序列号
     * @param property: 属性类型
     * @throws
     * @return: double 设置的属性值
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:30
     */
    public static native double get(long nativeId, int property);

    /**
     * 释放资源
     *
     * @param nativeId: FaceDetector在c++持久化的序列号
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:30
     */
    public static native void close(long nativeId);

}
