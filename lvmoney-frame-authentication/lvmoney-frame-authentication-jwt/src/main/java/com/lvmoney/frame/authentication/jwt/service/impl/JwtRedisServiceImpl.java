/**
 * 描述:
 * 包名:com.lvmoney.jwt.service.impl
 * 版本信息: 版本1.0
 * 日期:2019年1月4日  下午2:55:23
 * Copyright 四川******科技有限公司
 */

package com.lvmoney.frame.authentication.jwt.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.github.dozermapper.core.Mapper;
import com.lvmoney.frame.authentication.common.exception.AuthorityException;
import com.lvmoney.frame.authentication.service.service.UserCommonService;
import com.lvmoney.frame.authentication.util.util.JwtUtil;
import com.lvmoney.frame.authentication.util.vo.JwtVo;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.core.ro.UserRo;
import com.lvmoney.frame.authentication.jwt.service.JwtRedisService;
import com.lvmoney.frame.cache.common.annotations.CacheImpl;
import com.lvmoney.frame.cache.common.service.CacheCommonService;
import com.lvmoney.frame.core.vo.UserVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static com.lvmoney.frame.authentication.common.constant.AuthConstant.TOKEN_GROUP;
import static com.lvmoney.frame.base.core.constant.BaseConstant.CHARACTER_ASTERISK;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月4日 下午2:55:23
 */
@Service
public class JwtRedisServiceImpl implements JwtRedisService {
    @CacheImpl
    CacheCommonService baseRedisService;
    @Value("${jwt.server.expire:18000}")
    String expire;

    @Autowired
    private Mapper mapper;
    @Autowired
    UserCommonService userCommonService;

    @Override
    public void saveToken2Redis(UserRo userRo) {
        String redisKey = TOKEN_GROUP + userRo.getUserId() + BaseConstant.CONNECTOR_UNDERLINE + userRo.getToken();
        if (userRo.getExpire() > 0L) {
            baseRedisService.setString(redisKey, JsonUtil.t2JsonString(userRo), userRo.getExpire());
        } else {
            baseRedisService.setString(redisKey, JsonUtil.t2JsonString(userRo), Long.parseLong(expire));
        }
    }


    @Override
    public UserRo getUserRo(String token) {
        String userId = JwtUtil.getUserId(token);
        Object obj = baseRedisService.getByKey(TOKEN_GROUP + userId + BaseConstant.CONNECTOR_UNDERLINE + token);
        String jwtString = ObjectUtils.isEmpty(obj) ? null : obj.toString();
        if (jwtString != null) {
            UserRo userRo = JSON.parseObject(jwtString, new TypeReference<UserRo>() {
            });
            return userRo;
        } else {
            return null;
        }
    }

    @Override
    public boolean checkToken(String token) {
        String userId;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new BusinessException(AuthorityException.Proxy.TOKEN_USER_ID_ERROR);
        }
        UserVo userVo = userCommonService.getUserVo(token);
        if (userVo == null) {
            throw new BusinessException(AuthorityException.Proxy.TOKEN_USER_NOT_EXIST);
        }
        if (!userId.startsWith(userVo.getUserId())) {
            throw new BusinessException(AuthorityException.Proxy.TOKEN_USER_NOT_EXIST);
        }
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userVo.getPassword())).build();
        try {
            jwtVerifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }

    }

    @Override
    public void deleteToken(String token) {
        String userId = JwtUtil.getUserId(token);
        baseRedisService.deleteByKey(TOKEN_GROUP + userId + BaseConstant.CONNECTOR_UNDERLINE + token);
    }

    @Override
    public String updateToken(String token) {
        if (checkToken(token)) {
            UserRo userRo = getUserRo(token);
            JwtVo jwtVo = mapper.map(userRo, JwtVo.class);
            return JwtUtil.getToken(jwtVo);
        } else {
            return null;
        }

    }

    @Override
    public void deleteTokenByUserId(String userId) {
        baseRedisService.deleteByWildcardKey(TOKEN_GROUP + userId + CHARACTER_ASTERISK);
    }


}
