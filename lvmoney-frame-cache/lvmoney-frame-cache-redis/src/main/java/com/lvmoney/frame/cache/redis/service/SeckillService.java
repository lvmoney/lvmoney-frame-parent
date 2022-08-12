package com.lvmoney.frame.cache.redis.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.cache.lock.service
 * 版本信息: 版本1.0
 * 日期:2020/5/3
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.cache.redis.vo.SeckillProductVo;
import com.lvmoney.frame.cache.redis.vo.SeckillVo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/3 13:17
 */
public interface SeckillService {
    /**
     * 通过lua脚本实现的秒杀扣减商品数量
     *
     * @param seckillVo:
     * @throws
     * @return: java.lang.Long
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/3 13:26
     */
    Long reduce(SeckillVo seckillVo);

    /**
     * 通过lua脚本实现的秒杀增加商品数量
     *
     * @param seckillVo:
     * @throws
     * @return: java.lang.Long
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/3 13:26
     */
    Long add(SeckillVo seckillVo);

    /**
     * 保存秒杀商品数据
     *
     * @param seckillProductVo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/3 13:47
     */
    void save(SeckillProductVo seckillProductVo);
}
