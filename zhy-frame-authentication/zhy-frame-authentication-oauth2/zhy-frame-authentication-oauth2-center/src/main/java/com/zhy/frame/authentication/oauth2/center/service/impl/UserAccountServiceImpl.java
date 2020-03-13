package com.zhy.frame.authentication.oauth2.center.service.impl;

import com.github.dozermapper.core.Mapper;
import com.zhy.frame.authentication.oauth2.center.db.dao.UserAccountDao;
import com.zhy.frame.authentication.oauth2.center.db.entity.UserAccount;
import com.zhy.frame.authentication.oauth2.center.exception.Oauth2Exception;
import com.zhy.frame.authentication.oauth2.center.service.UserAccountService;
import com.zhy.frame.authentication.oauth2.center.vo.JsonObjects;
import com.zhy.frame.authentication.oauth2.center.vo.UserAccountVo;
import com.zhy.frame.core.exception.BusinessException;
import com.zhy.frame.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    UserAccountDao userAccountDao;

    @Autowired
    Mapper dozerMapper;

    @Value("${signin.failure.max:5}")
    private int failureMax;

    @Override
    public JsonObjects<UserAccountVo> listByUsername(String username, int pageNum, int pageSize, String sortField, String sortOrder) {
        return null;
    }


    @Override
    public UserAccountVo findByUsername(String username) {

        UserAccount userAccountEntity = userAccountDao.findByUsername(username);
        if (userAccountEntity != null) {
            return dozerMapper.map(userAccountEntity, UserAccountVo.class);
        } else {
            throw null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Async
    public void loginSuccess(String username) {
        UserAccount userAccountEntity = userAccountDao.findByUsername(username);
        if (userAccountEntity != null) {
            userAccountEntity.setFailureCount(0);
            userAccountDao.updateById(userAccountEntity);
        } else {
            throw new BusinessException(Oauth2Exception.Proxy.OAUTH2_USER_NOT_EXSIT);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void loginFailure(String username) {
        UserAccount userAccountEntity = userAccountDao.findByUsername(username);
        if (userAccountEntity != null) {
            if (userAccountEntity.getFailureTime() == null) {
                userAccountEntity.setFailureCount(1);
            } else {
                if (DateUtil.beforeToday(userAccountEntity.getFailureTime())) {
                    userAccountEntity.setFailureCount(0);
                } else {
                    userAccountEntity.setFailureCount(userAccountEntity.getFailureCount() + 1);
                }
            }
            userAccountEntity.setFailureTime(new Date());
            if (userAccountEntity.getFailureCount() >= failureMax && userAccountEntity.getValid() >= 0) {
                userAccountEntity.setValid(-1);
            }
            userAccountDao.updateById(userAccountEntity);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserAccountVo create(UserAccountVo userAccountVo) {
        return userAccountVo;
    }


    @Override
    public UserAccountVo retrieveById(long id) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserAccountVo updateById(UserAccountVo userAccountVo) {
        return null;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRecordStatus(long id, int recordStatus) {
    }

}
