package com.zhy.demo.db.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhy.demo.db.entity.DictionariesInfo;

/**
 * <p>
 * 会用相关字典表 服务类
 * </p>
 *
 * @author lvmoney
 * @since 2020-01-15
 */
public interface DictionariesInfoService extends IService<DictionariesInfo> {
    void delete(String code);

    void add(DictionariesInfo dictionariesInfo);


    void errorTest(String code, DictionariesInfo dictionariesInfo);

}
