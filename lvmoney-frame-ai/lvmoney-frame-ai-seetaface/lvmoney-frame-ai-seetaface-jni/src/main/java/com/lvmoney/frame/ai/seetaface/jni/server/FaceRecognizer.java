package com.lvmoney.frame.ai.seetaface.jni.server;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.jni.natives.FaceRecognizerNative;
import com.lvmoney.frame.ai.seetaface.jni.util.SeetafaceValidated;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaImageData;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaModelSetting;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaPointF;

import java.io.Closeable;

/**
 * @describe：人脸识别器 人脸识别器要求输入原始图像数据和人脸特征点（或者裁剪好的人脸数据），对输入的人脸提取特征值数组，根据提取的特征值数组对人脸进行相似度比较。
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class FaceRecognizer implements Closeable {

    private final long NATIVE_ID;

    private boolean isClose = false;

    /**
     *人脸识别器的构造函数
     * @param cstaPath:
     * @return: null
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:45
     */
    public FaceRecognizer(String cstaPath) {
        this(new SeetaModelSetting(cstaPath));
    }

    /**
     *人脸识别器的构造函数
     * @param setting:
     * @return: null
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:45
     */
    public FaceRecognizer(SeetaModelSetting setting) {
        NATIVE_ID = FaceRecognizerNative.init(setting);
    }

    /**
     *裁剪人脸
     * @param image:    原始图像数据
     * @param points: 人脸特征点数组
     * @return: SeetaImageData 返回的裁剪人脸
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:45
     */
    public SeetaImageData cropFace(SeetaImageData image, SeetaPointF[] points) {
        SeetafaceValidated.validate(isClose, image, points);
        return FaceRecognizerNative.cropFace(NATIVE_ID, image, points);
    }

    /**
     *输入裁剪后的人脸图像，提取人脸的特征值数组
     * @param face: 裁剪后的人脸图像数据
     * @return: float[] 如果提取成功返回数组 如果提取失败返回null
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:46
     */
    public float[] extractCroppedFace(SeetaImageData face) {
        SeetafaceValidated.validate(isClose, face);
        return FaceRecognizerNative.extractCroppedFace(NATIVE_ID, face);
    }

    /**
     *输入原始图像数据和人脸特征点数组，提取人脸的特征值数组。
     * @param image: 裁剪后的人脸图像数据
     * @param points:
     * @return: float[] 如果提取成功返回数组 如果提取失败返回null
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:46
     */
    public float[] extract(SeetaImageData image, SeetaPointF[] points) {
        SeetafaceValidated.validate(isClose, image, points);
        return FaceRecognizerNative.extract(NATIVE_ID, image, points);
    }

    /**
     *比较两人脸的特征值数据，获取人脸的相似度值。
     * @param features1: 特征数组一
     * @param features2: 特征数组二
     * @return: float 相似度值
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:47
     */
    public float calculateSimilarity(float[] features1, float[] features2) {
        SeetafaceValidated.validate(isClose, features1, features2);
        return FaceRecognizerNative.calculateSimilarity(NATIVE_ID, features1, features2);
    }

    /**
     *设置相关属性值
     * @param property: 属性 参考 FaceRecognizer.Property.getValue()
     * @param value: 设置的属性值
     * @return: void
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:47
     */
    public void set(Property property, double value) {
        SeetafaceValidated.validate(isClose, property);
        FaceRecognizerNative.set(NATIVE_ID, property.getValue(), value);
    }

    /**
     *获取相关属性值
     * @param property: 属性 参考 FaceRecognizer.Property.getValue()
     * @return: double 设置的属性值
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:47
     */
    public double get(Property property) {
        SeetafaceValidated.validate(isClose, property);
        return FaceRecognizerNative.get(NATIVE_ID, property.getValue());
    }

    /**
     *释放C++资源

     * @return: void
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:48
     */
    @Override
    public void close() {
        SeetafaceValidated.validate(isClose);
        FaceRecognizerNative.close(NATIVE_ID);
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

        Property(int num) {
            this.num = num;
        }

        public int getValue() {
            return num;
        }
    }
}
