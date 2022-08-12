package com.lvmoney.frame.log.logback.common.prop;/**
 * 描述:
 * 包名:com.lvmoney.frame.log.logback.common.prop
 * 版本信息: 版本1.0
 * 日期:2020/10/16
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/10/16 15:08
 */
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogbackProp implements Serializable {
    private static final long serialVersionUID = -4310160741315160971L;
    @Value("${frame.logback.support:sys_log}")
    private String support;
}
