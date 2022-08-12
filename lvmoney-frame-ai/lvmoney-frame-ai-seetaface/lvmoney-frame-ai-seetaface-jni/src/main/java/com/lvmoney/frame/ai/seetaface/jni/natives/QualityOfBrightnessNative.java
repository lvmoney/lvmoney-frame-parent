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
 * 非深度的人脸亮度评估器
 *
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class QualityOfBrightnessNative {

    /**
     * 默认值为{level0:70, 100, 210, 230}
     *
     * @throws
     * @return: long
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:50
     */
    public static native long init();

    /**
     * 分类依据为[0, v0) & [v3, ~) => LOW;[v0, v1) & [v2, v3) => MEDIUM;[v1, v2) => HIGH;
     *
     * @param v0: 分级参数一
     * @param v1: 分级参数二
     * @param v2: 分级参数三
     * @param v3: 分级参数四
     * @throws
     * @return: long QualityOfBrightness在C++的序列化
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:50
     */
    public static native long init(float v0, float v1, float v2, float v3);

    /**
     * 检测人脸亮度
     *
     * @param nativeId: QualityOfBrightness在C++的序列化
     * @param image:    原始图像数据
     * @param face:     人脸位置
     * @param points:   人脸特征点数组
     * @throws
     * @return: QualityResult 人脸亮度检测结果
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:51
     */
    public static native QualityResult check(long nativeId, SeetaImageData image, SeetaRect face, SeetaPointF[] points);

    /**
     * 释放资源
     *
     * @param nativeId: QualityOfBrightness在C++的序列化
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:51
     */
    public static native void close(long nativeId);

}
