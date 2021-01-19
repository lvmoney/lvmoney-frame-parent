package com.lvmoney.frame.log.server.vo;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/31
 * Copyright 四川******科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogVo implements Serializable {
    private static final long serialVersionUID = 2474100062128168336L;
    /**
     * 操作IP
     */
    private String requestIp;
    /**
     * 操作类型 1 操作记录 2异常记录
     */
    private String logType;
    /**
     * 操作人ID
     */
    private String username;
    /**
     *
     */
    private String userId;
    /**
     * 操作描述
     */
    private String description;
    /**
     * 操作时间
     */
    private Long actionDate;
    /**
     * 异常code
     */
    private Integer exceptionCode;
    /**
     * 异常详情
     */
    private String exceptionDetail;
    /**
     * 请求方法
     */
    private String actionMethod;
    /**
     * 请求参数
     */
    private String params;
    /**
     * 操作人员token
     */
    private String token;
}
