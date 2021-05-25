package com.lvmoney.frame.demo.seata.provider.service.impl;

import com.lvmoney.frame.demo.seata.provider.dao.UserDao;
import com.lvmoney.frame.demo.seata.provider.po.User;
import com.lvmoney.frame.demo.seata.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public int insertUser(User user) {
        return userDao.insert(user);
    }
}
