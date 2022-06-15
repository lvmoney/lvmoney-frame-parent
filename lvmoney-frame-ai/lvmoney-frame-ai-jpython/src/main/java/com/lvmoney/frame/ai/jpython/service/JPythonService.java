package com.lvmoney.frame.ai.jpython.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.jpython.service
 * 版本信息: 版本1.0
 * 日期:2022/5/9
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.ai.jpython.dto.ExecClazzDto;
import com.lvmoney.frame.ai.jpython.dto.ExecFuncDto;
import com.lvmoney.frame.ai.jpython.dto.ExecPyDto;
import com.lvmoney.frame.ai.jpython.dto.ExecStrDto;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

/**
 * @describe：使用jpython，不支持依赖的三方库，用处不大了
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/9 17:16
 */
public interface JPythonService {

    /**
     * 执行指定路径的python脚本没有python 的入参和出参
     *
     * @param pythonExecDto:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/9 17:33
     */
    void execPy(ExecPyDto pythonExecDto);

    /**
     * 执行指定代码
     *
     * @param execStrDto:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/9 17:56
     */
    void execStr(ExecStrDto execStrDto);

    /**
     * 调用python函数，python函数入参只支持只支持string，long，integer，boolean，float类型
     *
     * @param execFuncDto:
     * @throws
     * @return: org.python.core.PyObject
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/10 9:10
     */
    PyObject execFunc(ExecFuncDto execFuncDto);


    /**
     * 调用python函数(面向对象)，python函数入参只支持只支持string，long，int，double，float类型     * @param execClazzDto:
     *
     * @throws
     * @return: org.python.core.PyObject
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/10 10:43
     */
    PyObject execClazz(ExecClazzDto execClazzDto);

    /**
     * 关闭打开的python执行程序
     *
     * @param interp:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/5/9 17:36
     */
    default void close(PythonInterpreter interp) {
        interp.cleanup();
        interp.close();
    }
}
