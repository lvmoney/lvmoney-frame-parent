package com.lvmoney.frame.workflow.activiti.service;


import com.lvmoney.frame.workflow.activiti.entity.User;
import com.lvmoney.frame.workflow.activiti.entity.VacationForm;

import java.util.HashMap;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */

public interface MiaoService {
    /**
     * @describe: 填写请假信息
     * @param: [title, content, applicant]
     * @return: com.lvmoney.activiti.entity.VacationForm
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:22
     */
    public VacationForm writeForm(String title, String content, String applicant);

    /**
     * @describe: 放弃请假
     * @param: [formId, operator]
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:23
     */
    public boolean giveupVacation(String formId, String operator);

    /**
     * @describe: 任务申请
     * @param: [formId, operator]
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:23
     */
    public boolean applyVacation(String formId, String operator);

    /**
     * @describe:更新请假信息的审核人
     * @param: [formId, operator]
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:24
     */
    public boolean approverVacation(String formId, String operator);

    /**
     * @describe: 根据选择，申请或放弃请假
     * @param: [formId, operator, input]
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:23
     */
    public void completeProcess(String formId, String operator, String input);

    /**
     * @describe: 获取请假信息的当前流程状态
     * @param: [formId]
     * @return: java.util.HashMap<java.lang.String, java.lang.String>
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:25
     */
    public HashMap<String, String> getCurrentState(String formId);

    /**
     * @describe: 请假列表
     * @param: []
     * @return: java.util.List<com.lvmoney.activiti.entity.VacationForm>
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:25
     */
    public List<VacationForm> formList();

    /**
     * @describe: 登录验证用户名是否存在
     * @param: [username]
     * @return: com.lvmoney.activiti.entity.User
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:25
     */
    public User loginSuccess(String user);

    /**
     * @describe: 获取已执行的流程信息
     * @param: [formId]
     * @return: java.util.List
     * @author: lvmoney /XXXXXX科技有限公司
     * 2019/9/9 10:26
     */
    public List historyState(String formId);
}
