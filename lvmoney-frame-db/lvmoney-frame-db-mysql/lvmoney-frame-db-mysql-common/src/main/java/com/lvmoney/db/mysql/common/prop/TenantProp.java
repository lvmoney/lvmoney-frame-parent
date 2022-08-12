package com.lvmoney.db.mysql.common.prop;/**
 * 描述:
 * 包名:com.lvmoney.db.mysql.common.prop
 * 版本信息: 版本1.0
 * 日期:2020/8/7
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/8/7 14:11
 */
@Component
@ConfigurationProperties(prefix = "frame.saas")
@Data
public class TenantProp {
    private String tenantId;
}
