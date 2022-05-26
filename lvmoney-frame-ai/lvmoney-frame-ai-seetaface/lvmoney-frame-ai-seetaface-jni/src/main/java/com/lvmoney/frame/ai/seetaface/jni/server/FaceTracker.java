package com.lvmoney.frame.ai.seetaface.jni.server;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.jni.natives.FaceTrackerNative;
import com.lvmoney.frame.ai.seetaface.jni.util.SeetafaceValidated;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaImageData;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaModelSetting;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaTrackingFaceInfoArray;

import java.io.Closeable;

/**
 * @describe：人脸跟踪器,人脸跟踪器会对输入的彩色图像或者灰度图像中的人脸进行跟踪，并返回所有跟踪到的人脸信息。
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class FaceTracker implements Closeable {

    private final long NATIVE_ID;

    private boolean isClose = false;

    /**
     * 人脸跟踪器的构造器
     *
     * @param cstaPath:    face_recognizer.csta路径
     * @param videoWidth:  视频的宽度
     * @param videoHeight: 视频的高度
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:51
     */
    public FaceTracker(String cstaPath, int videoWidth, int videoHeight) {
        this(new SeetaModelSetting(cstaPath), videoWidth, videoHeight);
    }

    /**
     * 人脸跟踪器的构造器
     *
     * @param setting:     跟踪器结构参数
     * @param videoWidth:  视频的宽度
     * @param videoHeight: 视频的高度
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:52
     */
    public FaceTracker(SeetaModelSetting setting, int videoWidth, int videoHeight) {
        NATIVE_ID = FaceTrackerNative.init(setting, videoWidth, videoHeight);
    }

    /**
     * 设置底层的计算线程数量
     *
     * @param num: 线程数量
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:53
     */
    public void setSingleCalculationThreads(int num) {
        SeetafaceValidated.validate(isClose);
        FaceTrackerNative.setSingleCalculationThreads(NATIVE_ID, num);
    }

    /**
     * 对视频帧中的人脸进行跟踪
     *
     * @param image:   原始图像数据
     * @param frameNo: 视频帧索引
     * @throws
     * @return: SeetaTrackingFaceInfoArray 跟踪到的人脸信息数组
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:53
     */
    public SeetaTrackingFaceInfoArray track(SeetaImageData image, int frameNo) {
        SeetafaceValidated.validate(isClose, image);
        return FaceTrackerNative.track(NATIVE_ID, image, frameNo);
    }

    /**
     * 设置检测器的最小人脸大小
     *
     * @param size: 最小人脸大小 size 的大小保证大于等于 20，size的值越小，能够检测到的人脸的尺寸越小，检测速度越慢。
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:54
     */
    public void setMinFaceSize(int size) {
        SeetafaceValidated.validate(isClose);
        FaceTrackerNative.setMinFaceSize(NATIVE_ID, size);
    }

    /**
     * 获取检测器的最小人脸大小 与{@link #setMinFaceSize(long, int)}对应
     *
     * @throws
     * @return: int 获取检测器的最小人脸大小
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:54
     */
    public int getMinFaceSize() {
        SeetafaceValidated.validate(isClose);
        return FaceTrackerNative.getMinFaceSize(NATIVE_ID);
    }

    /**
     * 设置检测器的检测阈值
     *
     * @param thresh: 检测阈值
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:55
     */
    public void setThreshold(float thresh) {
        SeetafaceValidated.validate(isClose);
        FaceTrackerNative.setThreshold(NATIVE_ID, thresh);
    }

    /**
     * 获取检测器的检测阈值 与{@link #setThreshold(long, float)}对应
     *
     * @throws
     * @return: float 获取检测器的检测阈值
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:55
     */
    public float getThreshold() {
        SeetafaceValidated.validate(isClose);
        return FaceTrackerNative.getThreshold(NATIVE_ID);
    }

    /**
     * 设置以稳定模式输出人脸跟踪结果。 只有在视频中连续跟踪时，才使用此方法
     *
     * @param stable: 是否是稳定模式
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:55
     */
    public void setVideoStable(boolean stable) {
        SeetafaceValidated.validate(isClose);
        FaceTrackerNative.setVideoStable(NATIVE_ID, stable);
    }

    /**
     * 获取当前是否是稳定工作模式 与{@link #setVideoStable(long, boolean)}对应
     *
     * @throws
     * @return: boolean 是否是稳定模式
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:56
     */
    public boolean getVideoStable() {
        SeetafaceValidated.validate(isClose);
        return FaceTrackerNative.getVideoStable(NATIVE_ID);
    }

    /**
     * 释放资源
     *
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:56
     */
    @Override
    public void close() {
        SeetafaceValidated.validate(isClose);
        FaceTrackerNative.close(NATIVE_ID);
        isClose = true;
    }

}
