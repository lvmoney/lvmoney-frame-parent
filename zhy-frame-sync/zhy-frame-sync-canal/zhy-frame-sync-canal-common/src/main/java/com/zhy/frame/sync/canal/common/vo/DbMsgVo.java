package com.zhy.frame.sync.canal.common.vo;
/**
 * 描述:
 * 包名:com.zhy.common.handler
 * 版本信息: 版本1.0
 * 日期:2019/2/28
 * Copyright xxxx科技有限公司
 */

import lombok.Data;


/**
 * @describe：canal监听数据库的基本信息
 * @author: lvmoney/xxxx科技有限公司
 * @version:v1.0 2019/2/28 10:05
 */
@Data
public class DbMsgVo {
    /**
     * 指令
     */
    private String destination;
    /**
     * 数据库实例名称
     */
    private String schemaName;
    /**
     * 数据库表名称
     */
    private String tableName;

}
