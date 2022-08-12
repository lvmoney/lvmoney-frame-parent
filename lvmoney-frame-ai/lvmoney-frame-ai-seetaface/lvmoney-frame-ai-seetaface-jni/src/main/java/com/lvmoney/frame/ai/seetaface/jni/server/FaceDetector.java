package com.lvmoney.frame.ai.seetaface.jni.server;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.common.exception.SeetafaceException;
import com.lvmoney.frame.ai.seetaface.jni.natives.FaceDetectorNative;
import com.lvmoney.frame.ai.seetaface.jni.util.SeetafaceValidated;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaFaceInfoArray;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaImageData;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaModelSetting;
import com.lvmoney.frame.base.core.exception.BusinessException;

import java.io.Closeable;

/**
 * @describe：人脸检测器
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class FaceDetector implements Closeable {

    private Long NATIVE_ID;

    private boolean isClose = false;

    /**
     *人脸检测器
     * @param cstaPath: face_detector.csta模型路径
     * @return: null
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:38
     */
    public FaceDetector(String cstaPath) {
        this(new SeetaModelSetting(cstaPath));
    }

    /**
     *人脸检测器
     * @param setting: 检测器结构参数
     * @return: null
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:38
     */
    public FaceDetector(SeetaModelSetting setting) {
        if (setting == null) {
            throw new BusinessException(SeetafaceException.Proxy.SETTINGIS_NULL);
        }
        NATIVE_ID = FaceDetectorNative.init(setting);
    }

    /**
     *输入彩色图像，检测其中的人脸。
     * @param image: 输入的图像数据
     * @return: SeetaFaceInfoArray 人脸信息数组
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:39
     */
    public SeetaFaceInfoArray detect(SeetaImageData image) {
        SeetafaceValidated.validate(isClose, image);
        return FaceDetectorNative.detect(NATIVE_ID, image);
    }

    /**
     *设置人脸检测器相关属性值 property来自{@linkplain Property}
     * @param property: 属性类型
     * @param value: 设置的属性值
     * @return: void
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:39
     */
    public void set(Property property, double value) {
        SeetafaceValidated.validate(isClose, property);
        FaceDetectorNative.set(NATIVE_ID, property.getValue(), value);
    }

    /**
     *获取人脸检测器相关属性值 property来自{@linkplain Property}
     * @param property: 属性类型
     * @return: double 设置的属性值
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:40
     */
    public double get(Property property) {
        SeetafaceValidated.validate(isClose, property);
        return FaceDetectorNative.get(NATIVE_ID, property.getValue());
    }

    /**
     *释放资源

     * @return: void
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:40
     */
    @Override
    public void close() {
        SeetafaceValidated.validate(isClose);
        FaceDetectorNative.close(NATIVE_ID);
        isClose = true;
    }

    public enum Property {
        /**
         * 表示人脸检测器可以检测到的最小人脸，该值越小，支持检测到的人脸尺寸越小，检测速度越慢，默认值为20
         */
        PROPERTY_MIN_FACE_SIZE(0),
        /**
         * 表示人脸检测器过滤阈值，默认为 0.90
         */
        PROPERTY_THRESHOLD(1),
        /**
         * 表示支持输入的图像的最大宽度
         */
        PROPERTY_MAX_IMAGE_WIDTH(2),
        /**
         * 表示支持输入的图像的最大高度
         */
        PROPERTY_MAX_IMAGE_HEIGHT(3),
        /**
         * 表示人脸检测器计算线程数 默认为 4.
         */
        PROPERTY_NUMBER_THREADS(4),
        PROPERTY_ARM_CPU_MODE(0x101);

        private int num;

        Property(int num) {
            this.num = num;
        }

        public int getValue() {
            return num;
        }
    }

}
