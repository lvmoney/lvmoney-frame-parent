package com.lvmoney.frame.demo.seata.test.service.impl;

import com.lvmoney.frame.demo.seata.test.dao.StageDao;
import com.lvmoney.frame.demo.seata.test.dao.UserDao;
import com.lvmoney.frame.demo.seata.test.po.User;
import com.lvmoney.frame.demo.seata.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    StageDao stageDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertUser(User user) {
        return userDao.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateStage(String userId) {
        return stageDao.updateStage(userId);
    }
}
