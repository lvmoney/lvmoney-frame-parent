package com.zhy.frame.workflow.activiti.vo;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/29
 * Copyright xxxx科技有限公司
 */


import lombok.Data;
import org.activiti.engine.history.HistoricTaskInstance;

import java.util.Date;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
public class HistoryVo implements HistoricTaskInstance {
    private String formId;
    private String bpmnId;
    private String name;
    private String assignee;
    private String deleteReason;
    private Date startTime;
    private Long durationInMillis;
    private Date endTime;
    private Long workTimeInMillis;
    private String id;
    private String description;
    private int priority;
    private String owner;
    private String processInstanceId;
    private String executionId;
    private String processDefinitionId;
    private Date createTime;
    private Date dueDate;
    private String category;
    private String parentTaskId;
    private String tenantId;
    private String formKey;
    private Map<String, Object> taskLocalVariables;
    private Map<String, Object> processVariables;
    private Date claimTime;
    private String taskDefinitionKey;
    private Date time;

}
