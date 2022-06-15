package com.lvmoney.frame.ai.jpython.test;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.jpython.test
 * 版本信息: 版本1.0
 * 日期:2022/5/13
 * Copyright XXXXXX科技有限公司
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/13 14:17
 */
public class Python3Test {
    public static void main(String[] args) {
        String inputKey = "smartData_IsolationForest:_input_demo-istio_train";
        String fieldKey = "smartData_IsolationForest:_field_demo-istio";
        String paramKey = "smartData_IsolationForest:_param_demo-istio";
        String predictId = "10000";
        try {
            String[] args1 = new String[]{"python",
                    "D:\\work\\code\\lvmoney-platform\\lvmoney-platform-smart\\lvmoney-platform-smart-python\\src\\main\\resources\\isolationForest.py",
                    inputKey,
                    fieldKey,
                    paramKey,
                    predictId
            };
            Process proc = Runtime.getRuntime().exec(args1);

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
