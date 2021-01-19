package com.lvmoney.frame.sync.netty.socket.dao;


import com.lvmoney.frame.sync.netty.socket.model.po.UserInfo;

public interface UserInfoDao {

    void loadUserInfo();

    UserInfo getByUsername(String username);

    UserInfo getByUserId(String userId);
}
