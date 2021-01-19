package com.lvmoney.frame.workflow.activiti.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/23
 * Copyright 成都三合力通科技有限公司
 */


import com.lvmoney.frame.base.core.util.JsonUtil;
import com.lvmoney.frame.workflow.activiti.service.BaseActivitiService;
import com.lvmoney.frame.workflow.activiti.vo.*;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.Process;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.task.Task;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Service
public class BaseActivitiServiceImpl implements BaseActivitiService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseActivitiServiceImpl.class);

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    HistoryService historyService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private TaskService taskService;
    @Autowired
    ManagementService managementService;
    @Autowired
    private ProcessEngineConfigurationImpl processEngineConfiguration;

    @Override
    public void startProcessInstanceByKey(StartProcessVo startProcessVo) {
        runtimeService.startProcessInstanceByKey(startProcessVo.getBpmnId(), startProcessVo.getFormId(), startProcessVo.getVariables());
    }

    @Override
    public List<Task> findTasksByFormId(QueryTaskVo queryTaskVo) {
        return taskService.createTaskQuery().processInstanceBusinessKey(queryTaskVo.getFormId()).list();
    }

    @Override
    public List<Task> findTasksByUserId(QueryTaskVo queryTaskVo) {
        return taskService.createTaskQuery().processDefinitionKey(queryTaskVo.getBpmnId()).taskCandidateOrAssigned(queryTaskVo.getUserId()).list();
    }

    @Override
    public List<Task> findTasksByKey(QueryTaskVo queryTaskVo) {
        return taskService.createTaskQuery().processDefinitionKey(queryTaskVo.getBpmnId()).list();
    }

    @Override
    public Task findTaskByTaskId(QueryTaskVo queryTaskVo) {
        return taskService.createTaskQuery()
                // 创建任务查询
                .taskId(queryTaskVo.getTaskId())
                // 根据任务id查询
                .singleResult();
    }


    @Override
    public void completeTaskByTaskId(CompleteTaskVo completeTaskVo) {
        String taskId = completeTaskVo.getTaskId();
        String userId = completeTaskVo.getUserId();
        taskService.claim(taskId, userId);
        Map<String, Object> vars = completeTaskVo.getVariables();
        taskService.complete(taskId, vars);
    }

    @Override
    public List<HistoryVo> findHistoryByFormId(HistoryVo historyVo) {
        String formId = historyVo.getFormId();
        List<HistoryVo> processList = new ArrayList<HistoryVo>();
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .processInstanceBusinessKey(formId).list();
        if (list != null && list.size() > 0) {
            for (HistoricTaskInstance hti : list) {
                HistoryVo hv = new HistoryVo();
                hv.setFormId(formId);
                BeanUtils.copyProperties(hti, hv);
                processList.add(hv);
            }
        }
        return processList;
    }


    @Override
    public List<HistoryVo> findHistoryByKey(HistoryVo historyVo) {
        String bpmnId = historyVo.getBpmnId();
        List<HistoryVo> processList = new ArrayList<HistoryVo>();
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .processDefinitionKey(bpmnId).list();
        if (list != null && list.size() > 0) {
            for (HistoricTaskInstance hti : list) {
                HistoryVo hv = new HistoryVo();
                hv.setFormId(hti.getProcessInstanceId());
                BeanUtils.copyProperties(hti, hv);
                processList.add(hv);
            }
        }
        return processList;
    }

    @Override
    public ImgVo getProcessImgByFormId(ImgVo imgVo) {
        String formId = imgVo.getFormId();
        QueryTaskVo queryTaskVo = new QueryTaskVo();
        queryTaskVo.setFormId(formId);
        List<Task> tasks = findTasksByFormId(queryTaskVo);
        String processInstanceId = tasks.get(0).getProcessInstanceId();
        HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        //根据流程定义获取输入流
        InputStream is = repositoryService.getProcessDiagram(processInstance.getProcessDefinitionId());
        try {
            imgVo.setFileByte(IOUtils.toByteArray(is));
//            File file = new File("F:\\sclt\\file\\demo2.png");
//            BufferedImage bi = ImageIO.read(is);
//            if(!file.exists()) file.createNewFile();
//            FileOutputStream fos = new FileOutputStream(file);
//            ImageIO.write(bi, "png", fos);
//            fos.close();
//            is.close();
//            System.out.println("图片生成成功");
        } catch (IOException e) {
            LOGGER.error("通过流程id获得流程图报错:{}", e);
        }
        return imgVo;
    }

    @Override
    public ImgVo getProcessImgByKey(ImgVo imgVo) {
        String bpmnId = imgVo.getBpmnId();
        HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processDefinitionKey(bpmnId).singleResult();
        //根据流程定义获取输入流
        InputStream is = repositoryService.getProcessDiagram(processInstance.getProcessDefinitionId());
        try {
            imgVo.setFileByte(IOUtils.toByteArray(is));
        } catch (IOException e) {
            LOGGER.error("根据流程id获得流程图报错:{}", e);
        }
        return imgVo;
    }


    @Override
    public boolean delegateTask(DelegateVo delegateVo) {
        taskService.delegateTask(delegateVo.getTaskId(), delegateVo.getUserId());
        return true;
    }

    @Override
    public boolean resolveTask(DelegateVo delegateVo) {
        taskService.resolveTask(delegateVo.getTaskId(), delegateVo.getVariables());
        return true;
    }

    @Override
    public boolean assigneeTask(AssigneeVo assigneeVo) {
        String taskId = assigneeVo.getTaskId();
        String userId = assigneeVo.getUserId();
        taskService.setAssignee(taskId, userId);
        taskService.setOwner(taskId, userId);
        return true;
    }

    @Override
    public void jump2NodeId(JumpVo jumpVo) {
        String taskId = jumpVo.getTaskId();
        //当前任务
        Task currentTask = taskService.createTaskQuery().taskId(taskId).singleResult();
        //获取流程定义
        Process process = repositoryService.getBpmnModel(currentTask.getProcessDefinitionId()).getMainProcess();
        //获取目标节点定义
        FlowNode targetNode = (FlowNode) process.getFlowElement(jumpVo.getReturnId());
        //删除当前运行任务
        String executionEntityId = managementService.executeCommand(new DeleteTaskCmd(currentTask.getId(), JsonUtil.t2JsonString(jumpVo.getReason())));
        //流程执行到来源节点
        managementService.executeCommand(new SetFlowNodeAndGoCmd(targetNode, executionEntityId));
    }
}
