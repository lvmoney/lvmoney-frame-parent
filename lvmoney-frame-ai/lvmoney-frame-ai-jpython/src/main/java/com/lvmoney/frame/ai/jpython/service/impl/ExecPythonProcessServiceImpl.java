package com.lvmoney.frame.ai.jpython.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.jpython.service.impl
 * 版本信息: 版本1.0
 * 日期:2022/5/13
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSONObject;
import com.lvmoney.frame.ai.jpython.dto.ExecProcessDto;
import com.lvmoney.frame.ai.jpython.exception.JPythonException;
import com.lvmoney.frame.ai.jpython.service.ExecPythonProcessService;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.base.core.util.ArrayUtil;
import com.lvmoney.frame.base.core.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static com.lvmoney.frame.ai.jpython.constant.PythonConstant.LANGUAGE_PYTHON;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/13 16:24
 */
@Service
public class ExecPythonProcessServiceImpl implements ExecPythonProcessService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExecPythonProcessServiceImpl.class);

    @Override
    public JSONObject execProcess(ExecProcessDto execProcessDto) {
        try {
            String[] config = new String[]{LANGUAGE_PYTHON, execProcessDto.getPythonPath()};
            String[] input = execProcessDto.getInput().toArray(new String[execProcessDto.getInput().size()]);
            String[] args = ArrayUtil.merge(config, input);
            Process proc = Runtime.getRuntime().exec(args);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            StringBuffer result = new StringBuffer();
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            in.close();
            int i = proc.waitFor();
            if (1 == i) {
                LOGGER.error("java调用python脚本:{},参数:{},python脚本执行报错报错", execProcessDto.getPythonPath(), JsonUtil.t2JsonString(args));
                throw new BusinessException(JPythonException.Proxy.EXEC_PYTHON_ERROR);
            } else {
                String temp = result.toString().replaceAll(BaseConstant.SINGLE_QUOTATION_MARK, BaseConstant.DOUBLE_QUOTATION_MARKS);
                return JSONObject.parseObject(temp);
            }
        } catch (IOException e) {
            LOGGER.error("java调用python脚本io报错:{}", e);
            throw new BusinessException(JPythonException.Proxy.EXEC_PYTHON_PROCESS_ERROR);

        } catch (InterruptedException e) {
            LOGGER.error("java调用python脚本报错:{}", e);
            throw new BusinessException(JPythonException.Proxy.EXEC_PYTHON_PROCESS_IO_ERROR);
        }
    }
}
