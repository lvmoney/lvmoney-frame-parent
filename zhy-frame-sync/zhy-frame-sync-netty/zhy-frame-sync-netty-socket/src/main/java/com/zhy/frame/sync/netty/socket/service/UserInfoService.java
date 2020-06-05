package com.zhy.frame.sync.netty.socket.service;


import com.zhy.frame.sync.netty.socket.model.vo.ResponseJson;

public interface UserInfoService {

    ResponseJson getByUserId(String userId);
}
