package com.lvmoney.frame.ai.opencv.util;//package com.lvmoney.frame.opencv.util;/**
// * 描述:
// * 包名:com.lvmoney.frame.opencv.util
// * 版本信息: 版本1.0
// * 日期:2021/12/20
// * Copyright XXXXXX科技有限公司
// *
// * @describe：
// * @author: lvmoney/XXXXXX科技有限公司
// * @version:v1.0 2021/12/20 19:56
// */
//
//
//import com.lvmoney.frame.base.core.constant.BaseConstant;
//import com.lvmoney.frame.core.util.SnowflakeIdFactoryUtil;
//import com.lvmoney.frame.opencv.util.vo.FaceCheckVo;
//import com.lvmoney.frame.opencv.util.vo.FaceCutVo;
//import com.google.common.primitives.Bytes;
//import org.opencv.core.*;
//import org.opencv.imgcodecs.Imgcodecs;
//import org.opencv.imgproc.Imgproc;
//import org.opencv.objdetect.CascadeClassifier;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.io.*;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @describe：
// * @author: lvmoney/XXXXXX科技有限公司
// * @version:v1.0 2021/12/20 19:56
// */
//@Component
//public class OpenCvUtils implements CommandLineRunner {
//    private static final Logger LOGGER = LoggerFactory.getLogger(OpenCvUtils.class);
//    @Value("${opencv.lib.linuxxmlpath:/opt/opencv}")
//    private String linuxXmlPath;
//    @Value("${opencv.lib.windowxmlpath:E://}")
//    private String windowXmlPath;
//    /**
//     * byte 默认长度
//     */
//    private static final Integer DEFAULT_BYTE_LENGTH = 1024;
//    /**
//     * 人脸探测器对象
//     */
//    private static CascadeClassifier faceDetector;
//    /**
//     * 图片后缀
//     */
//    private static String DEFAULT_IMAGE_SUFFIX = ".jpg";
//
//    /**
//     * 图片默认签字
//     */
//    private static String DEFAULT_IMAGE_PREFIX = "res";
//
//    /**
//     * 判断是否是Windows系统
//     */
//    private static final boolean IS_WINDOWS = System.getProperty("os.name").toLowerCase().contains("win");
//
//    /**
//     * 监测在线图片是否合法，有几张脸
//     *
//     * @param pictureUrl:
//     * @throws
//     * @return: com.lvmoney.frame.opencv.util.vo.FaceCheckVo
//     * @author: lvmoney /XXXXXX科技有限公司
//     * @date: 2021/12/20 20:13
//     */
//    public static FaceCheckVo checkFace(String pictureUrl) {
//        try {
//            //在线图片
//            URL url = new URL(pictureUrl);
//            URLConnection uc = url.openConnection();
//            InputStream inputStream = uc.getInputStream();
//            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
//            byte[] buff = new byte[DEFAULT_BYTE_LENGTH];
//            int rc;
//            while ((rc = inputStream.read(buff, 0, DEFAULT_BYTE_LENGTH)) > 0) {
//                swapStream.write(buff, 0, rc);
//            }
//            byte[] urlBuff = swapStream.toByteArray();
//            List<Byte> bs = new ArrayList<>();
//            bs.addAll(Bytes.asList(urlBuff));
//            Mat image = Imgcodecs.imdecode(new MatOfByte(urlBuff), Imgcodecs.IMREAD_UNCHANGED);
//            if (image.empty()) {
//                LOGGER.error("image 内容不存在！");
//                return null;
//            }
//            // 3 特征匹配
//            MatOfRect face = new MatOfRect();
//            faceDetector.detectMultiScale(image, face);
//            // 4 匹配 Rect 矩阵 数组
//            Rect[] rects = face.toArray();
//            return new FaceCheckVo(rects.length);
//        } catch (MalformedURLException e) {
//            return null;
//        } catch (IOException e) {
//            return null;
//        }
//
//
//    }
//
//    public static FaceCheckVo faceCheck(byte[] img) {
//        Mat image = Imgcodecs.imdecode(new MatOfByte(img), Imgcodecs.IMREAD_UNCHANGED);
//        if (image.empty()) {
//            LOGGER.error("image 内容不存在！");
//            return null;
//        }
//        // 3 特征匹配
//        MatOfRect face = new MatOfRect();
//        faceDetector.detectMultiScale(image, face);
//        // 4 匹配 Rect 矩阵 数组
//        Rect[] rects = face.toArray();
//        return new FaceCheckVo(rects.length);
//    }
//
//    /**
//     * Callback used to run the bean.
//     *
//     * @param args: incoming main method arguments
//     * @throws
//     * @return: void
//     * @author: lvmoney /XXXXXX科技有限公司
//     * @date: 2021/12/20 20:03
//     */
//    @Override
//    public void run(String... args) {
//        String systemProperties = String.valueOf(System.getProperties());
//        LOGGER.info(systemProperties);
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        String path = "";
//        //如果是window系统取出路径开头的/
//        if (IS_WINDOWS) {
//            path = windowXmlPath;
//        } else {
//            path = linuxXmlPath;
//        }
//        //初始化人脸探测器
//        faceDetector = new CascadeClassifier(path);
//        LOGGER.info("==========初始化人脸探测器成功===========");
//    }
//
//
//    /**
//     * 裁剪人脸
//     *
//     * @param imagePath:
//     * @param outFile:
//     * @param posX:
//     * @param posY:
//     * @param width:
//     * @param height:
//     * @throws
//     * @return: void
//     * @author: lvmoney /XXXXXX科技有限公司
//     * @date: 2021/12/20 20:17
//     */
//    public static void faceCut(byte[] imagePath, String outFile, int posX, int posY, int width, int height) {
//        // 原始图像
//        Mat image = Imgcodecs.imdecode(new MatOfByte(imagePath), Imgcodecs.IMREAD_UNCHANGED);
//
//        // 截取的区域：参数,坐标X,坐标Y,截图宽度,截图长度
//        Rect rect = new Rect(posX, posY, width, height);
//        // 两句效果一样
//        Mat sub = image.submat(rect);
//        Mat mat = new Mat();
//        Size size = new Size(width, height);
//        // 将人脸进行截图并保存
//        Imgproc.resize(sub, mat, size);
//        Imgcodecs.imwrite(outFile, mat);
//    }
//
//    /**
//     * 裁剪文件流
//     *
//     * @param imagePath:
//     * @param outFile:
//     * @throws
//     * @return: java.util.List<java.lang.String>
//     * @author: lvmoney /XXXXXX科技有限公司
//     * @date: 2021/12/20 20:52
//     */
//    public static FaceCutVo faceCut(byte[] imagePath, String outFile) {
//        Mat image = Imgcodecs.imdecode(new MatOfByte(imagePath), Imgcodecs.IMREAD_UNCHANGED);
//        if (image.empty()) {
//            LOGGER.error("image 内容不存在！");
//            return null;
//        }
//        // 3 特征匹配
//        MatOfRect face = new MatOfRect();
//        faceDetector.detectMultiScale(image, face);
//        // 5 为每张识别到的人脸画一个圈
//        List<String> item = new ArrayList<>();
//        for (Rect rect : face.toArray()) {
//            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
//                    new Scalar(0, 255, 0), 3);
//
//            String file = outFile + BaseConstant.FILE_SEPARATOR + SnowflakeIdFactoryUtil.nextId() + DEFAULT_IMAGE_SUFFIX;
//            // 进行图片裁剪
//            faceCut(imagePath, file, rect.x, rect.y, rect.width, rect.height);
//            item.add(file);
//        }
//        String resFile = outFile + BaseConstant.FILE_SEPARATOR + DEFAULT_IMAGE_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + SnowflakeIdFactoryUtil.nextId() + DEFAULT_IMAGE_SUFFIX;
//        Imgcodecs.imwrite(resFile, image);
//        FaceCutVo faceCutVo = new FaceCutVo();
//        faceCutVo.setItem(item);
//        faceCutVo.setRes(resFile);
//        return faceCutVo;
//    }
//
//}