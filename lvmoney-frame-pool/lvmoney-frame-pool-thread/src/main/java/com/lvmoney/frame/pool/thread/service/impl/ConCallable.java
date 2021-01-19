package com.lvmoney.frame.pool.thread.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.pool.thread.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/4/1
 * Copyright XXXXXX科技有限公司
 */

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/1 20:07
 */
public class ConCallable implements Callable {
    private List<String> list;

    @Override
    public Object call() throws Exception {
        List<String> listRe = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            //含有‘4599’的字符串都返回
            if (list.get(i).contains("4599")) {
                listRe.add(list.get(i));
            }
        }
        return listRe;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
