package com.zhy.frame.log.logback.common.vo;/**
 * 描述:
 * 包名:com.zhy.k8s.logback.vo
 * 版本信息: 版本1.0
 * 日期:2019/8/23
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/23 8:55
 */
@Data
public class LogVo implements Serializable {
    private static final long serialVersionUID = -2791969778307151010L;
    private String level;
    private String logger;
    private String thread;
    private String message;
    private Long timeStamp;
    private String systemName;
}
