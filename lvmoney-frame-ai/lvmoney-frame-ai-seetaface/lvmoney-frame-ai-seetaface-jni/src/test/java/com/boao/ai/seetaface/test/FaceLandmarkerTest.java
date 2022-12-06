package com.lvmoney.ai.seetaface.test;/**
 * 描述:
 * 包名:com.lvmoney.frame.member.info
 * 版本信息: 版本1.0
 * 日期:2020/1/20
 * Copyright XXXXXX科技有限公司
 */

import com.lvmoney.frame.ai.seetaface.jni.config.SeetafaceBase;
import com.lvmoney.frame.ai.seetaface.jni.config.SeetafaceProp;
import com.lvmoney.frame.ai.seetaface.jni.server.FaceDetector;
import com.lvmoney.frame.ai.seetaface.jni.server.FaceLandmarker;
import com.lvmoney.frame.ai.seetaface.jni.util.SeetafaceUtil;
import com.lvmoney.frame.ai.seetaface.jni.vo.*;
import com.lvmoney.frame.base.core.constant.BaseConstant;

import static com.lvmoney.frame.ai.seetaface.common.constant.SeetafaceCommonConstant.CSTA_FACE_DETECTOR;
import static com.lvmoney.frame.ai.seetaface.common.constant.SeetafaceCommonConstant.CSTA_LANDMARKER_PTS5;

/**
 * @describe：人脸关键点 主要展示FaceLandmarker的mark方法, 该方法中需要传入已经识别的人脸区域, 该信息可通过FaceDetector获取
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2019/7/28 16:50
 */
public class FaceLandmarkerTest extends SeetafaceBase {


    public static void main(String[] args) {
        String imagePath = "D:/face/88.jpeg";
        SeetafaceProp instance = SeetafaceProp.getInstance();
        String modelPath = instance.getSeetafaceModelPath();
        // ---------------------FaceLandmarker---------------------
        FaceLandmarker marker = new FaceLandmarker(modelPath + BaseConstant.FILE_SEPARATOR + CSTA_LANDMARKER_PTS5);
        SeetaImageData image = SeetafaceUtil.toSeetaImageData(imagePath);
        // ---------------------FaceLandmarker---------------------

        FaceDetector detector = new FaceDetector(modelPath + BaseConstant.FILE_SEPARATOR + CSTA_FACE_DETECTOR);
        SeetaFaceInfoArray infos = detector.detect(SeetafaceUtil.toSeetaImageData(imagePath));
        System.out.printf("检测到%s个人脸\n", infos.size);
        int i = 0;
        for (SeetaFaceInfo info : infos.data) {
            SeetaRect face = info.pos;

            // ---------------------FaceLandmarker---------------------
            PointWithMask[] marks = marker.mark(image, face);
            // ---------------------FaceLandmarker---------------------

            System.out.printf("---第{}张人脸的关键点为---\n", ++i);
            int n = 0;
            for (PointWithMask mask : marks) {
                System.out.printf("第%s个关键点 是否被遮挡: %s, x: %s, y: %s\n", ++n, mask.mask, mask.point.x, mask.point.y);
            }
        }

        marker.close();
        detector.close();
    }

}
