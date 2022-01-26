package com.lvmoney.frame.sync.uniformity.unique.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.sync.uniformity.service
 * 版本信息: 版本1.0
 * 日期:2021/12/30
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.sync.uniformity.unique.dto.GetBitmapFromRedisDto;
import com.lvmoney.frame.sync.uniformity.unique.dto.UpdateBitmapDto;
import com.lvmoney.frame.sync.uniformity.unique.ro.BitmapRo;
import org.roaringbitmap.RoaringBitmap;


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/12/30 14:43
 */
public interface BitmapRedisService {

    /**
     * 更新缓存的 Bitmap
     *
     * @param updateBitmapDto:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/12/30 14:47
     */
    void updateBitmap(UpdateBitmapDto updateBitmapDto);

    /**
     * bitmap保存到redis
     *
     * @param bitmapRo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/12/30 14:53
     */
    void bitmap2Redis(BitmapRo bitmapRo);

    /**
     * 从redis中获得 bitmap
     *
     * @param getBitmapFromRedisDto:
     * @throws
     * @return: org.roaringbitmap.RoaringBitmap
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/12/30 15:23
     */
    RoaringBitmap getByRedisKey(GetBitmapFromRedisDto getBitmapFromRedisDto);



}
