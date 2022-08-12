package com.lvmoney.frame.sync.uniformity.unique.service.impl;/**
 * 描述:
 * 包名:com.chinapopin.platform.overall.unique.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/10/10
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.cache.common.annotations.CacheImpl;
import com.lvmoney.frame.cache.common.constant.CacheConstant;
import com.lvmoney.frame.cache.common.service.CacheCommonService;
import com.lvmoney.frame.sync.uniformity.unique.bo.UniqueCodeGetBo;
import com.lvmoney.frame.sync.uniformity.unique.common.UniformityUniqueConstant;
import com.lvmoney.frame.sync.uniformity.unique.dto.UniqueCodeGetDto;
import com.lvmoney.frame.sync.uniformity.unique.ro.UniqueCodeGetRo;
import com.lvmoney.frame.sync.uniformity.unique.service.UniqueCodeService;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/10/10 10:13
 */
@Service
public class UniqueCodeServiceImpl implements UniqueCodeService {
    @CacheImpl(CacheConstant.CACHE_REDIS)
    CacheCommonService cacheCommonService;
    /**
     * 最大长度
     */
    private static final Long MAX_SIZE = 999999999L;
    /**
     * 初始值
     */
    private static final Long INIT_VALUE = 0L;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public List<UniqueCodeGetBo> getByKey(UniqueCodeGetDto uniqueCodeGetDto) {
        String codeKey = UniformityUniqueConstant.UNIQUE_CODE_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + uniqueCodeGetDto.getClientId() + BaseConstant.CONNECTOR_UNDERLINE + LocalDate.now().toString();
        if (ObjectUtils.isEmpty(cacheCommonService.getByKey(codeKey))) {
            //如果key不存在，那么直接生成最新code
            UniqueCodeGetRo uniqueCodeGetRo = new UniqueCodeGetRo();
            uniqueCodeGetRo.setCodeKey(codeKey);
            uniqueCodeGetRo.setCode(INIT_VALUE);
            this.save2Redis(uniqueCodeGetRo);
        }
        DefaultRedisScript<String> longDefaultRedisScript = new DefaultRedisScript<>(UniformityUniqueConstant.UNIQUE_CODE_SCRIPT_LUA_ADD, String.class);
        String result = stringRedisTemplate.execute(longDefaultRedisScript, Collections.singletonList(codeKey), String.valueOf(uniqueCodeGetDto.getSize()));
        Long code = Long.valueOf(result);

        List<UniqueCodeGetBo> uniqueCodeGetBoList = new ArrayList();
        for (Long i = code; i > code - uniqueCodeGetDto.getSize(); i--) {
            UniqueCodeGetBo uniqueCodeGetBo = new UniqueCodeGetBo();
            uniqueCodeGetBo.setCode(i);
            uniqueCodeGetBoList.add(uniqueCodeGetBo);
        }

        return uniqueCodeGetBoList;
    }

    @Override
    public void save2Redis(UniqueCodeGetRo uniqueCodeGetRo) {
        cacheCommonService.setString(uniqueCodeGetRo.getCodeKey(), uniqueCodeGetRo.getCode(), ObjectUtils.isEmpty(uniqueCodeGetRo.getExpired()) ? uniqueCodeGetRo.getExpired() : UniformityUniqueConstant.UNIQUE_CODE_EXPIRED);
    }

    @Override
    public int getKey(String clientId) {
        String codeKey = UniformityUniqueConstant.UNIQUE_CODE_PREFIX + BaseConstant.CONNECTOR_UNDERLINE + clientId + BaseConstant.CONNECTOR_UNDERLINE + LocalDate.now().toString();

        Object obj = cacheCommonService.getByKey(codeKey);
        return Integer.parseInt(String.valueOf(obj));
    }

    /**
     * 获得当前日期并转换成长整型
     *
     * @throws
     * @return: java.lang.Long
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/12/30 13:30
     */
    private Long getNow() {
        LocalDate ld = LocalDate.now();
        String nowDay = ld.toString().replaceAll(BaseConstant.DASH_LINE, BaseConstant.EMPTY);
        return Long.parseLong(nowDay);
    }

    public static void main(String[] args) {

        LocalDate ld = LocalDate.now();
        String nowDay = ld.toString().replaceAll(BaseConstant.DASH_LINE, BaseConstant.EMPTY);
        System.out.println(nowDay);
    }
}
