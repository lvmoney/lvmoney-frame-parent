package com.lvmoney.frame.ai.jpython.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.jpython.service
 * 版本信息: 版本1.0
 * 日期:2022/5/9
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.ai.jpython.constant.PythonConstant;
import com.lvmoney.frame.ai.jpython.dto.*;
import com.lvmoney.frame.ai.jpython.service.JPythonService;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.base.core.util.JsonUtil;
import org.python.core.Py;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/9 17:16
 */
@Service
public class JPythonServiceImpl implements JPythonService {
    @Value("${python.home:/home}")
    private String pythonHome;

    @Override
    public void execPy(ExecPyDto pythonExecDto) {
        propertiesInit();
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile(pythonExecDto.getPythonPath());
        close(interpreter);
    }

    @Override
    public void execStr(ExecStrDto execStrDto) {
        propertiesInit();
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec(execStrDto.getPythonCode());
        close(interpreter);
    }

    @Override
    public PyObject execFunc(ExecFuncDto execFuncDto) {
        propertiesInit();
        PythonInterpreter interpreter = new PythonInterpreter();
        // 加载python程序
        interpreter.execfile(execFuncDto.getPythonPath());
        // 调用Python程序中的函数
        PyFunction pyf = interpreter.get(execFuncDto.getFunc(), PyFunction.class);
        PyObject dddRes = pyf.__call__(buildPyObject(execFuncDto.getParam()));
        close(interpreter);
        return dddRes;
    }

    @Override
    public PyObject execClazz(ExecClazzDto execClazzDto) {
        // python对象名
        String pythonObjName = execClazzDto.getObj();
        // python类名
        String pythonClazzName = execClazzDto.getClazz();
        propertiesInit();
        PythonInterpreter interpreter = new PythonInterpreter();
        // 加载python程序
        interpreter.execfile(execClazzDto.getPythonPath());
        // 实例化python对象
        interpreter.exec(pythonObjName + BaseConstant.CHAR_EQUAL_SIGN + pythonClazzName + BaseConstant.BRACKETS_LEFT + BaseConstant.BRACKETS_RIGHT);
        // 获取实例化的python对象
        PyObject pyObj = interpreter.get(pythonObjName);
        // 调用python对象方法,传递参数并接收返回值
        PyObject result = pyObj.invoke(execClazzDto.getFunc(), buildPyObject(execClazzDto.getParam()));
        close(interpreter);
        return result;
    }

    /**
     * 根据list构造python 函数的入参
     *
     * @param param:
     * @throws
     * @return: org.python.core.PyObject[]
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/10 9:21
     */
    private PyObject[] buildPyObject(List<PythonParamDto> param) {
        List<PyObject> temp = new ArrayList();
        param.stream().forEach(e -> {
            String k = e.getType();
            String v = e.getValue() + BaseConstant.EMPTY;
            if (PythonConstant.TYPE_FLOAT.equals(k)) {
                temp.add(Py.newFloat(Float.parseFloat(v)));
            } else if (PythonConstant.TYPE_LONG.equals(k)) {
                temp.add(Py.newLong(v));
            } else if (PythonConstant.TYPE_BOOLEAN.equals(k)) {
                temp.add(Py.newBoolean(Boolean.parseBoolean(v)));
            } else if (PythonConstant.TYPE_INTEGER.equals(k)) {
                temp.add(Py.newInteger(Integer.parseInt(v)));
            } else {
                temp.add(Py.newString(v));
            }
        });

        PyObject[] result = temp.stream().toArray(PyObject[]::new);
        return result;
    }

    public static void main(String[] args) {
        List<PythonParamDto> test = new ArrayList();
        test.add(new PythonParamDto("float", 1.0f));
        test.add(new PythonParamDto("float", 3.0f));
        System.out.println(JsonUtil.t2JsonString(test));
    }

    /**
     * 初始化python配置
     *
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/13 13:47
     */
    private void propertiesInit() {
        //初始化site 配置
        Properties props = new Properties();
        props.put("python.home", pythonHome);
        props.put("python.console.encoding", "UTF-8");
        props.put("python.security.respectJavaAccessibility", "false");
        props.put("python.import.site", "false");
        Properties preprops = System.getProperties();
        PythonInterpreter.initialize(preprops, props, new String[0]);
    }
}
