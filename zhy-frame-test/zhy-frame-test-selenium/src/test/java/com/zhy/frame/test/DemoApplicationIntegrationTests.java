package com.zhy.frame.test;/**
 * 描述:
 * 包名:test
 * 版本信息: 版本1.0
 * 日期:2020/3/21
 * Copyright XXXXXX科技有限公司
 */


import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/21 20:54
 */
@SpringBootTest
@WebAppConfiguration
@Slf4j
public class DemoApplicationIntegrationTests {
    private static ChromeDriver browser;

    @BeforeClass
    public static void init() {
        System.setProperty("webdriver.chrome.driver", "E:\\pkg\\chromedriver_win32_79\\chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().timeouts()
                .implicitlyWait(10, TimeUnit.SECONDS);
    }

    //自动化 访问百度
    @Test
    public void step00_test() throws InterruptedException {

        //获取网址

        browser.get("https://www.baidu.com");

        //线程睡眠3秒（目的是等待浏览器打开完成）

        Thread.sleep(3000);

        //获取百度搜索输入框元素，并自动写入搜索内容

        browser.findElement(By.id("kw")).sendKeys("selenium");

        //线程睡眠1秒

        Thread.sleep(1000);

        //获取“百度一下”元素，并自动点击

        browser.findElement(By.id("su")).click();

        //线程睡眠3秒

        Thread.sleep(3000);
    }

    @AfterClass
    public static void closeBrowser() {
        browser.quit();
    }
}