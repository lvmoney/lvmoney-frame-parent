package com.lvmoney.frame.ai.seetaface.jni.natives;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.jni.vo.BlurInfo;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaImageData;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaModelSetting;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaPointF;

/**
 * @describe：深度学习的人脸清晰度评估器
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class QualityOfLBNNative {

    /**
     * 人脸清晰度评估器构造函数
     *
     * @param setting: 对象构造结构体参数
     * @throws
     * @return: long QualityOfLBN在C++的序列号
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:58
     */
    public static native long init(SeetaModelSetting setting);

    /**
     * 检测人脸清晰度
     *
     * @param nativeId: QualityOfLBN在C++的序列号
     * @param image:    原始图像数据
     * @param points:   人脸68个特征点数组
     * @throws
     * @return: BlurInfo 模糊信息
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:58
     */
    public static native BlurInfo detect(long nativeId, SeetaImageData image, SeetaPointF[] points);

    /**
     * 设置相关属性值
     *
     * @param nativeId: QualityOfLBN在C++的序列号
     * @param property: 参考 QualityOfLBN.Property.getValue
     * @param value:    设置的属性值
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:58
     */
    public static native void set(long nativeId, int property, double value);

    /**
     * 获取相关属性值
     *
     * @param nativeId: QualityOfLBN在C++的序列号
     * @param property: 参考 QualityOfLBN.Property.getValue
     * @throws
     * @return: 设置的属性值
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:59
     */
    public static native double get(long nativeId, int property);

    /**
     * 释放资源
     *
     * @param nativeId: QualityOfLBN在C++的序列号
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:59
     */
    public static native void close(long nativeId);

}
