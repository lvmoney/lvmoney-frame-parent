package com.lvmoney.frame.ai.seetaface.jni.server;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.jni.natives.QualityOfPoseExNative;
import com.lvmoney.frame.ai.seetaface.jni.util.SeetafaceValidated;
import com.lvmoney.frame.ai.seetaface.jni.vo.*;

import java.io.Closeable;

/**
 * @describe：深度学习的人脸姿态评估器
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class QualityOfPoseEx implements Closeable {

    private final long NATIVE_ID;

    private boolean isClose = false;

    /**
     * 人脸姿态评估器构造函数
     *
     * @param cstaPath: pose_estimation.csta的路径
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:30
     */
    public QualityOfPoseEx(String cstaPath) {
        this(new SeetaModelSetting(cstaPath));
    }

    /**
     * 人脸姿态评估器构造函数
     *
     * @param setting:
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:31
     */
    public QualityOfPoseEx(SeetaModelSetting setting) {
        NATIVE_ID = QualityOfPoseExNative.init(setting);
    }

    /**
     * @param image:  原始图像数据
     * @param face:   人脸位置
     * @param points: 人脸5个特征点数组
     * @throws
     * @return: QualityResult 人脸姿态检测结果
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:31
     */
    public QualityResult check(SeetaImageData image, SeetaRect face, SeetaPointF[] points) {
        SeetafaceValidated.validate(isClose, image, face, points);
        return QualityOfPoseExNative.check(NATIVE_ID, image, face, points);
    }

    /**
     * 设置相关属性
     *
     * @param property: QualityOfPoseEx在c++的序列化
     * @param value:    属性 参考 QualityOfPose.Property.getValue()
     * @throws
     * @return: void 值
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:31
     */
    public void set(Property property, double value) {
        SeetafaceValidated.validate(isClose, property);
        QualityOfPoseExNative.set(NATIVE_ID, property.getValue(), value);
    }

    /**
     * 获取相关属性值
     *
     * @param nativeId: QualityOfPoseEx在c++的序列化
     * @param property: 属性 参考 QualityOfPose.Property.getValue()
     * @throws
     * @return: double 值
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:32
     */
    public double get(long nativeId, Property property) {
        SeetafaceValidated.validate(isClose, property);
        return QualityOfPoseExNative.get(NATIVE_ID, property.getValue());
    }

    /**
     * 释放资源
     *
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:33
     */
    @Override
    public void close() {
        SeetafaceValidated.validate(isClose);
        QualityOfPoseExNative.close(NATIVE_ID);
        this.isClose = true;
    }

    public enum Property {
        YAW_LOW_THRESHOLD(0),
        YAW_HIGH_THRESHOLD(1),
        PITCH_LOW_THRESHOLD(2),
        PITCH_HIGH_THRESHOLD(3),
        ROLL_LOW_THRESHOLD(4),
        ROLL_HIGH_THRESHOLD(5);

        private int num;

        Property(int num) {
            this.num = num;
        }

        public int getValue() {
            return num;
        }
    }

}
