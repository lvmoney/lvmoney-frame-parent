package com.zhy.frame.workflow.activiti.service;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/23
 * Copyright xxxx科技有限公司
 */


import com.zhy.frame.workflow.activiti.vo.*;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public interface BaseActivitiService {
    /**
     * 通过bpmnid开始流程，bpmnId 流程图id，formId用来记录流程详情表id，variables流程参数
     *
     * @param startProcessVo: 开始流程实体
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:24
     */
    void startProcessInstanceByKey(StartProcessVo startProcessVo);

    /**
     * 通过formId获得task
     *
     * @param queryTaskVo: 请求task实体
     * @return: java.util.List<org.activiti.engine.task.Task> 任务list
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:24
     */
    List<Task> findTasksByFormId(QueryTaskVo queryTaskVo);

    /**
     * 通过bpmnId和userId查询task
     *
     * @param queryTaskVo: 请求task实体
     * @return: java.util.List<org.activiti.engine.task.Task> 任务list
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:25
     */
    List<Task> findTasksByUserId(QueryTaskVo queryTaskVo);

    /**
     * 通过bpmnid查询task
     *
     * @param queryTaskVo: 任务查询实体
     * @return: java.util.List<org.activiti.engine.task.Task>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:26
     */
    List<Task> findTasksByKey(QueryTaskVo queryTaskVo);

    /**
     * 通过taskId获得任务
     *
     * @param queryTaskVo: 任务查询实体
     * @return: org.activiti.engine.task.Task
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:27
     */
    Task findTaskByTaskId(QueryTaskVo queryTaskVo);

    /**
     * 通过taskId完成任务并把任务claim给userId
     *
     * @param completeTaskVo: 完成任务的实体
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:27
     */
    void completeTaskByTaskId(CompleteTaskVo completeTaskVo);

    /**
     * 通过formId获得历史记录
     *
     * @param historyVo: 历史记录实体
     * @return: java.util.List<com.zhy.activiti.vo.HistoryVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:28
     */
    List<HistoryVo> findHistoryByFormId(HistoryVo historyVo);

    /**
     * 通过bpmnId获得历史记录
     *
     * @param historyVo: 历史记录实体
     * @return: java.util.List<com.zhy.activiti.vo.HistoryVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:28
     */
    List<HistoryVo> findHistoryByKey(HistoryVo historyVo);

    /**
     * 根据formId获得流程图二进制
     *
     * @param imgVo: 工作流图片实体
     * @return: com.zhy.activiti.vo.ImgVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:28
     */
    ImgVo getProcessImgByFormId(ImgVo imgVo);

    /**
     * 根据bpmnId获得流程图二进制
     *
     * @param imgVo: 工作流图片实体
     * @return: com.zhy.activiti.vo.ImgVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:29
     */
    ImgVo getProcessImgByKey(ImgVo imgVo);

    /**
     * 任务进行委派
     * 委派：是将任务节点分给其他人处理，等其他人处理好之后，委派任务会自动回到委派人的任务中
     *
     * @param delegateVo: 委派实体
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:29
     */
    boolean delegateTask(DelegateVo delegateVo);

    /**
     * 被委派人办理任务后
     *
     * @param delegateVo: 委派实体
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:30
     */
    boolean resolveTask(DelegateVo delegateVo);

    /**
     * @describe: 转办
     * 直接将办理人assignee 换成别人，这时任务的拥有着不再是转办人，而是为空，相当与将任务转出
     * @param: [assigneeVo]
     * @return: boolean
     * @author： lvmoney /xxxx科技有限公司
     * 2019/1/31
     */
    /**
     * 转办
     * 直接将办理人assignee 换成别人，这时任务的拥有着不再是转办人，而是为空，相当与将任务转出
     *
     * @param assigneeVo: 转办实体
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:30
     */
    boolean assigneeTask(AssigneeVo assigneeVo);

    /**
     * 通过当前任务taskId，把任务跳转到指定的节点id->returnId,并记录跳转原因reason
     * reason是一个实体，type表示类型撤回或者驳回在ActivitiConstant有默认值选择，reason为描述
     * 即处理人小王完成环节A的任务(id=6000)后，流程走到下一环节B生成任务(id=6004)，
     * 任务(id=6004)处理人小张审核不通过执行驳回，
     * 流程流转回环节A，环节A重新生成一条id=6000的待处理人为小王的任务。
     *
     * @param jumpVo: 跳转实体
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/9/9 19:31
     */
    void jump2NodeId(JumpVo jumpVo);

}
