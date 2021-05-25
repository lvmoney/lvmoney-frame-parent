package com.lvmoney.frame.workflow.activiti.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/31
 * Copyright XXXXXX科技有限公司
 */


import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;

import java.util.List;

/**
 * @describe：根据提供节点和执行对象id，进行跳转命令
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class SetFlowNodeAndGoCmd implements Command<Void> {
    private FlowNode flowElement;
    private String executionId;

    public SetFlowNodeAndGoCmd(FlowNode flowElement, String executionId) {
        this.flowElement = flowElement;
        this.executionId = executionId;
    }

    @Override
    public Void execute(CommandContext commandContext) {
        //获取目标节点的来源连线
        List<SequenceFlow> flows = flowElement.getIncomingFlows();
        if (flows == null || flows.size() < 1) {
            //throw new ActivitiException("回退错误，目标节点没有来源连线");
        }
        //随便选一条连线来执行，时当前执行计划为，从连线流转到目标节点，实现跳转
        ExecutionEntity executionEntity = commandContext.getExecutionEntityManager().findById(executionId);
        executionEntity.setCurrentFlowElement(flows.get(0));
        commandContext.getAgenda().planTakeOutgoingSequenceFlowsOperation(executionEntity, true);
        return null;
    }
}
