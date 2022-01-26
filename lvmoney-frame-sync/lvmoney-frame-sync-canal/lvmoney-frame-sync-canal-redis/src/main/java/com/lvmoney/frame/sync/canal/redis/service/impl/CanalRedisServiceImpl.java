package com.lvmoney.frame.sync.canal.redis.service.impl;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.cache.redis.service.BaseRedisService;
import com.lvmoney.frame.core.util.MapUtil;
import com.lvmoney.frame.base.core.vo.CorePage;
import com.lvmoney.frame.sync.canal.common.service.impl.AbstractCanalService;
import com.lvmoney.frame.sync.canal.common.vo.DbMsgVo;
import com.lvmoney.frame.sync.canal.redis.constant.CancalRedisConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;


/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@Service
public class CanalRedisServiceImpl extends AbstractCanalService {
    @Autowired
    BaseRedisService baseRedisService;

    @Override
    public void save(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
        Map<String, Object> allMap = new HashMap<>(MapUtil.initMapSize(rowDatasList.size()));
        for (CanalEntry.RowData rowData : rowDatasList) {
            Map<String, Object> map = new HashMap<>(MapUtil.initMapSize(rowData.getAfterColumnsList().size()));
            AtomicReference<String> key = new AtomicReference<>("nul");
            rowData.getAfterColumnsList().forEach((c) -> {
                if (c.getIsKey()) {
                    key.set(c.getValue());
                }
                map.put(c.getName(), c.getValue());
            });
            allMap.put(key.toString(), map);
        }
        String dbTableName = getDbTableName(dbMsgVo);
        baseRedisService.addMap(dbTableName, allMap, null);
    }


    @Override
    public void deleteRow(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
        String dbTableName = super.getDbTableName(dbMsgVo);
        for (CanalEntry.RowData rowData : rowDatasList) {
            rowData.getBeforeColumnsList().forEach((c) -> {
                if (c.getIsKey()) {
                    baseRedisService.deleteByMapKey(dbTableName, dbTableName, c.getValue());
                }
            });
        }

    }

    @Override
    public void deleteTable(DbMsgVo dbMsgVo) {
        String dbTableName = getDbTableName(dbMsgVo);
        baseRedisService.deleteByKey(dbTableName);
    }

    @Override
    public void renameTable(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        String dbTableName = getDbTableName(dbMsgVo);
        String oldDbTableName = dbMsgVo.getSchemaName().toUpperCase() + BaseConstant.CONNECTOR_UNDERLINE + getTableNameBySql(rowChange.getSql());
        baseRedisService.renameKey(oldDbTableName, dbTableName);
    }

    @Override
    public void deleteDb(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        String sql = rowChange.getSql().toUpperCase();
        if (sql.contains(CancalRedisConstant.SQL_DORP) && sql.contains(CancalRedisConstant.SQL_DORP_DATABASE) && sql.startsWith(CancalRedisConstant.SQL_DORP_DATABASE)) {
            String dbTableName = getDbName(dbMsgVo.getSchemaName());
            baseRedisService.deleteByWildcardKey(dbTableName + "*");
        }
    }

    @Override
    public CorePage getData(CorePage corePage, String key) {
        return baseRedisService.getValueByKey(corePage, key);
    }


}
