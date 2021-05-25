package com.lvmoney.frame.workflow.activiti.delegate;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/1/31
 * Copyright XXXXXX科技有限公司
 */


import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */


public class ServiceTask implements JavaDelegate {

//流程变量

    private Expression text1;


//重写委托的提交方法

    @Override

    public void execute(DelegateExecution execution) {

        System.out.println("serviceTask已经执行已经执行！");

        String value1 = (String) text1.getValue(execution);

        System.out.println(value1);

        execution.setVariable("var1", new StringBuffer(value1).reverse().toString());

    }


}
