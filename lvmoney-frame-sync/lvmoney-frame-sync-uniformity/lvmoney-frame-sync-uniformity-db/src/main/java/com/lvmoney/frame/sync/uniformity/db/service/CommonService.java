package com.lvmoney.frame.sync.uniformity.db.service;/**
 * 描述:
 * 包名:com.lvmoney.demo.sharding.service
 * 版本信息: 版本1.0
 * 日期:2021/12/31
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.sync.uniformity.db.dto.GetBitmapSharingListDto;
import com.lvmoney.frame.sync.uniformity.db.dto.GetSharingListDto;

import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/12/31 11:08
 */
public interface CommonService {
    /**
     * 根据sql语句查询数据，返回list<Map>
     *
     * @param getSharingListDto:
     * @throws
     * @return: java.util.List<java.util.Map>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/12/31 12:57
     */
    List<Map> getShardingList(GetSharingListDto getSharingListDto);

    /**
     * 获得数据总数
     *
     * @param getSharingListDto:
     * @throws
     * @return: java.lang.Long
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/12/31 15:30
     */
    Long getShardingCount(GetSharingListDto getSharingListDto);

    /**
     * 构造查询sql
     *
     * @param getSharingListDto:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/12/31 13:41
     */
    String structureShardingSelectSql(GetSharingListDto getSharingListDto);


    /**
     * 构造获得总数sql
     *
     * @param getSharingListDto:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/12/31 13:41
     */
    String structureShardingCountSql(GetSharingListDto getSharingListDto);


    /**
     * 构造遗漏数据查询sql
     *
     * @param getBitmapSharingListDto:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/12/31 13:41
     */
    String structureBitmapShardingSelectSql(GetBitmapSharingListDto getBitmapSharingListDto);


    /**
     * 根据遗漏sql语句查询数据，返回list<Map>
     *
     * @param getBitmapSharingListDto:
     * @throws
     * @return: java.util.List<java.util.Map>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/12/31 12:57
     */
    List<Map> getBitmapShardingList(GetBitmapSharingListDto getBitmapSharingListDto);


}
