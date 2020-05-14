package com.zhy.frame.workflow.activiti.vo;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/31
 * Copyright xxxx科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
public class AssigneeVo implements Serializable {
    private String taskId;
    private String userId;
}
