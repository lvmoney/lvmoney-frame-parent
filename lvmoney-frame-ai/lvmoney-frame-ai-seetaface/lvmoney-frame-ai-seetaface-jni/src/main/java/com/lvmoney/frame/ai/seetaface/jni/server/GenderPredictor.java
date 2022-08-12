package com.lvmoney.frame.ai.seetaface.jni.server;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.jni.natives.GenderPredictorNative;
import com.lvmoney.frame.ai.seetaface.jni.util.SeetafaceValidated;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaImageData;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaModelSetting;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaPointF;

import java.io.Closeable;

/**
 * @describe：性别估计器
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class GenderPredictor implements Closeable {

    private final long NATIVE_ID;

    private boolean isClose = false;

    /**
     * 性别估计器
     *
     * @param cstaPath: gender_predictor.csta的路径
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:57
     */
    public GenderPredictor(String cstaPath) {
        this(new SeetaModelSetting(cstaPath));
    }

    /**
     * 性别估计器
     *
     * @param seetaModelSetting:
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:57
     */
    public GenderPredictor(SeetaModelSetting seetaModelSetting) {
        NATIVE_ID = GenderPredictorNative.init(seetaModelSetting);
    }

    /**
     * 裁剪人脸
     *
     * @param image:  原始图像数据
     * @param points: 人脸特征点数
     * @throws
     * @return: SeetaImageData 裁剪好的人脸数据
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:57
     */
    public SeetaImageData cropFace(SeetaImageData image, SeetaPointF[] points) {
        SeetafaceValidated.validate(isClose, image, points);
        return GenderPredictorNative.cropFace(NATIVE_ID, image, points);
    }

    /**
     * 裁剪好的人脸数据
     *
     * @param face: 裁剪好的人脸数据
     * @throws
     * @return: GenderPredictor.Gender
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:58
     */
    public Gender predictGender(SeetaImageData face) {
        SeetafaceValidated.validate(isClose, face);
        int result = GenderPredictorNative.predictGender(NATIVE_ID, face);
        return toGender(result);
    }

    /**
     * @param result:
     * @throws
     * @return: GenderPredictor.Gender
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:58
     */
    private Gender toGender(int result) {
        Gender gender = null;
        switch (result) {
            case -1:
                gender = Gender.UNKNOW;
                break;
            case 0:
                gender = Gender.MALE;
                break;
            case 1:
                gender = Gender.FEMALE;
                break;
        }
        return gender;
    }

    /**
     * 输入原始图像数据和人脸特征点，返回估计的性别。
     *
     * @param image:  原始人脸数据
     * @param points: 人脸特征点
     * @throws
     * @return: GenderPredictor.Gender
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:59
     */
    public Gender predictGenderWithCrop(SeetaImageData image, SeetaPointF[] points) {
        SeetafaceValidated.validate(isClose, image, points);
        int result = GenderPredictorNative.predictGenderWithCrop(NATIVE_ID, image, points);
        return toGender(result);
    }

    /**
     * 设置相关属性值
     *
     * @param property: 属性类型 参考GenderPredictor.Property
     * @param value:    设置的属性值
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:59
     */
    public void set(Property property, double value) {
        SeetafaceValidated.validate(isClose, property);
        GenderPredictorNative.set(NATIVE_ID, property.getValue(), value);
    }

    /**
     * 获取相关属性值
     *
     * @param property: 属性类型 参考GenderPredictor.Property
     * @throws
     * @return: double 属性值
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:59
     */
    public double get(Property property) {
        SeetafaceValidated.validate(isClose, property);
        return GenderPredictorNative.get(NATIVE_ID, property.getValue());
    }

    /**
     * 释放资源
     *
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:00
     */
    @Override
    public void close() {
        SeetafaceValidated.validate(isClose);
        GenderPredictorNative.close(NATIVE_ID);
        isClose = true;
    }

    public enum Property {
        /**
         * 计算线程数，默认为 4.
         */
        PROPERTY_NUMBER_THREADS(4),
        /**
         * 计算线程数，默认为 5.
         */
        PROPERTY_ARM_CPU_MODE(5);

        int num;

        Property(int num) {
            this.num = num;
        }

        public int getValue() {
            return num;
        }
    }

    public enum Gender {
        /**
         * 男性
         */
        MALE,
        /**
         * 女性
         */
        FEMALE,
        /**
         * 未知
         */
        UNKNOW
    }

}
