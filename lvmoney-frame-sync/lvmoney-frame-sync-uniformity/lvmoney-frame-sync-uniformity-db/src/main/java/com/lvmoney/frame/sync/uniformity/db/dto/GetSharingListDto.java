package com.lvmoney.frame.sync.uniformity.db.dto;/**
 * 描述:
 * 包名:com.lvmoney.frame.sync.uniformity.db.dto
 * 版本信息: 版本1.0
 * 日期:2021/12/31
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
 * @version:v1.0 2021/12/31 12:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetSharingListDto implements Serializable {

    private static final long serialVersionUID = 8230691418319076067L;
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
     * 页码
     */
    private Integer pageNo;
    /**
     * 每页行数
     */
    private Integer pageSize;
    /**
     * 求和字段
     */
    private String countField;

    /**
     * 分类
     */
    private String classify;
    /**
     * 时间区间字段
     */
    private String dateField;

}
