package com.zhy.frame.db.mysql.rw.properties;/**
 * 描述:
 * 包名:com.zhy.frame.db.mysql.rw.properties
 * 版本信息: 版本1.0
 * 日期:2019/9/7
 * Copyright 四川******科技有限公司
 */


import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/9/7 13:25
 */
@Data
@ConfigurationProperties(prefix = "spring.datasource")
@Component
public class DruidConfigProp {
    private DruidDataSource druid;
}
