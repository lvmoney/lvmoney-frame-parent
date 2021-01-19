package com.lvmoney.frame.test;/**
 * 描述:
 * 包名:test
 * 版本信息: 版本1.0
 * 日期:2020/3/23
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.test.selenium.listener.TestingReporterListener;
import lombok.extern.slf4j.Slf4j;
import org.mockito.Mockito;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Iterator;

import static org.mockito.Mockito.mock;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/23 13:48
 */
@WebAppConfiguration
@Slf4j
@Listeners(TestingReporterListener.class)
public class CaseBase extends AbstractTestNGSpringContextTests {
    public CaseBase() {
    }

    @Test
    public void test() {
        //couponMapper.queryByLoanBillId(1l);
        System.out.println("test....");
        logger.info("test from logger...");
    }

    @Test
    public void test2() {

        //mock一个Iterator类
        Iterator iterator = mock(Iterator.class);
        //预设当iterator调用next()时第一次返回hello，第n次都返回world
        Mockito.when(iterator.next()).thenReturn("hello").thenReturn("world");
        //使用mock的对象
        String result = iterator.next() + " " + iterator.next() + " " + iterator.next();
        System.out.println(result);
        //验证结果
        Assert.assertEquals("hello world world", result);
    }


}