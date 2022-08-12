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
import com.lvmoney.frame.ai.seetaface.jni.util.SeetafaceUtil;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaFaceInfo;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaFaceInfoArray;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaImageData;
import com.lvmoney.frame.ai.seetaface.jni.vo.SeetaRect;
import com.lvmoney.frame.base.core.constant.BaseConstant;

import java.awt.image.BufferedImage;

import static com.lvmoney.frame.ai.seetaface.common.constant.SeetafaceCommonConstant.CSTA_FACE_DETECTOR;

/**
 * @describe：人脸检测
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2019/7/28 16:50
 */
public class FaceDetectorTest extends SeetafaceBase {

    public static void main(String[] args) {
        String imagePath = "D:/face/88.jpeg";
        SeetafaceProp instance = SeetafaceProp.getInstance();
        String modelPath = instance.getSeetafaceModelPath();
        FaceDetector detector = new FaceDetector(modelPath + BaseConstant.FILE_SEPARATOR + CSTA_FACE_DETECTOR);
        long start = System.currentTimeMillis();
        BufferedImage image = SeetafaceUtil.toBufferedImage(imagePath);
        image = SeetafaceUtil.resize(image, 480, 320);
        SeetaImageData imageData = SeetafaceUtil.toSeetaImageData(image);
        SeetaFaceInfoArray infos = detector.detect(imageData);
        System.out.println("人脸检测用时:" + (System.currentTimeMillis() - start));
        System.out.printf("人脸数: %s\n", infos.size);
        int i = 1;
        for (SeetaFaceInfo info : infos.data) {
            System.out.printf("第%s张人脸置信分数: %s\n", i, info.score);
            SeetaRect rect = info.pos;
            System.out.printf("第%s张人脸 x: %s, y: %s, width: %s, height: %s\n", i++, rect.x, rect.y, rect.width, rect.height);
            image = SeetafaceUtil.writeRect(image, rect);
        }
        SeetafaceUtil.show("人脸检测", image);

        detector.close();

    }

}
