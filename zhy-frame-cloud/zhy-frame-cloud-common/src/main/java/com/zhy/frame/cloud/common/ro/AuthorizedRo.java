package com.zhy.frame.cloud.common.ro;/**
 * 描述:
 * 包名:com.zhy.frame.cloud.common.ro
 * 版本信息: 版本1.0
 * 日期:2020/3/7
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/7 22:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizedRo implements Serializable {
    private Long expired;
    private Map<String, Set<String>> data;
}
