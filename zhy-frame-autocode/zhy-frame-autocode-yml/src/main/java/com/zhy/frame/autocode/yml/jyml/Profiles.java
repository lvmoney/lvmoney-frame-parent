package com.zhy.frame.autocode.yml.jyml;/**
 * 描述:
 * 包名:com.zhy.frame.autocode.yml.vo
 * 版本信息: 版本1.0
 * 日期:2020/6/18
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/18 14:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profiles implements Serializable {
    /**
     * 激活分支
     */
    private String active;
}
