package com.zhy.frame.db.sharding.util.vo.resp;/**
 * 描述:
 * 包名:com.zhy.mysql.subdb.util.vo.resp
 * 版本信息: 版本1.0
 * 日期:2020/1/6
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/1/6 16:45
 */
@Data
public class BatchTableRespVo implements Serializable {

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 真实的ip地址
     */
    private String ip;

    /**
     * 创建表的sql
     */
    private String createTableSql;

    /**
     * 数据库表列表
     */
    private List<String> tableName;

    /**
     * 创建数据库的sql
     */
    private String createDbSql;

}
