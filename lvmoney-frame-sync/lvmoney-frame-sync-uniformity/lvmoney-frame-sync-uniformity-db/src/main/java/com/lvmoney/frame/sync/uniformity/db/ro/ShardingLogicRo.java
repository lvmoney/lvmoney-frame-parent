package com.lvmoney.frame.sync.uniformity.db.ro;/**
 * 描述:
 * 包名:com.lvmoney.frame.sync.uniformity.db.ro
 * 版本信息: 版本1.0
 * 日期:2022/1/4
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/1/4 15:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShardingLogicRo implements Serializable {

    private static final long serialVersionUID = 5040173738021604562L;
    /**
     * 表用到的sharding 策略
     */
    private String tableLogicTable;
    /**
     * 需要查询的表
     */
    private List<String> selectedTable;
    /**
     * 库用到的sharding 策略
     */
    private String dbLogicTable;
    /**
     * 需要查询的库
     */
    private List<String> selectedDb;

    /**
     * 数据库的字段
     */
    private List<String> selectField;

    /**
     * 删除标记字段
     */
    private String deletedField;

    /**
     * 排序字段
     */
    private String orderByField;
    /**
     * desc sec
     */
    private String sort;
    /**
     * where
     */
    private String where;
    /**
     * 求和字段
     */
    private String countField;

    /**
     * 是否同步
     */
    private Boolean sync;

    /**
     * 分类
     */
    private String classify;

    /**
     * 时间区间字段
     */
    private String dateField;

}
