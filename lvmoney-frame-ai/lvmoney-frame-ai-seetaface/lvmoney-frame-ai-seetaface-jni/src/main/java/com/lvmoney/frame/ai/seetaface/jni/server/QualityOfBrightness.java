package com.lvmoney.frame.ai.seetaface.jni.server;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.jni.util.SeetafaceValidated;
import com.lvmoney.frame.ai.seetaface.jni.vo.QualityResult;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaImageData;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaPointF;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaRect;
import com.lvmoney.frame.ai.seetaface.jni.natives.QualityOfBrightnessNative;

import java.io.Closeable;

/**
 * @describe：非深度的人脸亮度评估器
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class QualityOfBrightness implements Closeable {

    private final long NATIVE_ID;

    private boolean isClose = false;

    /**
     * 默认值为{level0:70, 100, 210, 230}
     *
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:04
     */
    public QualityOfBrightness() {
        NATIVE_ID = QualityOfBrightnessNative.init();
    }

    /**
     * 分类依据为[0, v0) & [v3, ~) => LOW;[v0, v1) & [v2, v3) => MEDIUM;[v1, v2) => HIGH;
     *
     * @param v0:
     * @param v1:
     * @param v2:
     * @param v3:
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:04
     */
    public QualityOfBrightness(float v0, float v1, float v2, float v3) {
        NATIVE_ID = QualityOfBrightnessNative.init(v0, v1, v2, v3);
    }

    /**
     * 检测人脸亮度
     *
     * @param image:  原始图像数据
     * @param face:   人脸位置
     * @param points: 人脸特征点数组
     * @throws
     * @return: QualityResult 人脸亮度检测结果
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:04
     */
    public QualityResult check(SeetaImageData image, SeetaRect face, SeetaPointF[] points) {
        SeetafaceValidated.validate(isClose, image, face, points);
        return QualityOfBrightnessNative.check(NATIVE_ID, image, face, points);
    }

    /**
     * 释放资源
     *
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:05
     */
    @Override
    public void close() {
        SeetafaceValidated.validate(isClose);
        QualityOfBrightnessNative.close(NATIVE_ID);
        isClose = true;
    }

}
