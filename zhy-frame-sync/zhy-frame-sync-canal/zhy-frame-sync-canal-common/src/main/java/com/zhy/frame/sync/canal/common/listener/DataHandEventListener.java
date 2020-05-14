package com.zhy.frame.sync.canal.common.listener;
/**
 * 描述:
 * 包名:com.zhy.common.config
 * 版本信息: 版本1.0
 * 日期:2019/2/28
 * Copyright xxxx科技有限公司
 */

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.zhy.frame.sync.canal.common.annotation.CanalEventListener;
import com.zhy.frame.sync.canal.common.annotation.ddl.*;
import com.zhy.frame.sync.canal.common.annotation.dml.DeleteRowListenPoint;
import com.zhy.frame.sync.canal.common.annotation.dml.InsertListenPoint;
import com.zhy.frame.sync.canal.common.annotation.dml.UpdateListenPoint;
import com.zhy.frame.sync.canal.common.service.CanalService;
import com.zhy.frame.sync.canal.common.vo.DbMsgVo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @describe：canal监听数据库的基本信息，注意这里暂未考虑分库分区的情况
 * @author: lvmoney/xxxx科技有限公司
 * @version:v1.0 2019/2/28 10:05
 */
@CanalEventListener
public class DataHandEventListener {
    @Autowired
    CanalService canalService;

    /**
     * @describe: 插入数据
     * @param: [dbMsgVo, rowChange]
     * @return: void
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/19 16:21
     */
    @InsertListenPoint
    public void onEventInsertData(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        canalService.save(dbMsgVo, rowChange);
    }

    /**
     * @describe: 更新数据
     * @param: [dbMsgVo, rowChange]
     * @return: void
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/19 16:21
     */
    @UpdateListenPoint
    public void onEventUpdateData(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        canalService.save(dbMsgVo, rowChange);
    }

    /**
     * @describe: 删除数据
     * @param: [dbMsgVo, rowChange]
     * @return: void
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/19 16:22
     */
    @DeleteRowListenPoint
    public void onEventDeleteData(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        canalService.deleteRow(dbMsgVo, rowChange);
    }

    /**
     * @describe: 创建表，redis中没必要做数据同步
     * @param: [rowChange]
     * @return: void
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/19 16:22
     */
    @CreateTableListenPoint
    public void onEventCreateTable(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        System.out.println("======================注解方式（创建表操作）==========================");
        System.out.println("use " + rowChange.getDdlSchemaName() + ";\n" + rowChange.getSql());
        System.out.println("\n======================================================");
    }

    /**
     * @describe: 删除表
     * @param: [dbMsgVo, rowChange]
     * @return: void
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/19 16:22
     */
    @DropTableListenPoint
    public void onEventDropTable(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        canalService.deleteTable(dbMsgVo);
    }

    /**
     * @describe: 清空表，操作redis的key直接清空所有数据
     * @param: [dbMsgVo, rowChange]
     * @return: void
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/19 16:22
     */
    @DropTableListenPoint
    public void onEventTruncateTable(DbMsgVo dbMsgVo) {
        canalService.deleteTable(dbMsgVo);
    }

    @AlertTableListenPoint
    public void onEventAlertTable(CanalEntry.RowChange rowChange) {
        System.out.println("======================注解方式（修改表信息操作）==========================");
        System.out.println("use " + rowChange.getDdlSchemaName() + ";\n" + rowChange.getSql());
        System.out.println("\n======================================================");
    }

    /**
     * @describe:创建索引，redis中没必要做操作
     * @param: [dbMsgVo, rowChange]
     * @return: void
     * @author： lvmoney /XXXXXX有限公司
     * 2019/7/19 16:36
     */
    @CreateIndexListenPoint
    public void onEventCreateIndex(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        System.out.println("======================注解方式（创建索引操作）==========================");
        System.out.println("use " + dbMsgVo.getSchemaName() + ";\n" + rowChange.getSql());
        System.out.println("\n======================================================");

    }


    @DeleteDbListenPoint
    public void onEventDeleteDb(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        canalService.deleteDb(dbMsgVo, rowChange);
    }


    @RenameTableListenPoint
    public void onEventRenameTable(DbMsgVo dbMsgVo, CanalEntry.RowChange rowChange) {
        canalService.renameTable(dbMsgVo, rowChange);

    }

}
