package com.zhy.frame.db.sharding.base.service;/**
 * 描述:
 * 包名:com.zhy.mysql.subdb.service
 * 版本信息: 版本1.0
 * 日期:2020/1/7
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.db.sharding.base.po.MemberUser;
import com.zhy.frame.db.sharding.base.po.User;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/1/7 15:55
 */
public interface UserService {
    int saveUser(User user);

    User getUserById(String id);

    int updateUserById(String id);

    int deleteUserById(String id);

    List<MemberUser> seletMemberByUserId(String id);

}
