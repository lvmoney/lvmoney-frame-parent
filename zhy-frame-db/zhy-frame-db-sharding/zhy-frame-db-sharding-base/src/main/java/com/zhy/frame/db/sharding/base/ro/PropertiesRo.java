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

import java.io.Serializable;
import java.util.Map;
import java.util.Properties;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/18 9:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropertiesRo implements Serializable {
    private Map<String, Properties> properties;
    private Long expired;
}
