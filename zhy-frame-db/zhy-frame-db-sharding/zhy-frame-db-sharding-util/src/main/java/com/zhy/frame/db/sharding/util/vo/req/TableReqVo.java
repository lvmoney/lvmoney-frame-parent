package com.zhy.frame.db.sharding.util.vo.req;/**
 * 描述:
 * 包名:com.zhy.mysql.subdb.vo.req
 * 版本信息: 版本1.0
 * 日期:2019/11/15
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/15 15:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableReqVo extends CommonReqVo {
    private static final long serialVersionUID = 6561213948912518069L;
    /**
     * 表名
     */
    private String tableName;
}
