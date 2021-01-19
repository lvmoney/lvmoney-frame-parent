package com.lvmoney.frame.cloud.common.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.cloud.common.vo
 * 版本信息: 版本1.0
 * 日期:2020/3/7
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/7 22:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizedVo implements Serializable {
    private Set<String> sysId;
}
