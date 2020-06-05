/**
 * 描述:
 * 包名:com.zhy.jwt.util
 * 版本信息: 版本1.0
 * 日期:2019年1月4日  下午2:30:43
 * Copyright 四川******科技有限公司
 */

package com.zhy.frame.authentication.util.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.zhy.frame.authentication.common.constant.AuthConstant;
import com.zhy.frame.authentication.util.vo.JwtVo;
import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.core.vo.UserVo;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月4日 下午2:30:43
 */

public class JwtUtil {
    /**
     * 算法
     */
    private static final String JWT_ALG = "HS256";
    /**
     * 类型
     */
    private static final String JWT_TYPE = "JWT";
    /**
     * key userId
     */
    private static final String KEY_USER_ID = "userId";
    /**
     * key sysId
     */
    private static final String KEY_SYS_ID = "sysId";

    /**
     * key 租户
     */
    private static final String KEY_TENANT_ID = "tenantId";
    /**
     * key iss
     */
    private static final String KEY_ISS = "iss";
    /**
     * key aud
     */
    private static final String KEY_AUD = "aud";
    /**
     * key jti
     */
    private static final String KEY_JTI = "jti";
    /**
     * key alg
     */
    private static final String KEY_ALG = "alg";
    /**
     * key typ
     */
    private static final String KEY_TYP = "typ";

    /**
     * @describe:获得用户的token，为了安全redis中不保存用户密码，用户传的password是加过密的就没有问题，就怕有人传的是明文，那么明文就直接丢到redis了
     * @param: [user]
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * 2019/9/5 10:47
     */
    @Deprecated
    public static String getToken(UserVo user) {
        String token = "";
        Long millisecond = Instant.now().toEpochMilli();
        token = JWT.create().withAudience(user.getUserId() + millisecond).sign(Algorithm.HMAC256(user.getPassword()));
        token = AuthConstant.TOKEN_JWT_PREFIX + token;
        return token;
    }

    /**
     * 改进版本获得token
     *
     * @param jwtVo:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/3/3 18:13
     */
    public static String getToken(JwtVo jwtVo) {
        String password = jwtVo.getPassword();
        String sysId = jwtVo.getSysId();
        String tenantId = jwtVo.getTenantId();
        Map<String, Object> map = new HashMap(BaseConstant.MAP_DEFAULT_SIZE);
        map.put(KEY_ALG, JWT_ALG);
        map.put(KEY_TYP, JWT_TYPE);
        Date iatDate = new Date();
        String userId = jwtVo.getUserId();
        String token = JWT.create().withHeader(map)
                .withClaim(KEY_SYS_ID, sysId)
                .withClaim(KEY_USER_ID, userId)
                .withClaim(KEY_TENANT_ID, tenantId)
                .withClaim(KEY_ISS, jwtVo.getIss())
                .withClaim(KEY_AUD, jwtVo.getAud())
                .withClaim(KEY_JTI, jwtVo.getJti())
                .withIssuedAt(iatDate)
                .withIssuer(userId)
                .sign(Algorithm.HMAC256(password));
        return token;
    }

    /**
     * 去掉jwt:token的前缀
     *
     * @param token:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/3/3 18:41
     */
    public static String getRealToken(String token) {
        return token.startsWith(AuthConstant.TOKEN_JWT_PREFIX) ? token.replaceFirst(AuthConstant.TOKEN_JWT_PREFIX, "") : token;
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
        String verifyToken = getRealToken(token);
        String userId = JWT.decode(verifyToken).getClaim(KEY_USER_ID).asString();
        return userId;
    }

    /**
     * 直接从token中获得用户信息
     *
     * @param token:
     * @throws
     * @return: com.zhy.frame.core.vo.UserVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/3/3 18:14
     */
    public static UserVo getUserVo(String token) {
        String verifyToken = getRealToken(token);
        String userId = JWT.decode(verifyToken).getClaim(KEY_USER_ID).asString();
        String sysId = JWT.decode(verifyToken).getClaim(KEY_SYS_ID).asString();
        String tenantId = JWT.decode(verifyToken).getClaim(KEY_TENANT_ID).asString();
        UserVo userVo = new UserVo();
        userVo.setSysId(sysId);
        userVo.setUserId(userId);
        userVo.setTenantId(tenantId);
        return userVo;
    }

    public static void main(String[] args) {
        JwtVo jwtVo = new JwtVo();
        jwtVo.setPassword("1212");
        jwtVo.setUserId("1212");
        jwtVo.setSysId("AAA");
        jwtVo.setTenantId("123");
        jwtVo.setExp(18000L);
        String token2 = getToken(jwtVo);
        System.out.println(token2);
        System.out.println(token2.length());
        System.out.println(getUserVo(token2));

        String uid = getUserId(token2);
        System.out.println(uid);
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzeXNJZCI6IkFBQSIsImlzcyI6IjEyMTIiLCJ1c2VySWQiOiIxMjEyIiwiaWF0IjoxNTgzMzc0MDYyfQ.YzMtM5kdSqyLdluoXEnPp1PWBn6CWmAfR72S2CXWaFE";
        System.out.println(token.length());
        boolean succ = checkPassword(token, jwtVo.getPassword(), jwtVo.getUserId());
        System.out.println(succ);
    }

    public static boolean checkPassword(String token, String password, String userId) {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(password)).withIssuer(userId).build();
        try {
            String verifyToken = JwtUtil.getRealToken(token);
            jwtVerifier.verify(verifyToken);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

}
