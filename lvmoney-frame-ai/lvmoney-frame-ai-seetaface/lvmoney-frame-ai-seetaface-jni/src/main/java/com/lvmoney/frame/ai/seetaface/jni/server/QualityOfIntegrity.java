package com.lvmoney.frame.ai.seetaface.jni.server;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.jni.natives.QualityOfIntegrityNative;
import com.lvmoney.frame.ai.seetaface.jni.util.SeetafaceValidated;
import com.lvmoney.frame.ai.seetaface.jni.vo.QualityResult;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaImageData;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaPointF;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaRect;

import java.io.Closeable;

/**
 * @describe：非深度学习的人脸完整度评估器，评估人脸靠近图像边缘的程度。
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class QualityOfIntegrity implements Closeable {

    private final long NATIVE_ID;

    private boolean isClose = false;

    /**
     * 人脸完整评估器构造函数
     *
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:07
     */
    public QualityOfIntegrity() {
        NATIVE_ID = QualityOfIntegrityNative.init();
    }

    /**
     * 人脸完整评估器构造函数。 low和high主要来控制人脸位置靠近图像边缘的接受程度。
     *
     * @param low:  分级参数一
     * @param high: 分级参数二
     * @throws
     * @return: null QualityOfPose在C++的序列化
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:07
     */
    public QualityOfIntegrity(float low, float high) {
        NATIVE_ID = QualityOfIntegrityNative.init(low, high);
    }

    /**
     * 评估人脸完整度
     *
     * @param image:  原始图像数据
     * @param face:   人脸位置
     * @param points: 人脸5个特征数组
     * @throws
     * @return: QualityResult 人脸姿态检测结果
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:08
     */
    public QualityResult check(SeetaImageData image, SeetaRect face, SeetaPointF[] points) {
        SeetafaceValidated.validate(isClose, image, face, points);
        return QualityOfIntegrityNative.check(NATIVE_ID, image, face, points);
    }

    /**
     * 释放资源
     *
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:08
     */
    @Override
    public void close() {
        SeetafaceValidated.validate(isClose);
        QualityOfIntegrityNative.close(NATIVE_ID);
        isClose = true;
    }

}
