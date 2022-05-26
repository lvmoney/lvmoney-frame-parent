package com.lvmoney.frame.ai.seetaface.jni.server;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.common.exception.SeetafaceException;
import com.lvmoney.frame.ai.seetaface.jni.natives.AgePredictorNative;
import com.lvmoney.frame.ai.seetaface.jni.util.SeetafaceValidated;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaImageData;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaModelSetting;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaPointF;
import com.lvmoney.frame.base.core.exception.BusinessException;

import java.io.Closeable;

/**
 * @describe：年龄估计器
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class AgePredictor implements Closeable {

    private final long NATIVE_ID;

    private boolean isClose = false;

    /**
     * 年龄估计器
     *
     * @param cstaPath: age_predictor.csta的路径
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 9:45
     */
    public AgePredictor(String cstaPath) {
        this(new SeetaModelSetting(cstaPath));
    }

    /**
     * 年龄估计器
     *
     * @param seetaModelSetting:
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 9:45
     */
    public AgePredictor(SeetaModelSetting seetaModelSetting) {
        if (seetaModelSetting == null) {
            throw new BusinessException(SeetafaceException.Proxy.SETTINGIS_NULL);
        }
        NATIVE_ID = AgePredictorNative.init(seetaModelSetting);
    }

    /**
     * 裁剪人脸
     *
     * @param image:  原始图像数据
     * @param points: 人脸特征点数组
     * @throws
     * @return: SeetaImageData 裁剪成功的人脸
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 9:46
     */
    public SeetaImageData cropFace(SeetaImageData image, SeetaPointF[] points) {
        SeetafaceValidated.validate(isClose, image, points);
        return AgePredictorNative.cropFace(NATIVE_ID, image, points);
    }

    /**
     * 输入裁剪好的人脸，返回估计的年龄。
     *
     * @param image: 裁剪好的人脸数据
     * @throws
     * @return: int 估计的年龄 估计失败时返回-1
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 9:46
     */
    public int predictAge(SeetaImageData image) {
        SeetafaceValidated.validate(isClose, image);
        return AgePredictorNative.predictAge(NATIVE_ID, image);
    }

    /**
     * 输入原始图像数据和人脸特征点，返回估计的年龄。
     *
     * @param image:  原始人脸数据
     * @param points: 人脸特征点
     * @throws
     * @return: int 估计的年龄 估计失败时返回-1
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 9:46
     */
    public int predictAgeWithCrop(SeetaImageData image, SeetaPointF[] points) {
        SeetafaceValidated.validate(isClose, image, points);
        return AgePredictorNative.predictAgeWithCrop(NATIVE_ID, image, points);
    }

    /**
     * 设置相关属性值
     *
     * @param property: 属性类别 参考AgePredictor.Property.ordinal()
     * @param value:    设置的属性值
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 9:47
     */
    public void set(Property property, double value) {
        SeetafaceValidated.validate(isClose, property);
        AgePredictorNative.set(NATIVE_ID, property.getValue(), value);
    }

    /**
     * 获取相关属性值
     *
     * @param property: 属性类别
     * @throws
     * @return: double 对应的属性值
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 9:47
     */
    public double get(Property property) {
        SeetafaceValidated.validate(isClose, property);
        return AgePredictorNative.get(NATIVE_ID, property.getValue());
    }

    /**
     * 释放资源
     *
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 9:48
     */
    @Override
    public void close() {
        SeetafaceValidated.validate(isClose);
        AgePredictorNative.close(NATIVE_ID);
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

        int num;

        Property(int num) {
            this.num = num;
        }

        public int getValue() {
            return num;
        }

    }

}
