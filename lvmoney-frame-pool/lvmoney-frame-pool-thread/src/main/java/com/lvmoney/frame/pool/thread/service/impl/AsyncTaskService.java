package com.lvmoney.frame.pool.thread.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.pool.thread.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/4/1
 * Copyright XXXXXX科技有限公司
 */


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/1 18:36
 */
@Service
public class AsyncTaskService {

    public String uuid() {
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
            System.out.println(LocalDateTime.now() + "--->工作线程(" + Thread.currentThread().getName() + ")");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return UUID.randomUUID().toString();
    }

}
