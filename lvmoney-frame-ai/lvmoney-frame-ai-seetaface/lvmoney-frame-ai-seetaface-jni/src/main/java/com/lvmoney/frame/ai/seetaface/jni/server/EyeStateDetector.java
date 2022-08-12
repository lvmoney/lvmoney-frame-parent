package com.lvmoney.frame.ai.seetaface.jni.server;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.common.exception.SeetafaceException;
import com.lvmoney.frame.ai.seetaface.jni.natives.EyeStateDetectorNative;
import com.lvmoney.frame.ai.seetaface.jni.util.SeetafaceValidated;
import com.lvmoney.frame.ai.seetaface.jni.vo.EyeState;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaImageData;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaModelSetting;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaPointF;
import com.lvmoney.frame.base.core.exception.BusinessException;

import java.io.Closeable;

/**
 * @describe：眼睛状态检测器
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class EyeStateDetector implements Closeable {

    private final long NATIVE_ID;

    private boolean isClose = false;

    /**
     * 眼睛状态检测器
     *
     * @param cstaPath: eye_state.csta的路径
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:26
     */
    public EyeStateDetector(String cstaPath) {
        this(new SeetaModelSetting(cstaPath));
    }

    /**
     * 眼睛状态检测器
     *
     * @param seetaModelSetting:
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:26
     */
    public EyeStateDetector(SeetaModelSetting seetaModelSetting) {
        if (seetaModelSetting == null) {
            throw new BusinessException(SeetafaceException.Proxy.SETTINGIS_NULL);
        }
        NATIVE_ID = EyeStateDetectorNative.init(seetaModelSetting);
    }

    /**
     * 输入原始图像数据和人脸特征点，返回左眼和右眼的状态。
     *
     * @param image:  原始图像数据
     * @param points: * @param points 人脸特征点数组
     * @throws
     * @return: EyeState 眼睛状态
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:28
     */
    public EyeState detect(SeetaImageData image, SeetaPointF[] points) {
        SeetafaceValidated.validate(isClose, image, points);
        return EyeStateDetectorNative.detect(NATIVE_ID, image, points);
    }

    /**
     * 设置相关属性值
     *
     * @param property: 属性类型 参考EyeStateDetector.Property
     * @param value:    设置的属性值
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:29
     */
    public void set(Property property, double value) {
        SeetafaceValidated.validate(isClose, property);
        EyeStateDetectorNative.set(NATIVE_ID, property.getValue(), value);
    }

    /**
     * 获取相关属性值
     *
     * @param property: 属性类型 参考EyeStateDetector.Property
     * @throws
     * @return: double 属性值
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:29
     */
    public double get(Property property) {
        SeetafaceValidated.validate(isClose, property);
        return EyeStateDetectorNative.get(NATIVE_ID, property.getValue());
    }

    /**
     * 释放资源
     *
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:30
     */
    @Override
    public void close() {
        SeetafaceValidated.validate(isClose);
        EyeStateDetectorNative.close(NATIVE_ID);
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
