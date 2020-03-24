package com.zhy.frame.test;/**
 * 描述:
 * 包名:test
 * 版本信息: 版本1.0
 * 日期:2020/3/23
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.test.selenium.vo.TestingReporterListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

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


}