/**
 * 描述:
 * 包名:com.lvmoney.jwt.service
 * 版本信息: 版本1.0
 * 日期:2019年1月4日  下午2:38:07
 * Copyright 四川******科技有限公司
 */

package com.lvmoney.frame.authentication.jwt.service;

import com.lvmoney.frame.core.ro.UserRo;
import com.lvmoney.frame.core.vo.UserVo;

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
     * 通过token获得token信息
     *
     * @param token:
     * @throws
     * @return: com.lvmoney.frame.core.ro.UserRo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/3/3 18:19
     */
    UserRo getUserRo(String token);

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
     * 更新token
     *
     * @param token:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /四川******科技有限公司
     * @date: 2019/11/19 17:53
     */
    String updateToken(String token);


}
