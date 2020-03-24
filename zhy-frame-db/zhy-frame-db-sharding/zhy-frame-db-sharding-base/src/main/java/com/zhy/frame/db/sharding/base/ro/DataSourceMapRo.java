package com.zhy.frame.db.sharding.base.ro;/**
 * 描述:
 * 包名:com.zhy.frame.db.sharding.base.ro
 * 版本信息: 版本1.0
 * 日期:2020/3/18
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.sql.DataSource;
import java.io.Serializable;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/18 9:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataSourceMapRo implements Serializable {
    private Map<String, Map<String, DataSource>> dataSourceMap;
    private Long expired;
}
