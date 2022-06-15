package com.lvmoney.frame.ai.jpython.test;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.jpython.test
 * 版本信息: 版本1.0
 * 日期:2022/5/9
 * Copyright XXXXXX科技有限公司
 */


import org.python.util.PythonInterpreter;

import java.io.IOException;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/9 17:20
 */
public class PythonTest {
    public static void main(String[] args) {
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.exec("a='hello world'; ");
        interpreter.exec("print a;");
    }
}
