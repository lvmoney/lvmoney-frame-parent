package com.lvmoney.frame.sync.uniformity.db.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.sync.uniformity.db.service
 * 版本信息: 版本1.0
 * 日期:2022/1/5
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.sync.uniformity.db.dto.GetSelectDurationDto;
import com.lvmoney.frame.sync.uniformity.db.ro.SelectDurationRo;
import com.lvmoney.frame.sync.uniformity.db.ro.ShardingLogicRo;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/1/5 14:54
 */
public interface UniformityRedisService {
    /**
     * sharding 一致性数据保存到redis
     *
     * @param shardingLogicRoList:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/1/5 15:00
     */
    void uniformityConfig2Redis(List<ShardingLogicRo> shardingLogicRoList);

    /**
     * 获得 sharding 一致性配置
     *
     * @throws
     * @return: java.util.List<com.lvmoney.frame.sync.uniformity.db.ro.ShardingLogicRo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/1/5 15:07
     */
    List<ShardingLogicRo> getUniformityConfig();

    /**
     * 上一次数据同步截止时间
     *
     * @param selectDurationRo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/1/6 17:13
     */
    void selectDuration2Redis(SelectDurationRo selectDurationRo);

    /**
     * 获得上一次数据同步截止时间
     *
     * @param getSelectDurationDto:
     * @throws
     * @return: com.lvmoney.frame.sync.uniformity.db.ro.SelectDurationRo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/1/6 17:20
     */
    SelectDurationRo getSelectDuration(GetSelectDurationDto getSelectDurationDto);

}
