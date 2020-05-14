package com.zhy.frame.pool.thread.vo;/**
 * 描述:
 * 包名:com.zhy.frame.pool.thread.vo
 * 版本信息: 版本1.0
 * 日期:2020/4/1
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/1 11:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UncaughtExceptionVo implements Serializable {
    private String msg;
    private String methodName;
    private List<String> parameter;
    private String clazz;
}
