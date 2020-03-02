package com.zhy.demo.user.service;/**
 * 描述:
 * 包名:com.zhy.tmc.api.service
 * 版本信息: 版本1.0
 * 日期:2019/11/22
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.demo.user.vo.req.GetUserReqVo;
import com.zhy.demo.user.vo.req.UserLoginReqVo;
import com.zhy.demo.user.vo.resp.GetUserRespVo;
import com.zhy.demo.user.entity.User;
import com.zhy.demo.user.vo.resp.UserLoginRespVo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/22 17:06
 */
public interface UserService {
    /**
     * 通过用户名获得用户信息
     *
     * @param getUserReqVo: 含有user的请求实体
     * @throws
     * @return: GetUserRespVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/22 17:08
     */
    GetUserRespVo getUser(GetUserReqVo getUserReqVo);

    /**
     * 用户登录并获得token
     *
     * @param userLoginReqVo:
     * @throws
     * @return: UserLoginRespVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/22 17:48
     */
    UserLoginRespVo userLogin(UserLoginReqVo userLoginReqVo);

    /**
     * 获得登录的用户
     *
     * @param userLoginReqVo:
     * @throws
     * @return: User
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/25 9:03
     */
    User checkUserLogin(UserLoginReqVo userLoginReqVo);
}
