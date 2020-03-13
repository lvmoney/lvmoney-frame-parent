package com.zhy.frame.cache.lock.service;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/9
 * Copyright 四川******科技有限公司
 */


import com.zhy.frame.cache.lock.vo.req.SectionInitVo;
import com.zhy.frame.cache.lock.vo.req.SectionReqVo;
import com.zhy.frame.cache.lock.vo.resp.SectionRespVo;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public interface SequenceService {
    /**
     * 获得连续数据的开始和结束。这里需要锁定的值是Long。size不足在前面自动补零
     *
     * @param sectionVo: 请求实体
     * @throws
     * @return: com.zhy.lock.vo.resp.SectionRespVo
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 21:02
     */
    SectionRespVo getSectionData(SectionReqVo sectionVo);

    /**
     * 初始化分布式锁最新值
     *
     * @param sectionInitVo: 实体
     * @throws
     * @return: boolean
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 21:03
     */
    boolean initSectionData(SectionInitVo sectionInitVo);
}
