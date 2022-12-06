package com.lvmoney.frame.ai.seetaface.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lvmoney.frame.ai.seetaface.base.entity.ComparisonResource;
import com.lvmoney.frame.ai.seetaface.base.dto.GetByIdCardDto;
import com.lvmoney.frame.ai.seetaface.common.vo.ResourceStr;

/**
 * <p>
 * 人脸识别原图 服务类
 * </p>
 *
 * @author lvmoney
 * @since 2022-02-11
 */
public interface ComparisonResourceService extends IService<ComparisonResource> {
    /**
     * 通过身份号获得真实的图片信息
     *
     * @param getByIdCardDto:
     * @throws
     * @return: ResourceStr
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/11 17:28
     */
    ResourceStr getByIdCard(GetByIdCardDto getByIdCardDto);
}
