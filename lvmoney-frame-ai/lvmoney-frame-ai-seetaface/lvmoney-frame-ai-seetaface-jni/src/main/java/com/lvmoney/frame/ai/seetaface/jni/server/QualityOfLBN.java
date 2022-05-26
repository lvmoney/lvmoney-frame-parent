package com.lvmoney.frame.ai.seetaface.jni.server;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.jni.natives.QualityOfLBNNative;
import com.lvmoney.frame.ai.seetaface.jni.util.SeetafaceValidated;
import com.lvmoney.frame.ai.seetaface.jni.vo.BlurInfo;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaImageData;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaModelSetting;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaPointF;

import java.io.Closeable;

/**
 * @describe：深度学习的人脸清晰度评估器
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class QualityOfLBN implements Closeable {

    private final long NATIVE_ID;

    private boolean isClose = false;

    /**
     * 深度学习的人脸清晰度评估器
     *
     * @param cstaPath: quality_lbn.csta的路径
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:13
     */
    public QualityOfLBN(String cstaPath) {
        this(new SeetaModelSetting(cstaPath));
    }

    /**
     * 深度学习的人脸清晰度评估器
     *
     * @param setting:
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:13
     */
    public QualityOfLBN(SeetaModelSetting setting) {
        NATIVE_ID = QualityOfLBNNative.init(setting);
    }

    /**
     * 检测人脸清晰度
     *
     * @param image:  原始图像数据
     * @param points: 人脸68个特征点数组
     * @throws
     * @return: BlurInfo 模糊信息
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:13
     */
    public BlurInfo detect(SeetaImageData image, SeetaPointF[] points) {
        SeetafaceValidated.validate(isClose, image, points);
        return QualityOfLBNNative.detect(NATIVE_ID, image, points);
    }

    /**
     * 设置相关属性值
     *
     * @param property: 参考 QualityOfLBN.Property.getValue
     * @param value:    设置的属性值
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:27
     */
    public void set(Property property, double value) {
        SeetafaceValidated.validate(isClose, property);
        QualityOfLBNNative.set(NATIVE_ID, property.getValue(), value);
    }

    /**
     * 获取相关属性值
     *
     * @param property: 参考 QualityOfLBN.Property.getValue
     * @throws
     * @return: double 设置的属性值
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:28
     */
    public double get(Property property) {
        SeetafaceValidated.validate(isClose, property);
        return QualityOfLBNNative.get(NATIVE_ID, property.getValue());
    }

    /**
     * 释放资源
     *
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:28
     */
    @Override
    public void close() {
        SeetafaceValidated.validate(isClose);
        QualityOfLBNNative.close(NATIVE_ID);
        isClose = true;
    }

    public enum Property {
        /**
         * 表示计算线程数，默认为 4
         */
        PROPERTY_NUMBER_THREADS(4),
        /**
         * 针对于移动端，表示设置的 cpu 计算模式。0 表示大核计算模式，1 表示小核计算模式，2 表示平衡模式，为默认模式。
         */
        PROPERTY_ARM_CPU_MODE(5),
        PROPERTY_LIGHT_THRESH(10),
        /**
         * 表示人脸模糊阈值，默认值大小为 0.80
         */
        PROPERTY_BLUR_THRESH(11),
        PROPERTY_NOISE_THRESH(12);

        int value;

        Property(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
