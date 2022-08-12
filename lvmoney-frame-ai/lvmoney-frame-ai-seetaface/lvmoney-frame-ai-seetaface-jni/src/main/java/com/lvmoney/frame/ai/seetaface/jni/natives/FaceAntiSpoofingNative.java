package com.lvmoney.frame.ai.seetaface.jni.natives;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.ai.seetaface.jni.vo.*;

/**
 * @describe：活体识别 静默活体识别根据输入的图像数据、人脸位置和人脸特征点，对输入人脸进行活体的判断，并返回人脸活体的状态
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class FaceAntiSpoofingNative {

    /**
     * 初始化一个FaceAntiSpoofing
     *
     * @param setting:        配置
     * @param appendCstaPath: 附加模型
     * @throws
     * @return: long FaceAntiSpoofing在c++持久化的序列号
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:21
     */
    public static native long init(SeetaModelSetting setting, String appendCstaPath);

    /**
     * 检测活体
     * <pre>
     * 此函数不支持多线程调用，在多线程环境下需要建立对应的 FaceAntiSpoofing 的对象分别调用检测函数
     * 当前版本可能返回 REAL, SPOOF, FUZZY
     * </pre>
     *
     * @param nativeId: FaceAntiSpoofing在c++持久化的序列号
     * @param image:    输入图像，需要 RGB 彩色通道
     * @param face:     要识别的人脸位置
     * @param points:   要识别的人脸特征点
     * @throws
     * @return: int 人脸状态 参考AgePredictor.Status.ordinal()
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:21
     */
    public static native int predict(long nativeId, SeetaImageData image, SeetaRect face, SeetaPointF[] points);

    /**
     * 检测活体（Video模式）
     *
     * @param nativeId: FaceAntiSpoofing在c++持久化的序列号
     * @param image:    输入图像，需要 RGB 彩色通道
     * @param face:     要识别的人脸位置
     * @param points:   要识别的人脸特征点
     * @throws
     * @return: int 人脸状态 参考AgePredictor.Status.ordinal()
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:23
     */
    public static native int predictVideo(long nativeId, SeetaImageData image, SeetaRect face, SeetaPointF[] points);

    /**
     * 重置 Video,开始下一次 predictVideo识别
     *
     * @param nativeId: FaceAntiSpoofing在c++持久化的序列号
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:23
     */
    public static native void resetVideo(long nativeId);

    /**
     * 获取活体检测内部分数
     *
     * @param nativeId: FaceAntiSpoofing在c++持久化的序列号
     * @throws
     * @return: PreFrameScore 输出人脸质量分数和真实度, 获取的是上一次调用predict或predictVideo接口后内部的阈值
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:24
     */
    public static native PreFrameScore getPreFrameScore(long nativeId);

    /**
     * 设置 Video模式中,识别视频帧数,当输入帧数为该值以后才会有返回值
     *
     * @param nativeId: FaceAntiSpoofing在c++持久化的序列号
     * @param number:   视频帧数
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:24
     */
    public static native void setVideoFrameCount(long nativeId, int number);

    /**
     * 与{@link #setVideoFrameCount(long, int)}相对应
     *
     * @param nativeId: FaceAntiSpoofing在c++持久化的序列号
     * @throws
     * @return: int 获取视频帧数设置
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:24
     */
    public static native int getVideoFrameCount(long nativeId);

    /**
     * 设置阈值
     *
     * @param nativeId: FaceAntiSpoofing在c++持久化的序列号
     * @param clarity:  清晰度阈值 越高要求输入的图像质量越高 默认0.3
     * @param reality:  活体阈值 越高对识别要求越严格 0.8
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:25
     */
    public static native void setThreshold(long nativeId, float clarity, float reality);

    /**
     * 设置全局阈值
     *
     * @param nativeId:  FaceAntiSpoofing在c++持久化的序列号
     * @param boxThresh: 全局检测阈值 默认阈值为 0.8
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:25
     */
    public static native void setBoxThresh(long nativeId, float boxThresh);


    /**
     * 与{@link #setBoxThresh(float)}相对应
     *
     * @param nativeId: FaceAntiSpoofing在c++持久化的序列号
     * @throws
     * @return: float      获取全局域值
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:25
     */
    public static native float getBoxThresh(long nativeId);

    /**
     * 与{@link #setThreshold(long, float, float)}相对应
     *
     * @param nativeId: FaceAntiSpoofing在c++持久化的序列号
     * @throws
     * @return: PreFrameScore 获取域值
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:26
     */
    public static native PreFrameScore getThreshold(long nativeId);

    /**
     * 设置相关属性值
     *
     * @param nativeId: FaceAntiSpoofing在c++持久化的序列号
     * @param property: FaceAntiSpoofing.Property.ordinal()
     * @param value:    设置的值
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:26
     */
    public static native void set(long nativeId, int property, double value);

    /**
     * 与{@link #set(long, int, double)}相对应
     *
     * @param nativeId: FaceAntiSpoofing在c++持久化的序列号
     * @param property: 参考FaceAntiSpoofing.Property.ordinal()
     * @throws
     * @return: double 设置的值
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:27
     */
    public static native double get(long nativeId, int property);

    /**
     * 释放资源
     *
     * @param nativeId:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:27
     */
    public static native void close(long nativeId);

}
