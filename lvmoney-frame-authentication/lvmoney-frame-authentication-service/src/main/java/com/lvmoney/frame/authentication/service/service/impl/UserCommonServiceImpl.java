package com.lvmoney.frame.authentication.service.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.authentication.service.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/3/9
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lvmoney.frame.authentication.service.service.UserCommonService;
import com.lvmoney.frame.authentication.util.util.JwtUtil;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.cache.common.annotations.CacheImpl;
import com.lvmoney.frame.cache.common.service.CacheCommonService;
import com.lvmoney.frame.core.ro.UserRo;
import com.lvmoney.frame.core.vo.UserVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import static com.lvmoney.frame.authentication.common.constant.AuthConstant.TOKEN_GROUP;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/9 9:52
 */
@Service
public class UserCommonServiceImpl implements UserCommonService {
    @CacheImpl
    CacheCommonService baseRedisService;

    @Override
    public UserVo getUserVo(String token) {
        String userId = JwtUtil.getUserId(token);
        Object obj = baseRedisService.getByKey(TOKEN_GROUP + userId + BaseConstant.CONNECTOR_UNDERLINE + token);
        String jwtString = ObjectUtils.isEmpty(obj) ? null : obj.toString();
        UserVo userVo = new UserVo();
        if (jwtString != null) {
            UserRo userRo = JSON.parseObject(jwtString, new TypeReference<UserRo>() {
            });
            if (userRo != null) {
                userVo.setUserId(userRo.getUserId());
                userVo.setPassword(userRo.getPassword());
                userVo.setUsername(userRo.getUsername());
                userVo.setOther(userRo.getOther());
                userVo.setSysId(userRo.getSysId());
                userVo.setTenantId(userRo.getTenantId());
                userVo.setOrgId(userRo.getOrgId());
                return userVo;
            }
        }
        return null;
    }
}
