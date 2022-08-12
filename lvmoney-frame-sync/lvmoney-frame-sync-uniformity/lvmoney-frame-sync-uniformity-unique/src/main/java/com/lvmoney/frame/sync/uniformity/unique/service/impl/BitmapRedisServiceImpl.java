package com.lvmoney.frame.sync.uniformity.unique.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.sync.uniformity.service.impl
 * 版本信息: 版本1.0
 * 日期:2021/12/30
 * Copyright XXXXXX科技有限公司
 */


import cn.hutool.core.convert.Convert;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.cache.common.annotations.CacheImpl;
import com.lvmoney.frame.cache.common.constant.CacheConstant;
import com.lvmoney.frame.cache.common.service.CacheCommonService;
import com.lvmoney.frame.core.util.BitmapUtil;
import com.lvmoney.frame.sync.uniformity.unique.common.UniformityUniqueConstant;
import com.lvmoney.frame.sync.uniformity.unique.dto.GetBitmapFromRedisDto;
import com.lvmoney.frame.sync.uniformity.unique.dto.UpdateBitmapDto;
import com.lvmoney.frame.sync.uniformity.unique.enums.UniformityTypeEnum;
import com.lvmoney.frame.sync.uniformity.unique.ro.BitmapRo;
import com.lvmoney.frame.sync.uniformity.unique.service.BitmapRedisService;
import org.apache.commons.lang3.ObjectUtils;
import org.roaringbitmap.RoaringBitmap;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.lvmoney.frame.sync.uniformity.unique.common.UniformityUniqueConstant.BITMAP_EXPIRED;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/12/30 14:47
 */
@Service
public class BitmapRedisServiceImpl implements BitmapRedisService {
    @CacheImpl(CacheConstant.CACHE_REDIS)
    CacheCommonService cacheCommonService;

    @Override
    public void updateBitmap(UpdateBitmapDto updateBitmapDto) {
        List<Long> bitmaps = updateBitmapDto.getUniqueIds();
        if (ObjectUtils.isEmpty(bitmaps)) {
            return;
        }
        GetBitmapFromRedisDto getBitmapFromRedisDto = Convert.convert(GetBitmapFromRedisDto.class, updateBitmapDto);
        // 从redis获取bitmap
        RoaringBitmap bitmap = this.getByRedisKey(getBitmapFromRedisDto);

        // 设置bitmap
        bitmaps.forEach(o -> bitmap.add(o.intValue()));

        // 保存bitmap
        String source = BitmapUtil.roaringBitmap2Base64String(bitmap);
        BitmapRo bitmapRo = Convert.convert(BitmapRo.class, updateBitmapDto);
        bitmapRo.setBitmap(source);
        this.bitmap2Redis(bitmapRo);
    }

    @Override
    public void bitmap2Redis(BitmapRo bitmapRo) {
        String redisKey = getBitmapRedisKey(bitmapRo.getClientId(), bitmapRo.getClassify(), bitmapRo.getSynDate(), bitmapRo.getUniformityTypeEnum(), bitmapRo.getDbLogicTable(), bitmapRo.getTableLogicTable());
        cacheCommonService.setString(redisKey, bitmapRo.getBitmap(), BITMAP_EXPIRED);
    }

    @Override
    public RoaringBitmap getByRedisKey(GetBitmapFromRedisDto getBitmapFromRedisDto) {
        String redisKey = getBitmapRedisKey(getBitmapFromRedisDto.getClientId(), getBitmapFromRedisDto.getClassify(), getBitmapFromRedisDto.getSynDate(), getBitmapFromRedisDto.getUniformityTypeEnum(), getBitmapFromRedisDto.getDbLogicTable(), getBitmapFromRedisDto.getTableLogicTable());
        // 从redis获取bitmap
        RoaringBitmap bitmap;
        Object obj = cacheCommonService.getByKey(redisKey);
        if (obj != null) {
            bitmap = BitmapUtil.base64String2RoaringBitmap(obj.toString());
        } else {
            bitmap = new RoaringBitmap();
        }
        return bitmap;
    }

    /**
     * 获得 bitmap存储的redis key
     *
     * @param clientId:
     * @param classify:
     * @param synDate:
     * @param uniformityTypeEnum:
     * @param tableLogicTable:
     * @param dbLogicTable:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/1/7 15:33
     */
    private String getBitmapRedisKey(String clientId, String classify, String synDate, UniformityTypeEnum uniformityTypeEnum, String tableLogicTable, String dbLogicTable) {
        return UniformityUniqueConstant.DATASYNC_PREFIX + BaseConstant.CONNECTOR_UNDERLINE
                + dbLogicTable + BaseConstant.CONNECTOR_UNDERLINE
                + tableLogicTable + BaseConstant.CONNECTOR_UNDERLINE
                + classify + BaseConstant.CONNECTOR_UNDERLINE
                + uniformityTypeEnum.getType() + BaseConstant.CONNECTOR_UNDERLINE
                + synDate + BaseConstant.CONNECTOR_UNDERLINE
                + clientId;

    }
}
