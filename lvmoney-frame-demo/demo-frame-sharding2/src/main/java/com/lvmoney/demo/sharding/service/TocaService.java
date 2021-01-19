package com.lvmoney.demo.sharding.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lvmoney.demo.sharding.entity.Toca;
import com.lvmoney.demo.sharding.po.TestPo;

import java.util.List;

/**
 * <p>
 * 会用相关字典表 服务类
 * </p>
 *
 * @author lvmoney
 * @since 2020-01-15
 */
public interface TocaService extends IService<Toca> {

    public void batchSave(Toca toca);

    List<TestPo> slectAll();


}
