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
import com.lvmoney.frame.ai.seetaface.jni.server.FaceRecognizer;
import com.lvmoney.frame.ai.seetaface.jni.util.SeetafaceUtil;
import com.lvmoney.frame.ai.seetaface.jni.vo.*;
import com.lvmoney.frame.base.core.constant.BaseConstant;

import static com.lvmoney.frame.ai.seetaface.common.constant.SeetafaceCommonConstant.*;

/**
 * @describe：人脸识别
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2019/7/28 16:50
 */
public class FaceRecognizerTest extends SeetafaceBase {

    private static FaceRecognizer recognizer;
    private static FaceDetector detector;
    private static FaceLandmarker marker;

    public static void main(String[] args) {
        SeetafaceProp instance = SeetafaceProp.getInstance();
        String modelPath = instance.getSeetafaceModelPath();

        String fileName = "D:/face/1.jpg";
        String fileName2 = "D:/face/1.jpg";
        // 初始化人脸识别器
        recognizer = new FaceRecognizer(modelPath + BaseConstant.FILE_SEPARATOR + CSTA_FACE_RECOGNIZER);

        detector = new FaceDetector(modelPath + BaseConstant.FILE_SEPARATOR + CSTA_FACE_DETECTOR);
        marker = new FaceLandmarker(modelPath + BaseConstant.FILE_SEPARATOR + CSTA_LANDMARKER_PTS5);

        float[] features1 = extract(fileName);
        float[] features2 = extract(fileName2);

        if (features1 != null && features2 != null) {
            float calculateSimilarity = recognizer.calculateSimilarity(features1, features2);
            System.out.printf("相似度:%f\n", calculateSimilarity);
        }
    }

    /**
     * 获取特征数组
     * @param fileName:
     * @return: float[]
     * @throws
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/23 12:59
     */
    private static float[] extract(String fileName) {
        SeetaImageData image = SeetafaceUtil.toSeetaImageData(fileName);
        SeetaFaceInfoArray infos = detector.detect(image);
        for (SeetaFaceInfo info : infos.data) {
            PointWithMask[] marks = marker.mark(image, info.pos);
            SeetaPointF[] points = new SeetaPointF[marks.length];
            for (int n = 0; n < marks.length; n++) {
                points[n] = marks[n].point;
            }
            return recognizer.extract(image, points);
        }
        return null;
    }

}
