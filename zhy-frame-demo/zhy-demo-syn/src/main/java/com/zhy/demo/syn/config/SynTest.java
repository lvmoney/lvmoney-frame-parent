package com.zhy.demo.syn.config;/**
 * 描述:
 * 包名:com.zhy.demo.syn.handler
 * 版本信息: 版本1.0
 * 日期:2020/4/1
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/1 9:25
 */
@Service
public class SynTest {
    @Async
    public void exeAsynTask(Integer i) {
        System.out.println(new Date() + "执行任务：" + i);
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date() + "执行完毕：" + i);

    }

    @Async
    public void exeAsynTask2(Integer i) {
        System.out.println(new Date() + "执行任务2：" + i);
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date() + "执行完毕2：" + i);

    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ExecutorTaskConfig.class);
        SynTest synTest = context.getBean(SynTest.class);
        for (int i = 0; i < 5; i++) {
            synTest.exeAsynTask(i);
            synTest.exeAsynTask2(i);
        }
        context.close();
    }

}
