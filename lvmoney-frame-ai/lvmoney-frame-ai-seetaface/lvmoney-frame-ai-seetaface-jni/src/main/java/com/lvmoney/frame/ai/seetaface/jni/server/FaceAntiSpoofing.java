package com.lvmoney.frame.ai.seetaface.jni.server;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.common.exception.SeetafaceException;
import com.lvmoney.frame.ai.seetaface.jni.natives.FaceAntiSpoofingNative;
import com.lvmoney.frame.ai.seetaface.jni.util.SeetafaceValidated;
import com.lvmoney.frame.ai.seetaface.jni.vo.*;
import com.lvmoney.frame.base.core.exception.BusinessException;

import java.io.Closeable;

/**
 * @describe：活体识别
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class FaceAntiSpoofing implements Closeable {

    private final long NATIVE_ID;

    private boolean isClose = false;

    /**
     *初始化一个活体识别器
     * @param cstaPath: 局部检测模型(必传) fas_first.csta
     * @param cstaPathTwo: 全局检测模型(选传) fas_second.csta
     * @return: null
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:31
     */
    public FaceAntiSpoofing(String cstaPath, String... cstaPathTwo) {
        this(new SeetaModelSetting(cstaPath), cstaPathTwo);
    }

    /**
     *初始化一个活体识别器
     * @param seetaModelSetting:
     * @param cstaPathTwo:
     * @return: null
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:31
     */
    public FaceAntiSpoofing(SeetaModelSetting seetaModelSetting, String... cstaPathTwo) {
        if (seetaModelSetting == null) {
            throw new BusinessException(SeetafaceException.Proxy.SETTINGIS_NULL);
        }
        String appendCstaPath = cstaPathTwo == null || cstaPathTwo.length == 0 ? null : cstaPathTwo[0];
        NATIVE_ID = FaceAntiSpoofingNative.init(seetaModelSetting, appendCstaPath);
    }

    /**
     *检测活体
     * <pre>
     * 此函数不支持多线程调用，在多线程环境下需要建立对应的 FaceAntiSpoofing 的对象分别调用检测函数
     * 当前版本可能返回 REAL, SPOOF, FUZZY
     * </pre>
     * @param image: 输入图像，需要 RGB 彩色通道
     * @param face: 要识别的人脸位置
     * @param points: 要识别的人脸特征点
     * @return: FaceAntiSpoofing.Status 人脸状态 参考AgePredictor.Status.ordinal()
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:32
     */
    public Status predict(SeetaImageData image, SeetaRect face, SeetaPointF[] points) {
        SeetafaceValidated.validate(isClose, image, face, points);
        return Status.values()[FaceAntiSpoofingNative.predict(NATIVE_ID, image, face, points)];
    }

    /**
     *检测活体（Video模式）
     * @param image: 输入图像，需要 RGB 彩色通道
     * @param face: 要识别的人脸位置
     * @param points: 要识别的人脸特征点
     * @return: FaceAntiSpoofing.Status 人脸状态 参考AgePredictor.Status.ordinal()
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:33
     */
    public Status predictVideo(SeetaImageData image, SeetaRect face, SeetaPointF[] points) {
        SeetafaceValidated.validate(isClose, image, face, points);
        return Status.values()[FaceAntiSpoofingNative.predictVideo(NATIVE_ID, image, face, points)];
    }

    /**
     *重置 Video,开始下一次 predictVideo识别

     * @return: void
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:34
     */
    public void resetVideo() {
        SeetafaceValidated.validate(isClose);
        FaceAntiSpoofingNative.resetVideo(NATIVE_ID);
    }

    /**
     *获取活体检测内部分数

     * @return: PreFrameScore 输出人脸质量分数和真实度, 获取的是上一次调用predict或predictVideo接口后内部的阈值
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:34
     */
    public PreFrameScore getPreFrameScore() {
        SeetafaceValidated.validate(isClose);
        return FaceAntiSpoofingNative.getPreFrameScore(NATIVE_ID);
    }

    /**
     *设置 Video模式中,识别视频帧数,当输入帧数为该值以后才会有返回值
     * @param number: 视频帧数
     * @return: void
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:34
     */
    public void setVideoFrameCount(int number) {
        SeetafaceValidated.validate(isClose);
        FaceAntiSpoofingNative.setVideoFrameCount(NATIVE_ID, number);
    }

    /**
     *与{@link #setVideoFrameCount(long, int)}相对应

     * @return: int 获取视频帧数设置
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:34
     */
    public int getVideoFrameCount() {
        SeetafaceValidated.validate(isClose);
        return FaceAntiSpoofingNative.getVideoFrameCount(NATIVE_ID);
    }

    /**
     *设置阈值
     * @param clarity: 清晰度阈值 越高要求输入的图像质量越高 默认0.3
     * @param reality: 活体阈值 越高对识别要求越严格 0.8
     * @return: void
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:35
     */
    public void setThreshold(float clarity, float reality) {
        SeetafaceValidated.validate(isClose);
        FaceAntiSpoofingNative.setThreshold(NATIVE_ID, clarity, reality);
    }

    /**
     *设置全局阈值
     * @param boxThresh: 全局检测阈值 默认阈值为 0.8
     * @return: void
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:35
     */
    public void setBoxThresh(float boxThresh) {
        SeetafaceValidated.validate(isClose);
        FaceAntiSpoofingNative.setBoxThresh(NATIVE_ID, boxThresh);
    }

    /**
     *与{@link #setBoxThresh(float)}相对应

     * @return: float 获取全局域值
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:35
     */
    public float getBoxThresh() {
        SeetafaceValidated.validate(isClose);
        return FaceAntiSpoofingNative.getBoxThresh(NATIVE_ID);
    }

    /**
     *与{@link #setThreshold(long, float, float)}相对应

     * @return: PreFrameScore      * @return 获取域值
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:36
     */
    public PreFrameScore getThreshold() {
        SeetafaceValidated.validate(isClose);
        return FaceAntiSpoofingNative.getThreshold(NATIVE_ID);
    }

    /**
     *设置相关属性值
     * @param property: FaceAntiSpoofing.Property.ordinal()
     * @param value: 设置的值
     * @return: void
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:36
     */
    public void set(Property property, double value) {
        SeetafaceValidated.validate(isClose, property);
        FaceAntiSpoofingNative.set(NATIVE_ID, property.getValue(), value);
    }

    /**
     *与{@link #set(long, int, double)}相对应
     * @param property: 参考FaceAntiSpoofing.Property.ordinal()
     * @return: double 设置的值
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:37
     */
    public double get(Property property) {
        SeetafaceValidated.validate(isClose, property);
        return FaceAntiSpoofingNative.get(NATIVE_ID, property.getValue());
    }

    /**
     *释放资源

     * @return: void
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:37
     */
    @Override
    public void close() {
        SeetafaceValidated.validate(isClose);
        FaceAntiSpoofingNative.close(NATIVE_ID);
        isClose = true;
    }

    public enum Property {
        /**
         * 表示计算线程数，默认为 4.
         */
        PROPERTY_NUMBER_THREADS(4),
        /**
         * 表示计算线程数，默认为 5.
         */
        PROPERTY_ARM_CPU_MODE(5);

        private int num;

        private Property(int num) {
            this.num = num;
        }

        public int getValue() {
            return num;
        }
    }

    public enum Status {
        /**
         * 真实人脸
         */
        REAL,
        /**
         * 攻击人脸（假人脸）
         */
        SPOOF,
        /**
         * 无法判断（人脸成像质量不好）
         */
        FUZZY,
        /**
         * 正在检测
         */
        DETECTING
    }

}
