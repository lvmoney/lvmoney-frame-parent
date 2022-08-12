package com.lvmoney.frame.cache.lock.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/3/9
 * Copyright 四川******科技有限公司
 */


import com.lvmoney.frame.cache.lock.constant.LockConstant;
import com.lvmoney.frame.cache.lock.service.DistributedLockerService;
import com.lvmoney.frame.cache.lock.service.SequenceService;
import com.lvmoney.frame.cache.lock.vo.req.SectionInitVo;
import com.lvmoney.frame.cache.lock.vo.req.SectionReqVo;
import com.lvmoney.frame.cache.lock.vo.resp.SectionRespVo;
import com.lvmoney.frame.cache.redis.service.BaseRedisService;
import com.lvmoney.frame.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Service
public class SequenceServiceImpl implements SequenceService {
    @Value("${lock.expire:60}")
    String lockExpire;
    @Autowired
    BaseRedisService baseRedisService;
    @Autowired
    DistributedLockerService distributedLockerService;

    @Override
    public SectionRespVo getSectionData(SectionReqVo sectionVo) {
        String name = sectionVo.getName();
        int seq = sectionVo.getSeqSize();
        List<String> seqList = new ArrayList<>();
        distributedLockerService.lock(LockConstant.SECTION_LOCK_KEY, TimeUnit.SECONDS, LockConstant.LOCK_TIME);
        Long start = Long.parseLong(baseRedisService.getByKey(name).toString());
        Long end = start + seq;
        SectionInitVo sectionInitVo = new SectionInitVo(name, end, sectionVo.getExpire());
        initSectionData(sectionInitVo);
        distributedLockerService.unlock(LockConstant.SECTION_LOCK_KEY);
        for (int i = 1; i <= seq; i++) {
            seqList.add(StringUtil.addZeroForNum(String.valueOf(start + i), sectionVo.getSize()));
        }
        return new SectionRespVo(start + 1, end, seqList);
    }

    @Override
    public boolean initSectionData(SectionInitVo sectionInitVo) {
        baseRedisService.setString(sectionInitVo.getName(), String.valueOf(sectionInitVo.getValue()), sectionInitVo.getExpire());
        return false;
    }
}
