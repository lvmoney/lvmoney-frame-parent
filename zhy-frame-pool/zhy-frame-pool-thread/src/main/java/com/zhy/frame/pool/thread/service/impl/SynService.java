package com.zhy.frame.pool.thread.service.impl;/**
 * 描述:
 * 包名:com.zhy.demo.syn.config
 * 版本信息: 版本1.0
 * 日期:2020/4/1
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.base.core.exception.CommonException;
import com.zhy.frame.pool.thread.vo.UncaughtExceptionVo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/1 9:25
 */
@Service
public class SynService {
    @Async
    public void exeAsynTask(Integer i) {
        System.out.println(new Date() + "执行任务：" + i);
        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException e) {
            throw new IllegalArgumentException(e);
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

    @Async
    public String asyncMethodWithVoidReturnType(UncaughtExceptionVo uncaughtExceptionVo) {
        throw new BusinessException(CommonException.Proxy.PARAM_ERROR);

//        System.out.println("线程名称："+Thread.currentThread().getName() + " be ready to read data!");
//        try {
//            Thread.sleep(1000 * 5);
//            System.out.println("---------------------》》》无返回值延迟3秒：");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return "已进入到异步";
    }

}
