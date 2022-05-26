package com.lvmoney.frame.ai.seetaface.base.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.seetaface.base.service.impl
 * 版本信息: 版本1.0
 * 日期:2022/2/9
 * Copyright XXXXXX科技有限公司
 */


import cn.hutool.core.convert.Convert;
import com.lvmoney.frame.ai.seetaface.base.bo.*;
import com.lvmoney.frame.ai.seetaface.base.dto.ComparisonHistoryDto;
import com.lvmoney.frame.ai.seetaface.base.dto.*;
import com.lvmoney.frame.ai.seetaface.base.entity.ComparisonHistory;
import com.lvmoney.frame.ai.seetaface.base.enums.ComparisonClassify;
import com.lvmoney.frame.ai.seetaface.base.service.ComparisonHistoryService;
import com.lvmoney.frame.ai.seetaface.base.service.ComparisonResourceService;
import com.lvmoney.frame.ai.seetaface.base.service.FaceService;
import com.lvmoney.frame.ai.seetaface.common.constant.SeetafaceCommonConstant;
import com.lvmoney.frame.ai.seetaface.common.exception.SeetafaceException;
import com.lvmoney.frame.ai.seetaface.common.vo.ResourceStr;
import com.lvmoney.frame.authentication.util.util.JwtUtil;
import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.base.core.util.PrivacyUtil;
import com.lvmoney.frame.base.core.util.SpringBeanUtil;
import com.lvmoney.frame.core.vo.UserVo;
import com.lvmoney.frame.oss.common.vo.FileBaseOutVo;
import com.lvmoney.frame.oss.common.vo.FileBaseVo;
import com.lvmoney.frame.oss.common.vo.FileByteOutVo;
import com.lvmoney.frame.oss.common.vo.FileQueryVo;
import com.lvmoney.frame.oss.minio.dto.MinioDto;
import com.lvmoney.frame.oss.minio.service.BaseMinioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/2/9 18:38
 */
@Service
public class FaceServiceImpl implements FaceService {


    /**
     * 隐私信息通用加密密码
     */
    @Value("${privacy.unify.password:chChdriver2021!}")
    private String privacyPassword;

    private static final Logger LOGGER = LoggerFactory.getLogger(FaceServiceImpl.class);

    @Autowired
    BaseMinioService ossService;
    @Autowired
    ComparisonHistoryService comparisonHistoryService;
    @Autowired
    ComparisonResourceService comparisonResourceService;

    @Override
    public CompareBo compare(CompareDto compareDto) {
        CompareBo compareBo = new CompareBo();
        BufferedImage res = null;
        BufferedImage target = null;
        try {
            MultipartFile resFile = compareDto.getRes().getFile();
            MultipartFile targetFile = compareDto.getTarget().getFile();
            res = ImageIO.read(new ByteArrayInputStream(resFile.getBytes()));
            target = ImageIO.read(new ByteArrayInputStream(targetFile.getBytes()));
            float compare = compare(res, target);
            compareBo.setScore(compare);
            if (compareDto.getRecord()) {
                Upload2MinioDto upload2MinioDtoRes = Convert.convert(Upload2MinioDto.class, compareDto.getRes());
                FileBaseOutVo fileBaseOutVoRes = upload2Minio(upload2MinioDtoRes);
                Upload2MinioDto upload2MinioDtoTarget = Convert.convert(Upload2MinioDto.class, compareDto.getTarget());
                FileBaseOutVo fileBaseOutVoTarget = upload2Minio(upload2MinioDtoTarget);
                ComparisonHistoryDto comparisonHistoryDto = new ComparisonHistoryDto();
                ResourceStr resourceStrRes = Convert.convert(ResourceStr.class, fileBaseOutVoRes);
                comparisonHistoryDto.setRes(JsonUtil.t2JsonString(resourceStrRes));
                ResourceStr resourceStrTarget = Convert.convert(ResourceStr.class, fileBaseOutVoTarget);
                comparisonHistoryDto.setTarget(JsonUtil.t2JsonString(resourceStrTarget));
                comparisonHistoryDto.setScore(BigDecimal.valueOf(compare));
                comparisonHistoryDto.setClassify(ComparisonClassify.SINGLE_V_SINGLE.getValue());
                this.save2Mysql(comparisonHistoryDto);
            }
        } catch (IOException e) {
            LOGGER.error("两张图片相识度对比报错:{}", e);
            throw new BusinessException(SeetafaceException.Proxy.COMPARE_ERROR);
        } finally {
            if (res != null) {
                res.getGraphics().dispose();
            }
            if (target != null) {
                target.getGraphics().dispose();
            }
        }
        return compareBo;
    }

