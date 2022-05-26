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
import com.lvmoney.frame.ai.seetaface.jni.server.GenderPredictor;
import com.lvmoney.frame.ai.seetaface.jni.util.SeetafaceUtil;
import com.lvmoney.frame.ai.seetaface.jni.vo.*;
import com.lvmoney.frame.base.core.constant.BaseConstant;

import static com.lvmoney.frame.ai.seetaface.common.constant.SeetafaceCommonConstant.*;


/**
 * @describe：性别判断
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2019/7/28 16:50
 */
public class GenderPredictorTest extends SeetafaceBase {

    public static void main(String[] args) {
        String imagePath = "D:/face/88.jpeg";
        SeetafaceProp instance = SeetafaceProp.getInstance();
        String modelPath = instance.getSeetafaceModelPath();
        FaceDetector detector = new FaceDetector(modelPath + BaseConstant.FILE_SEPARATOR + CSTA_FACE_DETECTOR);
        GenderPredictor genderPredictor = new GenderPredictor(modelPath + BaseConstant.FILE_SEPARATOR + CSTA_GENDER_PREDICTOR);
        FaceLandmarker marker = new FaceLandmarker(modelPath + BaseConstant.FILE_SEPARATOR + CSTA_LANDMARKER_PTS5);
        SeetaImageData image = SeetafaceUtil.toSeetaImageData(imagePath);
        SeetaFaceInfoArray infos = detector.detect(image);
        System.out.printf("检测到%s个人脸\n", infos.size);
        int i = 1;
        for (SeetaFaceInfo info : infos.data) {
            SeetaRect rect = info.pos;
            PointWithMask[] marks = marker.mark(image, rect);
            SeetaPointF[] points = new SeetaPointF[marks.length];
            for (int n = 0; n < marks.length; n++) {
                points[n] = marks[n].point;
            }
            GenderPredictor.Gender gender = genderPredictor.predictGenderWithCrop(image, points);
            System.out.printf("第%s张脸的性别为:%s\n", i++, gender.toString());
        }
        marker.close();
        genderPredictor.close();
        detector.close();
    }

}
