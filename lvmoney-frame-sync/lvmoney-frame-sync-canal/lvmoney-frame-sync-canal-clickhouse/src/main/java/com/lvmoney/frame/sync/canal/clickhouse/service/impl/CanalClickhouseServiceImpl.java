package com.lvmoney.frame.sync.canal.clickhouse.service.impl;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.base.core.vo.CorePage;
import com.lvmoney.frame.cache.redis.service.BaseRedisService;
import com.lvmoney.frame.core.util.MapUtil;
import com.lvmoney.frame.sync.canal.common.service.impl.AbstractCanalService;
import com.lvmoney.frame.sync.canal.common.vo.DbMsgVo;
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
public class CanalClickhouseServiceImpl extends AbstractCanalService {
    @Override
    public void save(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
        for (CanalEntry.RowData rowData : rowDatasList) {
            Map<String, Object> map = new HashMap<>(MapUtil.initMapSize(rowData.getAfterColumnsList().size()));
            AtomicReference<String> key = new AtomicReference<>("nul");
            rowData.getAfterColumnsList().forEach((c) -> {
                if (c.getIsKey()) {
                    key.set(c.getValue());
                }
                map.put(c.getName(), c.getValue());
            });
        }
    }


    @Override
    public void deleteRow(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {

    }

    @Override
    public void deleteTable(DbMsgVo dbMsgVo) {
        String dbTableName = getDbTableName(dbMsgVo);
    }

    @Override
    public void renameTable(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        String dbTableName = getDbTableName(dbMsgVo);
        String oldDbTableName = dbMsgVo.getSchemaName().toUpperCase() + BaseConstant.CONNECTOR_UNDERLINE + getTableNameBySql(rowChange.getSql());
    }

    @Override
    public void deleteDb(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        String sql = rowChange.getSql().toUpperCase();
    }

}
