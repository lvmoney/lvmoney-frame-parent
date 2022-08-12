package com.lvmoney.frame.sync.netty.socket.service;


import com.lvmoney.frame.sync.netty.socket.model.vo.ResponseJson;

public interface UserInfoService {

    ResponseJson getByUserId(String userId);
}
