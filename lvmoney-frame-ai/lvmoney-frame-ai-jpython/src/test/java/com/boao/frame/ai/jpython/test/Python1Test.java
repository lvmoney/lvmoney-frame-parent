package com.lvmoney.frame.ai.jpython.test;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.jpython.test
 * 版本信息: 版本1.0
 * 日期:2022/5/9
 * Copyright XXXXXX科技有限公司
 */


import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/9 17:46
 */
public class Python1Test {
    public static void main(String[] args) {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("D:\\temp\\hello.py");
    }
}
