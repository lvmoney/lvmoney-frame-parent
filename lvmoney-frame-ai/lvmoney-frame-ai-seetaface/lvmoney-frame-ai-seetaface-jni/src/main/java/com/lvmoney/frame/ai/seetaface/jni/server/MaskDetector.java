package com.lvmoney.frame.ai.seetaface.jni.server;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.jni.natives.MaskDetectorNative;
import com.lvmoney.frame.ai.seetaface.jni.util.SeetafaceValidated;
import com.lvmoney.frame.ai.seetaface.jni.vo.MaskStatus;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaImageData;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaModelSetting;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaRect;

import java.io.Closeable;

/**
 * @describe：口罩检测器
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class MaskDetector implements Closeable {

    private final long NATIVE_ID;

    private boolean isClose = false;

    /**
     * 口罩检测器
     *
     * @param cstaPath: mask_detector.csta的路径
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:01
     */
    public MaskDetector(String cstaPath) {
        this(new SeetaModelSetting(cstaPath));
    }

    /**
     * 口罩检测器
     *
     * @param seetaModelSetting:
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:02
     */
    public MaskDetector(SeetaModelSetting seetaModelSetting) {
        NATIVE_ID = MaskDetectorNative.init(seetaModelSetting);
    }

    /**
     * 输入图像数据、人脸位置，返回是否佩戴口罩的检测结果。
     *
     * @param image: 原始图像数据
     * @param face:  人脸位置
     * @throws
     * @return: MaskStatus false没戴口罩/true戴了口罩
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:02
     */
    public MaskStatus detect(SeetaImageData image, SeetaRect face) {
        SeetafaceValidated.validate(isClose, image, face);
        return MaskDetectorNative.detect(NATIVE_ID, image, face);
    }

    /**
     * 释放资源
     *
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 11:02
     */
    @Override
    public void close() {
        SeetafaceValidated.validate(isClose);
        MaskDetectorNative.close(NATIVE_ID);
        isClose = true;
    }

}