    @Override
    public CompareResBo compare(CompareResDto compareResDto) {
        CompareResBo compareResBo = new CompareResBo();
        BufferedImage res = null;
        BufferedImage target = null;
        try {
            FileQueryVo fileQueryVo = Convert.convert(FileQueryVo.class, compareResDto.getRes());
            FileByteOutVo byFileIdRes = ossService.getByFileId(fileQueryVo);
            MultipartFile targetFile = compareResDto.getTarget().getFile();
            res = ImageIO.read(new ByteArrayInputStream(byFileIdRes.getFileByte()));
            target = ImageIO.read(new ByteArrayInputStream(targetFile.getBytes()));
            float compare = compare(res, target);
            compareResBo.setScore(compare);
            if (compareResDto.getRecord()) {
                Upload2MinioDto upload2MinioDtoTarget = Convert.convert(Upload2MinioDto.class, compareResDto.getTarget());
                FileBaseOutVo fileBaseOutVoTarget = upload2Minio(upload2MinioDtoTarget);
                ComparisonHistoryDto comparisonHistoryDto = new ComparisonHistoryDto();
                ResourceStr resourceStrRes = Convert.convert(ResourceStr.class, byFileIdRes);
                comparisonHistoryDto.setRes(JsonUtil.t2JsonString(resourceStrRes));
                ResourceStr resourceStrTarget = Convert.convert(ResourceStr.class, fileBaseOutVoTarget);
                comparisonHistoryDto.setTarget(JsonUtil.t2JsonString(resourceStrTarget));
                comparisonHistoryDto.setScore(BigDecimal.valueOf(compare));
                comparisonHistoryDto.setClassify(ComparisonClassify.SINGLE_V_SINGLE.getValue());
                this.save2Mysql(comparisonHistoryDto);
            }
        } catch (IOException e) {
            LOGGER.error("和minio中图片相识度对比报错:{}", e);
            throw new BusinessException(SeetafaceException.Proxy.COMPARE_RES_ERROR);
        } finally {
            if (res != null) {
                res.getGraphics().dispose();
            }
            if (target != null) {
                target.getGraphics().dispose();
            }
        }
        return compareResBo;
    }

    @Override
    public CompareIdCardBo compare(CompareIdCardDto compareIdCardDto) {
        String idCardPrivacy = PrivacyUtil.privacyIdCard(compareIdCardDto.getIdCard());
        String idCard = PrivacyUtil.privacyEncryption(compareIdCardDto.getIdCard(), privacyPassword);
        GetByIdCardDto getByIdCardDto = new GetByIdCardDto(idCard, idCardPrivacy);
        ResourceStr byIdCard = comparisonResourceService.getByIdCard(getByIdCardDto);
        BufferedImage res = null;
        BufferedImage target = null;
        CompareIdCardBo compareIdCardBo = new CompareIdCardBo();
        try {
            FileQueryVo fileQueryVo = new FileQueryVo();
            fileQueryVo.setFileId(byIdCard.getFileId());
            fileQueryVo.setName(byIdCard.getFileName());
            FileByteOutVo byFileIdRes = ossService.getByFileId(fileQueryVo);
            MultipartFile targetFile = compareIdCardDto.getTarget().getFile();
            res = ImageIO.read(new ByteArrayInputStream(byFileIdRes.getFileByte()));
            target = ImageIO.read(new ByteArrayInputStream(targetFile.getBytes()));
            float compare = compare(res, target);
            compareIdCardBo.setScore(compare);
            if (compareIdCardDto.getRecord()) {
                Upload2MinioDto upload2MinioDtoTarget = Convert.convert(Upload2MinioDto.class, compareIdCardDto.getTarget());
                FileBaseOutVo fileBaseOutVoTarget = upload2Minio(upload2MinioDtoTarget);
                ComparisonHistoryDto comparisonHistoryDto = new ComparisonHistoryDto();
                ResourceStr resourceStrRes = Convert.convert(ResourceStr.class, byFileIdRes);
                comparisonHistoryDto.setRes(JsonUtil.t2JsonString(resourceStrRes));
                ResourceStr resourceStrTarget = Convert.convert(ResourceStr.class, fileBaseOutVoTarget);
                comparisonHistoryDto.setTarget(JsonUtil.t2JsonString(resourceStrTarget));
                comparisonHistoryDto.setScore(BigDecimal.valueOf(compare));
                comparisonHistoryDto.setClassify(ComparisonClassify.SINGLE_V_SINGLE.getValue());
                this.save2Mysql(comparisonHistoryDto);
            }
        } catch (IOException e) {
            LOGGER.error("通过身份证号获得图片进行图片相识度对比报错:{}", e);
            throw new BusinessException(SeetafaceException.Proxy.COMPARE_ID_CARD_ERROR);
        } finally {
            if (res != null) {
                res.getGraphics().dispose();
            }
            if (target != null) {
                target.getGraphics().dispose();
            }
        }
        return compareIdCardBo;
    }

