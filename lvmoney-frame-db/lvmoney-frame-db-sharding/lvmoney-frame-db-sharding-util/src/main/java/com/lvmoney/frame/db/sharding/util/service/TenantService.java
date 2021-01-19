package com.lvmoney.frame.db.sharding.util.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.db.sharding.util.service
 * 版本信息: 版本1.0
 * 日期:2020/10/26
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.db.sharding.util.bo.GetDbTenantBo;
import com.lvmoney.frame.db.sharding.util.dto.GetDbTableDto;
import com.lvmoney.frame.db.sharding.util.dto.GetDbTenantDto;
import com.lvmoney.frame.db.sharding.util.ro.DbTableRelRo;
import com.lvmoney.frame.db.sharding.util.ro.DbTenantRelRo;
import com.lvmoney.frame.db.sharding.util.ro.item.DbTableRelRoItem;
import com.lvmoney.frame.db.sharding.util.ro.item.DbTenantRelRoItem;

import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/10/26 18:07
 */
public interface TenantService {

    /**
     * 获得租户对应的库表
     * 1:从redis判断对应关系是否已经存在，如果已存在那么直接使用
     * 2:如果对应关系没有那就重新生成，并保存到redis。该关系需要保存到数据库
     * 3:这样做得好处就是新增库表后，原有的映射关系不会变动，那么通过原来的映射关系能够继续获得正确的数据
     * 4:要求已有的库表关系不能删除和更改。删除和更改会导致已有的关系混乱
     * 5:设计上固定了租户和数据库表的关系，某些库表出问题了会影响某些对应的租户，对其他租户不会影响
     * 6:库表关系需要做记录，例如：新增了库表需要做记录，这部分数据维护到数据库
     * 7:新增了库表，那么需要把新增库表后面的租户数据和新的库表对应起来，久的对应关系就不变动了
     *
     * @param getDbTenantDto:
     * @throws
     * @return: com.lvmoney.frame.db.sharding.util.bo.GetDbTenantBo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/10/27 16:44
     */
    GetDbTenantBo getDbTenant(GetDbTenantDto getDbTenantDto);

    /**
     * 数据库表关系保存到redis
     *
     * @param dbTableRelRo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/10/27 16:03
     */
    void dbTableRel2Redis(DbTableRelRo dbTableRelRo);


    /**
     * 根据库分类（group）获得对应的库表关系
     *
     * @param getDbTableDto:
     * @throws
     * @return: java.util.List<com.lvmoney.frame.db.sharding.util.ro.item.DbTableRelRoItem>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/10/27 16:35
     */
    List<DbTableRelRoItem> getDbTableByRedis(GetDbTableDto getDbTableDto);

    /**
     * 租户的库表关系保存到redis
     *
     * @param dbTenantRelRo:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/10/27 16:04
     */
    void dbTenantRel2Redis(DbTenantRelRo dbTenantRelRo);


    /**
     * 获取以分配库表的租户信息
     *
     * @param getDbTenantDto:
     * @throws
     * @return: com.lvmoney.frame.db.sharding.util.ro.item.DbTenantRelRoItem
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/10/27 18:43
     */
    DbTenantRelRoItem getDbTenantByRedis(GetDbTenantDto getDbTenantDto);

    /**
     * 根据group获得租户数据库表关系
     *
     * @param group:
     * @throws
     * @return: java.util.Map<java.lang.String, com.lvmoney.frame.db.sharding.util.ro.item.DbTenantRelRoItem>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/10/28 9:16
     */
    Map<String, DbTenantRelRoItem> getDbTenantByKey(String group);

}
