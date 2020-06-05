package com.zhy.frame.pool.thread.test;/**
 * 描述:
 * 包名:com.zhy.frame.pool.thread.test
 * 版本信息: 版本1.0
 * 日期:2020/5/23
 * Copyright XXXXXX科技有限公司
 */


import org.apache.tools.ant.taskdefs.optional.PropertyFile;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/23 15:47
 */
public class VolatileDemo {
    public static volatile boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
//        Thread thread = new Thread(() -> {
//            int i = 0;
//            while (!stop) {
//                i++;
//            }
//        }, "VolatileDemoThread");
//        thread.start();
//        System.out.println("start Thread");
//        Thread.sleep(6000);
//        stop = true;

        AtomicInteger atomicInteger = new AtomicInteger(-1);
        System.out.println(atomicInteger.addAndGet(2));
    }

}