    @Override
    public FaceCheckBo isFace(FaceCheckDto faceCheckDto) {
        SeetaRect[] detect = new SeetaRect[0];
        if (detect.length > 0) {
            LOGGER.error("识别出的人脸超过有多个:{}", detect);
            throw new BusinessException(SeetafaceException.Proxy.FACE_NOT_ONLY);
        }
//        try {
//            detect = FaceHelper.detect(faceCheckDto.getRes().getFile().getBytes());
//        } catch (IOException e) {
//            LOGGER.error("获得出入文件的二进制流报错:{}", e);
//        }
        FaceCheckBo result = Convert.convert(FaceCheckBo.class, detect[0]);
        if (faceCheckDto.getRecord()) {
            Upload2MinioDto upload2MinioDtoRes = Convert.convert(Upload2MinioDto.class, faceCheckDto.getRes());
            FileBaseOutVo fileBaseOutVoRes = upload2Minio(upload2MinioDtoRes);
            ComparisonHistoryDto comparisonHistoryDto = new ComparisonHistoryDto();
            ResourceStr resourceStrRes = Convert.convert(ResourceStr.class, fileBaseOutVoRes);
            comparisonHistoryDto.setRes(JsonUtil.t2JsonString(resourceStrRes));
            comparisonHistoryDto.setScore(BigDecimal.valueOf(result.getScore()));
            comparisonHistoryDto.setClassify(ComparisonClassify.IS_FACE.getValue());
            comparisonHistoryDto.setResult(JsonUtil.t2JsonString(detect));
            this.save2Mysql(comparisonHistoryDto);
        }
        return result;
    }

    @Override
    public FaceCheckResBo isFace(FaceCheckResDto faceCheckResDto) {
        FileQueryVo fileQueryVo = Convert.convert(FileQueryVo.class, faceCheckResDto.getRes());
        FileByteOutVo byFileIdRes = ossService.getByFileId(fileQueryVo);
//      SeetaRect[] detect = FaceHelper.detect(byFileIdRes.getFileByte());
//        if (detect.length > 0) {
//            LOGGER.error("识别出的人脸超过有多个:{}", detect);
//            throw new BusinessException(SeetafaceException.Proxy.FACE_NOT_ONLY);
//        }
        //      FaceCheckResBo result = Convert.convert(FaceCheckResBo.class, detect[0]);
        FaceCheckResBo result = new FaceCheckResBo();
        if (faceCheckResDto.getRecord()) {
            ComparisonHistoryDto comparisonHistoryDto = new ComparisonHistoryDto();
            ResourceStr resourceStrRes = Convert.convert(ResourceStr.class, byFileIdRes);
            comparisonHistoryDto.setRes(JsonUtil.t2JsonString(resourceStrRes));
            comparisonHistoryDto.setRes(JsonUtil.t2JsonString(resourceStrRes));
            comparisonHistoryDto.setScore(BigDecimal.valueOf(result.getScore()));
            comparisonHistoryDto.setClassify(ComparisonClassify.IS_FACE.getValue());
//            comparisonHistoryDto.setResult(JsonUtil.t2JsonString(detect));
            this.save2Mysql(comparisonHistoryDto);
        }
        return result;
    }


    /**
     * 人脸对比，并将数据同步到mysql
     *
     * @param res:
     * @param target:
     * @throws
     * @return: float
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/10 11:25
     */
    private float compare(BufferedImage res, BufferedImage target) {
        return 0f;
//        return FaceHelper.compare(res, target);
    }


    /**
     * 保存文件到 minio
     *
     * @param upload2MinioDto:
     * @throws
     * @return: com.lvmoney.frame.oss.common.vo.FileBaseOutVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/10 17:15
     */
    private FileBaseOutVo upload2Minio(Upload2MinioDto upload2MinioDto) {
        FileBaseVo fileBaseVo = Convert.convert(FileBaseVo.class, upload2MinioDto);
        MinioDto minioDto = new MinioDto();
        minioDto.setBucketName(SeetafaceCommonConstant.DEFAULT_BUCKET_NAME);
        if (ossService.bucketIsExist(SeetafaceCommonConstant.DEFAULT_BUCKET_NAME)) {
            ossService.createBucket(SeetafaceCommonConstant.DEFAULT_BUCKET_NAME);
        }
        fileBaseVo.setData(minioDto);
        return ossService.save(fileBaseVo);
    }

    /**
     * 保存信息到数据库
     *
     * @throws
     * @return: java.lang.Boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/10 17:16
     */
    private Boolean save2Mysql(ComparisonHistoryDto comparisonHistoryDto) {
        ComparisonHistory comparisonHistory = Convert.convert(ComparisonHistory.class, comparisonHistoryDto);
        String token = SpringBeanUtil.getToken();
        UserVo userVo = JwtUtil.getUserVo(token);
        Long userId = Long.parseLong(userVo.getSysId());
        String username = userVo.getUsername();
        comparisonHistory.setClientId(userId);
        comparisonHistory.setCreateId(userId);
        comparisonHistory.setCreateName(userVo.getUsername());
        comparisonHistory.setUpdateId(userId);
        comparisonHistory.setUpdateName(username);
        return comparisonHistoryService.save(comparisonHistory);
    }
}
