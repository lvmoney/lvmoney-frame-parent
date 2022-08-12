package com.lvmoney.frame.ai.seetaface.jni.natives;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.jni.vo.QualityResult;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaImageData;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaPointF;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaRect;

/**
 * @describe：非深度学习的人脸尺寸评估器
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class QualityOfResolutionNative {

    /**
     * 人脸尺寸评估器构造函数
     *
     * @throws
     * @return: long QualityOfPose在C++的序列化
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 22:09
     */
    public static native long init();

    /**
     * 人脸尺寸评估器构造函数
     *
     * @param low:  分级参数一
     * @param high: 分级参数二
     * @throws
     * @return: long QualityOfPose在C++的序列化
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 22:09
     */
    public static native long init(float low, float high);

    /**
     * 评估人脸尺寸
     *
     * @param nativeId: QualityOfPose在C++的序列化
     * @param image:    原始图像数据
     * @param face:     人脸位置
     * @param points:   人脸5个特征数组
     * @throws
     * @return: QualityResult 人脸姿态检测结果
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 22:09
     */
    public static native QualityResult check(long nativeId, SeetaImageData image, SeetaRect face, SeetaPointF[] points);

    /**
     * 释放资源
     *
     * @param nativeId: QualityOfPose在C++的序列化
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 22:11
     */
    public static native void close(long nativeId);

}
