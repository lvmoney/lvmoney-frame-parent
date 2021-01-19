package com.lvmoney.frame.sync.netty.socket.service.impl;

import com.lvmoney.frame.sync.netty.socket.dao.UserInfoDao;
import com.lvmoney.frame.sync.netty.socket.model.po.UserInfo;
import com.lvmoney.frame.sync.netty.socket.model.vo.ResponseJson;
import com.lvmoney.frame.sync.netty.socket.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public ResponseJson getByUserId(String userId) {
        UserInfo userInfo = userInfoDao.getByUserId(userId);
        return new ResponseJson().success()
                .setData("userInfo", userInfo);
    }

}
