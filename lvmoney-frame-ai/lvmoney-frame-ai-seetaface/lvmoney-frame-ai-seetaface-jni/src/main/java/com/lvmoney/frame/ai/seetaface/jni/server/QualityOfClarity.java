package com.lvmoney.frame.ai.seetaface.jni.server;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.jni.natives.QualityOfClarityNative;
import com.lvmoney.frame.ai.seetaface.jni.util.SeetafaceValidated;
import com.lvmoney.frame.ai.seetaface.jni.vo.QualityResult;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaImageData;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaPointF;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaRect;

import java.io.Closeable;

/**
 * @describe：非深度学习的人脸清晰度评估器
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class QualityOfClarity implements Closeable {

    private final long NATIVE_ID;

    private boolean isClose = false;

    /**
     * 默认值为low=0.1 high=0.2 {@link this#init(float, float)}
     *
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:05
     */
    public QualityOfClarity() {
        NATIVE_ID = QualityOfClarityNative.init();
    }

    /**
     * <pre>
     * [0, low)=> LOW 0
     * [low, high)=> MEDIUM 1
     * [high, ~)=> HIGH 2
     * </pre>
     *
     * @param low:  分级参数一
     * @param high: 分级参数二
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:06
     */
    public QualityOfClarity(float low, float high) {
        NATIVE_ID = QualityOfClarityNative.init(low, high);
    }

    /**
     * 检测人脸清晰度
     *
     * @param image:  原始图像数据
     * @param face:   人脸位置
     * @param points: 人脸特征点数组
     * @throws
     * @return: QualityResult 人脸清晰度检测结果
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:06
     */
    public QualityResult check(SeetaImageData image, SeetaRect face, SeetaPointF[] points) {
        SeetafaceValidated.validate(isClose, image, face, points);
        return QualityOfClarityNative.check(NATIVE_ID, image, face, points);
    }

    /**
     * 释放资源
     *
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:07
     */
    @Override
    public void close() {
        SeetafaceValidated.validate(isClose);
        QualityOfClarityNative.close(NATIVE_ID);
        isClose = true;
    }

}
