package com.lvmoney.frame.authentication.service.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.authentication.util.service
 * 版本信息: 版本1.0
 * 日期:2020/3/9
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.core.vo.UserVo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/9 9:52
 */
public interface UserCommonService {

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
}
