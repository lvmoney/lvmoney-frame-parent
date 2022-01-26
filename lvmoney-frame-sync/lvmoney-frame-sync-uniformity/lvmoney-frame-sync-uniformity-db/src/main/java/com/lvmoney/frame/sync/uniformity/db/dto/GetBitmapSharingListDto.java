package com.lvmoney.frame.sync.uniformity.db.dto;/**
 * 描述:
 * 包名:com.lvmoney.frame.sync.uniformity.db.dto
 * 版本信息: 版本1.0
 * 日期:2022/1/7
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
 * @version:v1.0 2022/1/7 17:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetBitmapSharingListDto implements Serializable {

    private static final long serialVersionUID = 5224181074050474261L;
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
     * 求和字段
     */
    private String countField;

    /**
     * 分类
     */
    private String classify;

    /**
     * 需要查询的id 列表
     */
    private List<Long> selectedId;
}
