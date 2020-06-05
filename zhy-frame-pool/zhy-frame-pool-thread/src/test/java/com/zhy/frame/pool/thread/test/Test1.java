package com.zhy.frame.pool.thread.test;/**
 * 描述:
 * 包名:com.zhy.frame.pool.thread.test
 * 版本信息: 版本1.0
 * 日期:2020/5/26
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.util.JsonUtil;
import com.zhy.frame.core.util.FileUtil;
import com.zhy.frame.core.util.SplitFileUtil;
import com.zhy.frame.core.vo.SplitFileVo;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/26 20:37
 */
public class Test1 {
    public static void main(String[] args) {
        long starTime = System.currentTimeMillis();
        AtomicLong count = new AtomicLong(0);
        String file = "D:\\data\\temp\\test.txt";
        List<SplitFileVo> list = SplitFileUtil.getSplit(file, 4);
        System.out.println(JsonUtil.t2JsonString(list));
        list.parallelStream().forEach(e -> count.getAndAdd(FileUtil.readFile(file, e.getBegin(), e.getEnd())));
        System.out.println(count);
        long endTime = System.currentTimeMillis();
        long Time = endTime - starTime;
        System.out.println("执行时间:" + Time);
//        FileUtil.readFile(file, 0, 13);
    }
}
