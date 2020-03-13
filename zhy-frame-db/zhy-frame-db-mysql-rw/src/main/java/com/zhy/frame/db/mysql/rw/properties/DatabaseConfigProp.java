package com.zhy.frame.db.mysql.rw.properties;/**
 * 描述:
 * 包名:com.zhy.frame.db.mysql.rw.properties
 * 版本信息: 版本1.0
 * 日期:2019/9/6
 * Copyright 四川******科技有限公司
 */


import com.zhy.frame.db.mysql.rw.vo.DatabaseConfig;
import com.zhy.frame.db.mysql.rw.vo.FrameMasterSlaveRule;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/9/6 16:17
 */
@Data
@ConfigurationProperties(prefix = "frame.config")
@Component
public class DatabaseConfigProp {
    private List<DatabaseConfig> database;
    private FrameMasterSlaveRule masterSlaveRule;
}
