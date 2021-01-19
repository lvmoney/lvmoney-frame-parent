package com.lvmoney.frame.workflow.activiti.vo;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/29
 * Copyright 成都三合力通科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
public class QueryTaskVo implements Serializable {
    private String userId;
    /**
     * 流程id
     */
    private String bpmnId;
    /**
     * 自建数据库中的流程记录表id
     */
    private String formId;

    private String taskId;
}
