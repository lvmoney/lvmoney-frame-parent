package com.lvmoney.frame.ai.jpython.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.jpython.service
 * 版本信息: 版本1.0
 * 日期:2022/5/13
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSONObject;
import com.lvmoney.frame.ai.jpython.dto.ExecProcessDto;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/13 16:24
 */
public interface ExecPythonProcessService {
    /**
     * 执行 python脚本有入参和返回值
     *
     * @param execProcessDto:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/13 16:30
     */
    JSONObject execProcess(ExecProcessDto execProcessDto);
}
