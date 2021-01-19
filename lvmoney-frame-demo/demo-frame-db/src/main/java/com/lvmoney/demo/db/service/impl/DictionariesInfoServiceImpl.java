package com.lvmoney.demo.db.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvmoney.demo.db.dao.DictionariesInfoDao;
import com.lvmoney.demo.db.entity.DictionariesInfo;
import com.lvmoney.demo.db.service.DictionariesInfoService;
import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.base.core.exception.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 会用相关字典表 服务实现类
 * </p>
 *
 * @author lvmoney
 * @since 2020-01-15
 */
@Service
public class DictionariesInfoServiceImpl extends ServiceImpl<DictionariesInfoDao, DictionariesInfo> implements DictionariesInfoService {
    @Autowired
    DictionariesInfoDao dictionariesInfoDao;

    @Override
    public void delete(String code) {
        this.removeById(code);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(DictionariesInfo dictionariesInfo) {
        this.save(dictionariesInfo);
        throw new BusinessException(CommonException.Proxy.PARAM_ERROR);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void errorTest(String code, DictionariesInfo dictionariesInfo) {
        this.delete(code);
        this.add(dictionariesInfo);
    }

    @Override

    public DictionariesInfo getDataByName(String name) {
        return dictionariesInfoDao.getDataByName(name);
    }
}
