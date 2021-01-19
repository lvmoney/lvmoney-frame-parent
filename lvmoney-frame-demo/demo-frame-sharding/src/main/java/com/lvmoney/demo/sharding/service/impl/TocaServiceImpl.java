package com.lvmoney.demo.sharding.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvmoney.demo.sharding.dao.TocaDao;
import com.lvmoney.demo.sharding.entity.Toca;
import com.lvmoney.demo.sharding.service.TocaService;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 会用相关字典表 服务实现类
 * </p>
 *
 * @author lvmoney
 * @since 2020-01-15
 */
@Service
public class TocaServiceImpl extends ServiceImpl<TocaDao, Toca> implements TocaService {
    @Autowired
    TocaDao tocaDao;

    @Override
    public void batchSave(Toca toca) {
        try (HintManager hintManager = HintManager.getInstance()) {
            hintManager.addTableShardingValue("toca", "toca_1");
            hintManager.addDatabaseShardingValue("toca", "ds0");
            this.save(toca);
        }
    }
}
