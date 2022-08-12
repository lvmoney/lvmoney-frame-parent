package com.lvmoney.frame.ai.seetaface.jni.natives;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaImageData;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaModelSetting;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaPointF;

/**
 * @describe：性别估计器 性别估计器要求输入原始图像数据和人脸特征点（或者裁剪好的人脸数据），对输入的人脸进行性别估计。
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class GenderPredictorNative {

    /**
     * 初始化一个GenderPredictor
     *
     * @param setting: 配置
     * @throws
     * @return: long GenderPredictor在c++持久化的序列号
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:45
     */
    public static native long init(SeetaModelSetting setting);

    /**
     * 裁剪人脸
     *
     * @param nativeId: 持久化ID
     * @param image:    原始图像数据
     * @param points:   * @param points 人脸特征点数
     * @throws
     * @return: SeetaImageData 裁剪好的人脸数据
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:45
     */
    public static native SeetaImageData cropFace(long nativeId, SeetaImageData image, SeetaPointF[] points);

    /**
     * 裁剪好的人脸数据
     *
     * @param nativeId: 持久化ID
     * @param face:     裁剪好的人脸数据
     * @throws
     * @return: int
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:45
     */
    public static native int predictGender(long nativeId, SeetaImageData face);

    /**
     * 输入原始图像数据和人脸特征点，返回估计的性别。
     *
     * @param nativeId: 持久化ID
     * @param image:    原始人脸数据
     * @param points:   人脸特征点
     * @throws
     * @return: int
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:46
     */
    public static native int predictGenderWithCrop(long nativeId, SeetaImageData image, SeetaPointF[] points);

    /**
     * 设置相关属性值
     *
     * @param nativeId: 持久化ID
     * @param property: 属性类型 参考GenderPredictor.Property.ordinal()
     * @param value:    设置的属性值
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:46
     */
    public static native void set(long nativeId, int property, double value);

    /**
     * 获取相关属性值
     *
     * @param nativeId: 持久化ID
     * @param property: 属性类型 参考GenderPredictor.Property.ordinal()
     * @throws
     * @return: double 属性值
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:47
     */
    public static native double get(long nativeId, int property);

    /**
     * 释放资源
     *
     * @param nativeId:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:47
     */
    public static native void close(long nativeId);

}
