package com.zhy.frame.test.selenium.vo;/**
 * 描述:
 * 包名:com.zhy.frame.test.selenium.vo
 * 版本信息: 版本1.0
 * 日期:2020/3/24
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/24 17:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReporterVo implements Serializable {
    private String output;
    private String name;
    private String title;
    private String fileName;
}
