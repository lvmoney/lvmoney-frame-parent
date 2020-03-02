/**
 * 描述:
 * 包名:com.zhy.jwt.util
 * 版本信息: 版本1.0
 * 日期:2019年1月4日  下午2:30:43
 * Copyright 四川******科技有限公司
 */

package com.zhy.frame.authentication.jwt.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zhy.frame.authentication.common.constant.AuthConstant;
import com.zhy.frame.core.vo.UserVo;

import java.time.Instant;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月4日 下午2:30:43
 */

public class JwtUtil {
    /**
     * @describe:获得用户的token，为了安全redis中不保存用户密码，用户传的password是加过密的就没有问题，就怕有人传的是明文，那么明文就直接丢到redis了
     * @param: [user]
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/5 10:47
     */
    public static String getToken(UserVo user) {
        String token = "";
        Long millisecond = Instant.now().toEpochMilli();
        token = JWT.create().withAudience(user.getUserId() + millisecond).sign(Algorithm.HMAC256(user.getPassword()));
        token = AuthConstant.TOKEM_JWT_PREFIX + token;
        return token;
    }

    /**
     * 通过token获得用户id
     *
     * @param token:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/12/2 14:01
     */
    public static String getUserId(String token) {
        String verifyToken = token.startsWith(AuthConstant.TOKEM_JWT_PREFIX) ? token.replaceFirst(AuthConstant.TOKEM_JWT_PREFIX, "") : token;
        String userId = JWT.decode(verifyToken).getAudience().get(0);
        return userId;
    }

}
