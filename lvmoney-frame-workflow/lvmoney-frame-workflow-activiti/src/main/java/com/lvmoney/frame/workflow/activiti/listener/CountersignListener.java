package com.lvmoney.frame.workflow.activiti.listener;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/23
 * Copyright 成都三合力通科技有限公司
 */


import org.activiti.engine.delegate.DelegateTask;

import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class CountersignListener {

    public void notify(DelegateTask delegateTask) {
        System.out.println("delegateTask.getEventName() = " + delegateTask.getEventName());

        //添加会签的人员，所有的都审批通过才可进入下一环节
        List<String> assigneeList = new ArrayList<String>();
        assigneeList.add("fgld");

        delegateTask.setVariable("assigneeList", assigneeList);
    }
}