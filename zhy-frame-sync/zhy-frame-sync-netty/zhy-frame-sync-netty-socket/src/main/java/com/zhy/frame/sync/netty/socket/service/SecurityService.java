package com.zhy.frame.sync.netty.socket.service;


import com.zhy.frame.sync.netty.socket.model.vo.ResponseJson;

import javax.servlet.http.HttpSession;

public interface SecurityService {

    ResponseJson login(String username, String password, HttpSession session);

    ResponseJson logout(HttpSession session);
}
