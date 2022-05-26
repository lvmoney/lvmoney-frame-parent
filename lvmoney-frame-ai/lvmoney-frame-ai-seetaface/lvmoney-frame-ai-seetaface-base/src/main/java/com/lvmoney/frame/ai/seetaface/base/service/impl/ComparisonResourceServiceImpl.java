package com.lvmoney.frame.ai.seetaface.base.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvmoney.frame.ai.seetaface.base.dao.ComparisonResourceDao;
import com.lvmoney.frame.ai.seetaface.base.dto.GetByIdCardDto;
import com.lvmoney.frame.ai.seetaface.base.entity.ComparisonResource;
import com.lvmoney.frame.ai.seetaface.base.service.ComparisonResourceService;
import com.lvmoney.frame.ai.seetaface.common.vo.ResourceStr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 人脸识别原图 服务实现类
 * </p>
 *
 * @author lvmoney
 * @since 2022-02-11
 */
@Service
public class ComparisonResourceServiceImpl extends ServiceImpl<ComparisonResourceDao, ComparisonResource> implements ComparisonResourceService {
    @Autowired
    ComparisonResourceDao comparisonResourceDao;

    @Override
    public ResourceStr getByIdCard(GetByIdCardDto getByIdCardDto) {
        ComparisonResource byIdCard = comparisonResourceDao.getByIdCard(getByIdCardDto.getIdCard(), getByIdCardDto.getIdCardPrivacy());
        return Convert.convert(ResourceStr.class, byIdCard);
    }
}
