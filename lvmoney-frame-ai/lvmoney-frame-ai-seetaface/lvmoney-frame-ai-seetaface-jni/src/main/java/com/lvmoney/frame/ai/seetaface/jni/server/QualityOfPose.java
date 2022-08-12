package com.lvmoney.frame.ai.seetaface.jni.server;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.jni.natives.QualityOfPoseNative;
import com.lvmoney.frame.ai.seetaface.jni.util.SeetafaceValidated;
import com.lvmoney.frame.ai.seetaface.jni.vo.QualityResult;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaImageData;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaPointF;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaRect;

import java.io.Closeable;

/**
 * @describe：非深度学习的人脸姿态评估器
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class QualityOfPose implements Closeable {

    private final long NATIVE_ID;

    private boolean isClose = false;

    /**
     * 非深度学习的人脸姿态评估器
     *
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:29
     */
    public QualityOfPose() {
        NATIVE_ID = QualityOfPoseNative.init();
    }

    /**
     * 检测人脸姿态
     *
     * @param image:  原始图像数据
     * @param face:   人脸位置
     * @param points: 人脸5个特征数组
     * @throws
     * @return: QualityResult 人脸姿态检测结果
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:29
     */
    public QualityResult check(SeetaImageData image, SeetaRect face, SeetaPointF[] points) {
        SeetafaceValidated.validate(isClose, image, face, points);
        return QualityOfPoseNative.check(NATIVE_ID, image, face, points);
    }

    /**
     * 释放资源
     *
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:30
     */
    @Override
    public void close() {
        SeetafaceValidated.validate(isClose);
        QualityOfPoseNative.close(NATIVE_ID);
        this.isClose = true;
    }

}
