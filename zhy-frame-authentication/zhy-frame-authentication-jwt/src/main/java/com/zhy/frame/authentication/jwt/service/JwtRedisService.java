/**
 * 描述:
 * 包名:com.zhy.jwt.service
 * 版本信息: 版本1.0
 * 日期:2019年1月4日  下午2:38:07
 * Copyright 四川******科技有限公司
 */

package com.zhy.frame.authentication.jwt.service;

import com.zhy.frame.core.ro.UserRo;
import com.zhy.frame.core.vo.UserVo;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2019年1月4日 下午2:38:07
 */

public interface JwtRedisService {
    /**
     * 存储token到redis
     *
     * @param userRo: 用户信息
     * @throws
     * @return: void
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:48
     */
    void saveToken2Redis(UserRo userRo);

    /**
     * 通过token获得用户信息
     *
     * @param token:
     * @throws
     * @return: UserVo 用户信息
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:48
     */
    UserVo getUserVo(String token);

    /**
     * 校验token是否存在
     *
     * @param token: token
     * @throws
     * @return: boolean
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/9/9 20:49
     */
    boolean checkToken(String token);

    /**
     * 删除token
     *
     * @param token:
     * @throws
     * @return: boolean
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/11/13 11:34
     */
    void deleteToken(String token);

    /**
     *更新token
     * @param token:
     * @return: java.lang.String
     * @throws
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/11/19 17:53
     */
    String updateToken(String token);


}
