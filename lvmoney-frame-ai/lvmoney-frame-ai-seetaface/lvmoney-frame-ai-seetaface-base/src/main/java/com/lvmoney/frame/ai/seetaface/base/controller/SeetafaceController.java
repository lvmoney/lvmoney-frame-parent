package com.lvmoney.frame.ai.seetaface.base.controller;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.seetaface.base.controller
 * 版本信息: 版本1.0
 * 日期:2022/2/15
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.ai.seetaface.base.bo.CompareIdCardBo;
import com.lvmoney.frame.ai.seetaface.base.bo.FaceCheckBo;
import com.lvmoney.frame.ai.seetaface.base.bo.FaceCheckResBo;
import com.lvmoney.frame.ai.seetaface.base.dto.CompareIdCardDto;
import com.lvmoney.frame.ai.seetaface.base.dto.FaceCheckDto;
import com.lvmoney.frame.ai.seetaface.base.service.FaceService;
import com.lvmoney.frame.base.core.api.ApiResult;
import com.lvmoney.frame.oss.common.vo.FileBaseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/2/15 11:15
 */
@RestController
public class SeetafaceController {
    @Autowired
    FaceService faceService;

    @PostMapping("/compare")
    public ApiResult<CompareIdCardBo> compare(String idcard, MultipartFile file) {
        CompareIdCardDto compareIdCardDto = new CompareIdCardDto();
        compareIdCardDto.setIdCard(idcard);
        compareIdCardDto.setRecord(true);
        FileBaseVo target = new FileBaseVo();
        target.setFile(file);
        compareIdCardDto.setTarget(target);
        CompareIdCardBo compare = faceService.compare(compareIdCardDto);
        return ApiResult.success(compare);
    }

    @PostMapping("/isFace")
    public ApiResult<FaceCheckResBo> isFace(MultipartFile file) {
        FaceCheckDto faceCheckDto = new FaceCheckDto();
        faceCheckDto.setRecord(true);
        FileBaseVo target = new FileBaseVo();
        target.setFile(file);
        faceCheckDto.setRes(target);
        FaceCheckBo face = faceService.isFace(faceCheckDto);
        return ApiResult.success(face);
    }

}
