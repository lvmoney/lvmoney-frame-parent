package com.lvmoney.frame.ai.seetaface.jni.natives;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.jni.vo.MaskStatus;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaImageData;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaModelSetting;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaRect;

/**
 * @describe：口罩检测
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class MaskDetectorNative {

    /**
     * 初始化一个MaskDetector
     *
     * @param setting: 配置
     * @throws
     * @return: long 在c++持久化的序列号
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:48
     */
    public static native long init(SeetaModelSetting setting);

    /**
     * 输入图像数据、人脸位置，返回是否佩戴口罩的检测结果。
     *
     * @param nativeId: MaskDetector在c++持久化的序列号
     * @param image:    原始图像数据
     * @param face:     人脸位置
     * @throws
     * @return: MaskStatus false没戴口罩/true戴了口罩
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:48
     */
    public static native MaskStatus detect(long nativeId, SeetaImageData image, SeetaRect face);

    /**
     * 释放资源
     *
     * @param nativeId: MaskDetector在c++持久化的序列号
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/19 21:49
     */
    public static native void close(long nativeId);

}
