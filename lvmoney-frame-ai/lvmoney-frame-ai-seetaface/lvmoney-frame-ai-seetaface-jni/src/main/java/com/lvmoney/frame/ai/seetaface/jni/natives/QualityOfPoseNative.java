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
 * @describe：非深度学习的人脸姿态评估器
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class QualityOfPoseNative {

    /**
     * @throws
     * @return: long QualityOfPose在C++的序列化
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 22:06
     */
    public static native long init();

    /**
     * 检测人脸姿态
     *
     * @param nativeId: QualityOfPose在C++的序列化
     * @param image:    原始图像数据
     * @param face:     人脸位置
     * @param points:   人脸5个特征数组
     * @throws
     * @return: QualityResult 人脸姿态检测结果
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 22:06
     */
    public static native QualityResult check(long nativeId, SeetaImageData image, SeetaRect face, SeetaPointF[] points);

    /**
     * 释放资源
     *
     * @param nativeId: QualityOfPose在C++的序列化
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 22:07
     */
    public static native void close(long nativeId);

}
