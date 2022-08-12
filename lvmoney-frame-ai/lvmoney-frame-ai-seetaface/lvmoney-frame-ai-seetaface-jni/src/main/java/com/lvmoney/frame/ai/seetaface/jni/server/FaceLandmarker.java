package com.lvmoney.frame.ai.seetaface.jni.server;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.common.exception.SeetafaceException;
import com.lvmoney.frame.ai.seetaface.jni.natives.FaceLandmarkerNative;
import com.lvmoney.frame.ai.seetaface.jni.util.SeetafaceValidated;
import com.lvmoney.frame.ai.seetaface.jni.vo.PointWithMask;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaImageData;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaModelSetting;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaRect;
import com.lvmoney.frame.base.core.exception.BusinessException;

import java.io.Closeable;

/**
 * @describe：人脸特征点检测器
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2020/1/20 14:08
 */
public class FaceLandmarker implements Closeable {

    private Long NATIVE_ID;

    private boolean isClose = false;

    /**
     *人脸特征点检测器
     * @param cstaPath: face_landmarker_pts5.csta或face_landmarker_pts68.csta的路径
     * @return: null
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:41
     */
    public FaceLandmarker(String cstaPath) {
        this(new SeetaModelSetting(cstaPath));
    }

    /**
     *人脸特征点检测器
     * @param seetaModelSetting: 检测器结构参数
     * @return: null
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:49
     */
    public FaceLandmarker(SeetaModelSetting seetaModelSetting) {
        if (seetaModelSetting == null) {
            throw new BusinessException(SeetafaceException.Proxy.SETTINGIS_NULL);
        }
        this.NATIVE_ID = FaceLandmarkerNative.init(seetaModelSetting);
    }

    /**
     *获取模型对应的特征点数组长度

     * @return: int 模型特征点数组长度
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:49
     */
    public int number() {
        SeetafaceValidated.validate(isClose);
        return FaceLandmarkerNative.number(NATIVE_ID);
    }

    /**
     *获取人脸特征点
     * @param image: 图像原始数据
     * @param face: 人脸位置
     * @return: PointWithMask[] 人脸特征点数组
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:49
     */
    public PointWithMask[] mark(SeetaImageData image, SeetaRect face) {
        SeetafaceValidated.validate(isClose, image, face);
        return FaceLandmarkerNative.mark(NATIVE_ID, image, face);
    }

    /**
     *释放资源

     * @return: void
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/20 10:50
     */
    @Override
    public void close() {
        SeetafaceValidated.validate(isClose);
        FaceLandmarkerNative.close(NATIVE_ID);
        isClose = true;
    }

}
