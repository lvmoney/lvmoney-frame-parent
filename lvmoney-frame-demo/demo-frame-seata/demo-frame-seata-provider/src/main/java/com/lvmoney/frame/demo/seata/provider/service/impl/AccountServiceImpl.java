package com.lvmoney.frame.demo.seata.provider.service.impl;

import com.lvmoney.frame.demo.seata.provider.dao.AccountDao;
import com.lvmoney.frame.demo.seata.provider.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDao accountDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateAccount(String userid) {
        return accountDao.updateAccount(userid);
    }


}
