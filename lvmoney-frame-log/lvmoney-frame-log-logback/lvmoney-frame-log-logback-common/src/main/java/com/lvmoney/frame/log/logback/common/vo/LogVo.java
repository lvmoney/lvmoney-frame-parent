package com.lvmoney.frame.log.logback.common.vo;/**
 * 描述:
 * 包名:com.lvmoney.k8s.logback.vo
 * 版本信息: 版本1.0
 * 日期:2019/8/23
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/23 8:55
 */
@Data
public class LogVo implements Serializable {
    private static final long serialVersionUID = -2791969778307151010L;
    /**
     * id
     */
    private String id;
    /**
     * 系统名称
     */
    private String sysName;
    /**
     * 日志类型
     */
    private String level;
    /**
     * 信息
     */
    private String msg;

    /**
     * 线程
     */
    private String thread;

    /**
     * 执行时间
     */
    private LocalDateTime exeDate;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;
}
