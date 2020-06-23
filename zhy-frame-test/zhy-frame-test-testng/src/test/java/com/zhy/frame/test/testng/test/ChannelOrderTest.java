package com.zhy.frame.test.testng.test;/**
 * 描述:
 * 包名:com.zhy.frame.test.testng.test
 * 版本信息: 版本1.0
 * 日期:2020/6/10
 * Copyright XXXXXX科技有限公司
 */


import io.qameta.allure.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.Test;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/10 16:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ChannelOrderTest {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Mock
    private ProceedingJoinPoint pjp;
    String custId = "ff808081698b2cbc03146b32dbf90000";// 创建用户

    @Before
    public void setUp() throws Exception {

    }

    @Epic("正向epic")//测试集
    @Story("正向story")//测试case
    @Step("获取城市编码")//测试步骤
    @Severity(SeverityLevel.CRITICAL)//设置case的优先级
    @Test
    public void createAccount() throws Throwable {
        System.out.println(" testNg  测试");
    }
}
