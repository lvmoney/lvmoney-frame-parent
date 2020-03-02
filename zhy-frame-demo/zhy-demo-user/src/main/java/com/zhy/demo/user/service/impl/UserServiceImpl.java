package com.zhy.demo.user.service.impl;/**
 * 描述:
 * 包名:com.zhy.tmc.api.service.impl
 * 版本信息: 版本1.0
 * 日期:2019/11/22
 * Copyright XXXXXX科技有限公司
 */


import com.github.dozermapper.core.Mapper;
import com.zhy.demo.user.vo.req.GetUserReqVo;
import com.zhy.demo.user.vo.req.UserLoginReqVo;
import com.zhy.demo.user.vo.resp.GetUserRespVo;
import com.zhy.frame.authentication.jwt.service.JwtRedisService;
import com.zhy.frame.authentication.jwt.util.JwtUtil;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.core.ro.UserRo;
import com.zhy.demo.user.dao.UserDao;
import com.zhy.demo.user.entity.User;
import com.zhy.demo.user.exception.UserException;
import com.zhy.demo.user.service.UserService;
import com.zhy.demo.user.vo.resp.UserLoginRespVo;
import com.zhy.frame.core.vo.UserVo;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/22 17:06
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    Mapper dozerMapper;

    @Autowired
    JwtRedisService jwtRedisService;
    @Autowired
    UserDao userDao;

    @Override
    public GetUserRespVo getUser(GetUserReqVo getUserReqVo) {
        GetUserRespVo getUserRespVo = dozerMapper.map(getUserReqVo, GetUserRespVo.class);
        getUserRespVo.setSex("男");
        getUserRespVo.setAddress("四川省成都市武侯区创新时代广场");
        return getUserRespVo;
    }

    @Override
    public UserLoginRespVo userLogin(UserLoginReqVo userLoginReqVo) {
        User user = checkUserLogin(userLoginReqVo);
        if (ObjectUtils.isEmpty(user)) {
            throw new BusinessException(UserException.Proxy.USER_LOGIN_CHECK_ERROR);
        }
        UserVo userVo = dozerMapper.map(userLoginReqVo, UserVo.class);
        userVo.setUserId(user.getUid());
        String token = JwtUtil.getToken(userVo);
        UserRo userRo = dozerMapper.map(userLoginReqVo, UserRo.class);
        userRo.setToken(token);
        userRo.setUserId(user.getUid());
        jwtRedisService.saveToken2Redis(userRo);
        UserLoginRespVo userLoginRespVo = new UserLoginRespVo();
        userLoginRespVo.setToken(token);
        return userLoginRespVo;
    }

    @Override
    public User checkUserLogin(UserLoginReqVo userLoginReqVo) {
        User user = userDao.selectByLoginUser(userLoginReqVo.getUsername(), userLoginReqVo.getPassword());
        return user;
    }
}
