package com.lvmoney.frame.job.handler;/**
 * 描述:
 * 包名:com.lvmoney.xxljob.handler
 * 版本信息: 版本1.0
 * 日期:2019/10/25
 * Copyright XXXXXX科技有限公司
 */

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/10/25 17:52
 */
@JobHandler(value = "TestHandler")
@Component
public class TestHandler extends IJobHandler {

    @Override
    public ReturnT<String> execute(String param) throws Exception {
        System.out.println("执行了TestHandler一次");
        return SUCCESS;
    }
}
