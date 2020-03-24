package com.zhy.frame.db.sharding.util.vo.req;/**
 * 描述:
 * 包名:com.zhy.mysql.subdb.vo.req
 * 版本信息: 版本1.0
 * 日期:2019/11/15
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/15 15:28
 */
@Data
public class BatchTableReqVo extends CommonReqVo {
    private static final long serialVersionUID = -3377596991414523079L;
    /**
     * 表的数量，根据租户的不同可以把数据库表的数量设置变大，例如：为租户lvmoney，创建5个表
     */
    private Integer tableNum;
    /**
     * 租户编号
     */
    private String code;
    /**
     * 是否是某个租户独有，需要结合code使用
     */
    private boolean unique;

    /**
     * 表名
     */
    private String tableName;


    /**
     * 虚拟节点数
     */
    private Integer virtualNode;

    /**
     * 真实服务器列表
     */
    private List<String> server;
}
