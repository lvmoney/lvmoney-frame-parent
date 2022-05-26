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
 * @describe：年龄估计器要求输入原始图像数据和人脸特征点（或者裁剪好的人脸数据），对输入的人脸进行年龄估计
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class AgePredictorNative {

    /**
     * 初始化一个AgePredictor
     *
     * @param setting: 配置
     * @throws
     * @return: long AgePredictor在c++持久化的序列号
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 20:59
     */
    public static native long init(SeetaModelSetting setting);

    /**
     * 裁剪人脸
     *
     * @param nativeId: AgePredictor在c++持久化的序列号
     * @param image:    原始图像数据
     * @param points:   人脸特征点数组
     * @throws
     * @return: com.lvmoney.frame.ai.seetaface.core.vo.SeetaImageData 裁剪成功的人脸
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 20:59
     */
    public static native SeetaImageData cropFace(long nativeId, SeetaImageData image, SeetaPointF[] points);

    /**
     * 输入裁剪好的人脸，返回估计的年龄。
     *
     * @param nativeId: AgePredictor在c++持久化的序列号
     * @param image:    裁剪好的人脸数据
     * @throws
     * @return: int 估计的年龄 估计失败时返回-1
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:00
     */
    public static native int predictAge(long nativeId, SeetaImageData image);

    /**
     * 输入原始图像数据和人脸特征点，返回估计的年龄。
     *
     * @param nativeId: AgePredictor在c++持久化的序列号
     * @param image:    原始人脸数据
     * @param points:   人脸特征点
     * @throws
     * @return: int 估计的年龄 估计失败时返回-1
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:01
     */
    public static native int predictAgeWithCrop(long nativeId, SeetaImageData image, SeetaPointF[] points);

    /**
     * 设置相关属性值
     *
     * @param nativeId: AgePredictor在c++持久化的序列号
     * @param property: 属性类别 参考AgePredictor.Property.ordinal()
     * @param value:    设置的属性值
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:01
     */
    public static native void set(long nativeId, int property, double value);

    /**
     * 获取相关属性值
     *
     * @param nativeId: AgePredictor在c++持久化的序列号
     * @param property: 属性类别
     * @throws
     * @return: double 对应的属性值
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:02
     */
    public static native double get(long nativeId, int property);

    /**
     * 释放资源
     *
     * @param nativeId: AgePredictor在c++持久化的序列号
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:02
     */
    public static native void close(long nativeId);
}
