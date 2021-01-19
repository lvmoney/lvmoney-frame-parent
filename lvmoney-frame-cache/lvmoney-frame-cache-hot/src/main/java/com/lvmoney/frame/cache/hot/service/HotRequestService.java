package com.lvmoney.frame.cache.hot.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.cache.redis.service
 * 版本信息: 版本1.0
 * 日期:2020/5/18
 * Copyright XXXXXX科技有限公司
 */



import com.lvmoney.frame.cache.hot.ro.HotRequestRo;

import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/18 16:49
 */
public interface HotRequestService {
    /**
     * 保存热点请求统计
     *
     * @param hotRequestRo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/18 16:49
     */
    void save(HotRequestRo hotRequestRo);

    /**
     * 获得热点统计数据
     *
     * @param url:
     * @param reqVo:
     * @throws
     * @return: com.lvmoney.frame.cache.lock.ro.HotRequestRo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/19 11:05
     */
    HotRequestRo getHotRequestRo(String url, Map<String, String> reqVo);

    /**
     * 更新请求数据
     * 1：计算在一分钟内访问次数是否达到1万次
     * 2：每访问一次就把访问次数加1
     * 3：如果一分钟内访问次数未达到1万次，那么重新开始计算次数，同时更新时间
     * 4：正常情况下该接口的数据很少变化，没请求一次均会更新数据到redis
     *
     * @param hotRequestRo:
     * @param threshold:    阀值
     * @param section:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/19 9:59
     */
    void update(HotRequestRo hotRequestRo, long threshold, int section);


}
