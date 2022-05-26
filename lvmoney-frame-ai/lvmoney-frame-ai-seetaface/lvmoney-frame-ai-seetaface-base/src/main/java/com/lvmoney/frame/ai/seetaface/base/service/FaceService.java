package com.lvmoney.frame.ai.seetaface.base.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.seetaface.base.service
 * 版本信息: 版本1.0
 * 日期:2022/2/9
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.ai.seetaface.base.bo.*;
import com.lvmoney.frame.ai.seetaface.base.dto.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/2/9 18:37
 */
public interface FaceService {
    /**
     * 两张不同图片人脸对比
     *
     * @param compareDto:
     * @throws
     * @return: CompareBo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/10 11:11
     */
    CompareBo compare(CompareDto compareDto);

    /**
     * 和minio中某个图片进行对比
     *
     * @param compareResDto:
     * @throws
     * @return: CompareResBo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/11 11:27
     */
    CompareResBo compare(CompareResDto compareResDto);

    /**
     * 和身份证号已有的图片进行对比
     *
     * @param compareIdCardDto:
     * @throws
     * @return: CompareIdCardBo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/11 11:28
     */
    CompareIdCardBo compare(CompareIdCardDto compareIdCardDto);

    /**
     * 传入文件，判断是不是人脸
     *
     * @param faceCheckDto:
     * @throws
     * @return: FaceCheckBo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/16 16:08
     */
    FaceCheckBo isFace(FaceCheckDto faceCheckDto);

    /**
     * 获得minio文件，判断是不是人脸
     *
     * @param faceCheckResDto:
     * @throws
     * @return: FaceCheckBo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/16 16:08
     */
    FaceCheckResBo isFace(FaceCheckResDto faceCheckResDto);


}
