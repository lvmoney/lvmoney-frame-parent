package com.lvmoney.frame.workflow.activiti.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * @describe：请假单信息表
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
@TableName("vacation_form")
public class VacationForm {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    private String content;

    /**
     * 申请者
     */
    private String applicant;

    /**
     * 审批者
     */
    private String approver;

    /**
     * 申请所处状态
     */
    private String state;

    public VacationForm() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}