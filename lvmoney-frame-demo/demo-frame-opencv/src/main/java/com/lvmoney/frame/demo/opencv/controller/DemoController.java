//package com.lvmoney.frame.demo.opencv.controller;/**
// * 描述:
// * 包名:com.lvmoney.demo.provider.function
// * 版本信息: 版本1.0
// * 日期:2020/3/8
// * Copyright XXXXXX科技有限公司
// */
//
//
//import com.lvmoney.frame.base.core.api.ApiResult;
//import com.lvmoney.frame.base.core.util.JsonUtil;
//import com.lvmoney.frame.core.util.FileUtil;
////import com.lvmoney.frame.opencv.util.OpenCvUtils;
//import com.lvmoney.frame.opencv.util.vo.FaceCheckVo;
//import com.lvmoney.frame.opencv.util.vo.FaceCutVo;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.File;
//import java.util.List;
//
///**
// * @describe：
// * @author: lvmoney /XXXXXX科技有限公司
// * @version:v1.0 2020/3/8 17:40
// */
//@RestController
//public class DemoController {
//    @GetMapping(value = "frame/opencv/test")
//    public ApiResult<FaceCheckVo> testJwt(@RequestParam("name") String name) {
//        FaceCheckVo faceCheckVo = OpenCvUtils.faceCheck(FileUtil.file2byte("D:\\12.jpg"));
//        FaceCutVo faceCutVo = OpenCvUtils.faceCut(FileUtil.file2byte("D:\\12.jpg"), "D:\\pictures");
//        System.out.println(JsonUtil.t2JsonString(faceCutVo));
//
//        return ApiResult.success(faceCheckVo);
//    }
//}
