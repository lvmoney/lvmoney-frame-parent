package com.zhy.frame.authentication.oauth2.center.service.impl;

import com.github.dozermapper.core.Mapper;
import com.zhy.frame.authentication.oauth2.center.db.dao.LoginHistoryDao;
import com.zhy.frame.authentication.oauth2.center.db.entity.LoginHistory;
import com.zhy.frame.authentication.oauth2.center.service.LoginHistoryService;
import com.zhy.frame.authentication.oauth2.center.vo.JsonObjects;
import com.zhy.frame.authentication.oauth2.center.vo.LoginHistoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Service
public class LoginHistoryServiceImpl implements LoginHistoryService {
    @Autowired
    LoginHistoryDao loginHistoryDao;

    @Autowired
    Mapper dozerMapper;

    @Override
    public JsonObjects<LoginHistoryVo> listByUsername(String username, int pageNum, int pageSize, String sortField, String sortOrder) {
        JsonObjects<LoginHistoryVo> jsonObjects = new JsonObjects<>();
        return jsonObjects;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Async
    public void asyncCreate(LoginHistoryVo loginHistoryVo) {
        LoginHistory entity = dozerMapper.map(loginHistoryVo, LoginHistory.class);
        loginHistoryDao.insert(entity);
    }
}
