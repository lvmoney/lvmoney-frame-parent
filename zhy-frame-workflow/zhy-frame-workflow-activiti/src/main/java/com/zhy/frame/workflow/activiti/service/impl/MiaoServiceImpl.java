package com.zhy.frame.workflow.activiti.service.impl;

import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.workflow.activiti.dao.UserMapper;
import com.zhy.frame.workflow.activiti.dao.VacationFormMapper;
import com.zhy.frame.workflow.activiti.entity.User;
import com.zhy.frame.workflow.activiti.entity.VacationForm;
import com.zhy.frame.workflow.activiti.service.MiaoService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */

@Service("miaoService")
public class MiaoServiceImpl implements MiaoService {
    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    HistoryService historyService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VacationFormMapper vacationFormMapper;


    @Override
    public VacationForm writeForm(String title, String content, String applicant) {
        VacationForm form = new VacationForm();
        String approver = "未知审批者";
        form.setTitle(title);
        form.setContent(content);
        form.setApplicant(applicant);
        form.setApprover(approver);
        vacationFormMapper.insert(form);

        Map<String, Object> variables = new HashMap<String, Object>(BaseConstant.MAP_DEFAULT_SIZE);
        variables.put("employee", form.getApplicant());
        //开始请假流程，使用formId作为流程的businessKey
        runtimeService.startProcessInstanceByKey("myProcess", form.getId().toString(), variables);
        return form;
    }


    @Override
    public void completeProcess(String formId, String operator, String input) {
        //根据businessKey找到当前任务节点
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(formId).singleResult();
        //设置输入参数，使流程自动流转到对应节点
        taskService.setVariable(task.getId(), "input", input);
        taskService.complete(task.getId());
        String apply = "apply";
        if (apply.equals(input)) {
            applyVacation(formId, operator);
        } else {
            giveupVacation(formId, operator);
        }
    }


    @Override
    public boolean giveupVacation(String formId, String operator) {
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(formId).singleResult();
        Map<String, Object> variables = new HashMap<String, Object>(BaseConstant.MAP_DEFAULT_SIZE);
        variables.put("employee", operator);
        //认领任务
        taskService.claim(task.getId(), operator);
        //完成任务
        taskService.complete(task.getId(), variables);
        return true;
    }


    @Override
    public boolean applyVacation(String formId, String operator) {
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(formId).singleResult();
        Map<String, Object> variables = new HashMap<String, Object>(BaseConstant.MAP_DEFAULT_SIZE);
        List<User> users = userMapper.selectAll();
        String managers = "";
        //获取所有具有审核权限的用户
        for (User user : users) {
            if (user.getType().equals(2)) {
                managers += user.getName() + ",";
            }
        }
        managers = managers.substring(0, managers.length() - 1);
        variables.put("employee", operator);
        variables.put("managers", managers);
        taskService.claim(task.getId(), operator);
        taskService.complete(task.getId(), variables);
        return true;
    }


    @Override
    public boolean approverVacation(String formId, String operator) {
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(formId).singleResult();
        taskService.claim(task.getId(), operator);
        taskService.complete(task.getId());
        //更新请假信息的审核人
        VacationForm form = vacationFormMapper.selectById(Integer.parseInt(formId));
        //vacationFormService.findOne(Integer.parseInt(formId));
        if (form != null) {
            form.setApprover(operator);
            vacationFormMapper.insert(form);
        }
        return true;
    }


    @Override
    public HashMap<String, String> getCurrentState(String formId) {
        HashMap<String, String> map = new HashMap<String, String>(BaseConstant.MAP_DEFAULT_SIZE);
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(formId).singleResult();
        if (task != null) {
            map.put("status", "processing");
            map.put("taskId", task.getId());
            map.put("taskName", task.getName());
            map.put("user", task.getAssignee());
        } else {
            map.put("status", "finish");
        }
        return map;
    }


    @Override
    public List<VacationForm> formList() {
        List<VacationForm> formList = vacationFormMapper.selectAll();
        for (VacationForm form : formList) {
            Task task = taskService.createTaskQuery().processInstanceBusinessKey(form.getId().toString())
                    .singleResult();
            if (task != null) {
                String state = task.getName();
                form.setState(state);
            } else {
                form.setState("已结束");
            }
        }
        return formList;
    }


    @Override
    public User loginSuccess(String username) {
        List<User> users = userMapper.selectByName(username);
        if (users != null && users.size() > 0) {
            User user = users.get(0);
            return user;
        }
        return null;
    }

    /**
     * @describe: 获取当前登录用户
     * @param: [request]
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:50
     */
    public String getCurrentUser(HttpServletRequest request) {
        String user = "";
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userInfo".equals(cookie.getName())) {
                    user = cookie.getValue();
                }
            }
        }
        return user;
    }


    @Override
    public List historyState(String formId) {
        List<HashMap<String, String>> processList = new ArrayList<HashMap<String, String>>();
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .processInstanceBusinessKey(formId).list();
        if (list != null && list.size() > 0) {
            for (HistoricTaskInstance hti : list) {
                HashMap<String, String> map = new HashMap<String, String>(BaseConstant.MAP_DEFAULT_SIZE);
                map.put("name", hti.getName());
                map.put("operator", hti.getAssignee());
                processList.add(map);
            }
        }
        return processList;
    }
}
