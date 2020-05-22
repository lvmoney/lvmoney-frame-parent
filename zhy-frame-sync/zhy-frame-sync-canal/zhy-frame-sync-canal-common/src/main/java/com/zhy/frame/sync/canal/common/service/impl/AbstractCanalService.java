package com.zhy.frame.sync.canal.common.service.impl;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.core.vo.Page;
import com.zhy.frame.sync.canal.common.service.CanalService;
import com.zhy.frame.sync.canal.common.vo.DbMsgVo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public abstract class AbstractCanalService implements CanalService {

    @Override
    public void save(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
    }


    @Override
    public void deleteRow(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {

    }

    @Override
    public void deleteTable(DbMsgVo dbMsgVo) {
    }

    @Override
    public void renameTable(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
    }

    @Override
    public void deleteDb(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
    }

    @Override
    public Page getData(Page page, String key) {
        return null;
    }

    /**
     * @describe:获得对应的数据库_表的名称，并大写处理
     * @param: [dbMsgVo]
     * @return: java.lang.String
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/18 17:37
     */
    public String getDbTableName(DbMsgVo dbMsgVo) {
        return dbMsgVo.getSchemaName().toUpperCase() + BaseConstant.CONNECTOR_UNDERLINE + dbMsgVo.getTableName().toUpperCase();
    }

    /**
     * @describe:仅适合类似sql:RENAME TABLE `product2` TO `product`
     * 获得更新前的表名,实例中就是:product2
     * @param: [sql]
     * @return: java.lang.String
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/19 17:52
     */
    public String getTableNameBySql(String sql) {
        String step1 = sql.substring(sql.indexOf("`") + 1);
        String step2 = step1.substring(0, step1.indexOf("`"));
        return step2.toUpperCase();
    }

    /**
     * @describe:清除数据库名称外面的`
     * @param: [sql]
     * @return: java.lang.String
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/19 22:12
     */
    public String getDbName(String sql) {
        return sql.replaceAll("`", "").toUpperCase();
    }
}
