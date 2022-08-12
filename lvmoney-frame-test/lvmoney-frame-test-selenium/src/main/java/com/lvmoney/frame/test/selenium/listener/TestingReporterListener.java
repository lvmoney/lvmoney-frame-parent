package com.lvmoney.frame.test.selenium.listener;/**
 * 描述:
 * 包名:com.lvmoney.frame.test.selenium.handler
 * 版本信息: 版本1.0
 * 日期:2020/3/23
 * Copyright XXXXXX科技有限公司
 */


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.ResourceCDN;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.TestAttribute;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.lvmoney.frame.base.core.constant.BaseConstant;
import com.lvmoney.frame.test.selenium.util.YmlUtil;
import com.lvmoney.frame.test.selenium.vo.ReporterVo;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.*;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/3/23 20:37
 */
public class TestingReporterListener implements IReporter {
    /**
     * 默认地址
     */
    private static final String OUTPUT = "output/";
    /**
     * 默认文件名
     */
    private static final String FILE_NAME = "测试报告.html";
    /**
     * 默认报告名称
     */
    private static final String NAME = "api测试报告";
    /**
     * 默认报告标题
     */
    private static final String TITLE = "api测试报告";
    /**
     * yml frame
     */
    private static final String YML_FRAME = "frame";
    /**
     * yml testing
     */
    private static final String YML_TESTING = "testing";
    /**
     * yml 报告目录
     */
    private static final String YML_TESTING_OUTPUT = "output";
    /**
     * yml 报告文件名
     */
    private static final String YML_TESTING_FILENAME = "filename";
    /**
     * yml 报告标题
     */
    private static final String YML_TESTING_TITLE = "title";
    /**
     * yml 报告名称
     */
    private static final String YML_TESTING_NAME = "name";
    /**
     * 名称最大长度
     */
    private static final int NAME_MAX_LENGTH = 50;
    private ExtentReports extent;


    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        init();
        boolean createSuiteNode = false;
        if (suites.size() > 1) {
            createSuiteNode = true;
        }
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();
            //如果suite里面没有任何用例，直接跳过，不在报告里生成
            if (result.size() == 0) {
                continue;
            }
            //统计suite下的成功、失败、跳过的总用例数
            int suiteFailSize = 0;
            int suitePassSize = 0;
            int suiteSkipSize = 0;
            ExtentTest suiteTest = null;
            //存在多个suite的情况下，在报告中将同一个一个suite的测试结果归为一类，创建一级节点。
            if (createSuiteNode) {
                suiteTest = extent.createTest(suite.getName()).assignCategory(suite.getName());
            }
            boolean createSuiteResultNode = false;
            if (result.size() > 1) {
                createSuiteResultNode = true;
            }
            for (ISuiteResult r : result.values()) {
                ExtentTest resultNode;
                ITestContext context = r.getTestContext();
                if (createSuiteResultNode) {
                    //没有创建suite的情况下，将在SuiteResult的创建为一级节点，否则创建为suite的一个子节点。
                    if (null == suiteTest) {
                        resultNode = extent.createTest(r.getTestContext().getName());
                    } else {
                        resultNode = suiteTest.createNode(r.getTestContext().getName());
                    }
                } else {
                    resultNode = suiteTest;
                }
                if (resultNode != null) {
                    resultNode.getModel().setName(suite.getName() + " : " + r.getTestContext().getName());
                    if (resultNode.getModel().hasCategory()) {
                        resultNode.assignCategory(r.getTestContext().getName());
                    } else {
                        resultNode.assignCategory(suite.getName(), r.getTestContext().getName());
                    }
                    resultNode.getModel().setStartTime(r.getTestContext().getStartDate());
                    resultNode.getModel().setEndTime(r.getTestContext().getEndDate());
                    //统计SuiteResult下的数据
                    int passSize = r.getTestContext().getPassedTests().size();
                    int failSize = r.getTestContext().getFailedTests().size();
                    int skipSize = r.getTestContext().getSkippedTests().size();
                    suitePassSize += passSize;
                    suiteFailSize += failSize;
                    suiteSkipSize += skipSize;
                    if (failSize > 0) {
                        resultNode.getModel().setStatus(Status.FAIL);
                    }
                    resultNode.getModel().setDescription(String.format("Pass: %s ; Fail: %s ; Skip: %s ;", passSize, failSize, skipSize));
                }
                buildTestNodes(resultNode, context.getFailedTests(), Status.FAIL);
                buildTestNodes(resultNode, context.getSkippedTests(), Status.SKIP);
                buildTestNodes(resultNode, context.getPassedTests(), Status.PASS);
            }
            if (suiteTest != null) {
                suiteTest.getModel().setDescription(String.format("Pass: %s ; Fail: %s ; Skip: %s ;", suitePassSize, suiteFailSize, suiteSkipSize));
                if (suiteFailSize > 0) {
                    suiteTest.getModel().setStatus(Status.FAIL);
                }
            }

        }

        extent.flush();
    }

    private void init() {
        ReporterVo reporterVo = getReporterConfig();
        //文件夹不存在的话进行创建
        File reportDir = new File(reporterVo.getOutput());
        if (!reportDir.exists() && !reportDir.isDirectory()) {
            reportDir.mkdir();
        }
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reporterVo.getOutput() + BaseConstant.FILE_SEPARATOR + reporterVo.getFileName());
        // 设置静态文件的DNS
        //怎么样解决cdn.rawgit.com访问不了的情况
        htmlReporter.config().setResourceCDN(ResourceCDN.EXTENTREPORTS);
        htmlReporter.config().setDocumentTitle(reporterVo.getTitle());
        htmlReporter.config().setReportName(reporterVo.getName());
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setCSS(".node.level-1  ul{ display:none;} .node.level-1.active ul{display:block;}");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setReportUsesManualConfiguration(true);
    }

    private void buildTestNodes(ExtentTest extenttest, IResultMap tests, Status status) {
        //存在父节点时，获取父节点的标签
        String[] categories = new String[0];
        if (extenttest != null) {
            List<TestAttribute> categoryList = extenttest.getModel().getCategoryContext().getAll();
            categories = new String[categoryList.size()];
            for (int index = 0; index < categoryList.size(); index++) {
                categories[index] = categoryList.get(index).getName();
            }
        }

        ExtentTest test;

        if (tests.size() > 0) {
            //调整用例排序，按时间排序
            Set<ITestResult> treeSet = new TreeSet<ITestResult>(new Comparator<ITestResult>() {
                @Override
                public int compare(ITestResult o1, ITestResult o2) {
                    return o1.getStartMillis() < o2.getStartMillis() ? -1 : 1;
                }
            });
            treeSet.addAll(tests.getAllResults());
            for (ITestResult result : treeSet) {
                Object[] parameters = result.getParameters();
                String name = "";
                //如果有参数，则使用参数的toString组合代替报告中的name
                for (Object param : parameters) {
                    name += param.toString();
                }
                if (name.length() > 0) {
                    if (name.length() > NAME_MAX_LENGTH) {
                        name = name.substring(0, 49) + "...";
                    }
                } else {
                    name = result.getMethod().getMethodName();
                }
                if (extenttest == null) {
                    test = extent.createTest(name);
                } else {
                    //作为子节点进行创建时，设置同父节点的标签一致，便于报告检索。
                    test = extenttest.createNode(name).assignCategory(categories);
                }
                for (String group : result.getMethod().getGroups()) {
                    test.assignCategory(group);
                    //描述
                    test.assignCategory(result.getMethod().getDescription());
                }

                List<String> outputList = Reporter.getOutput(result);
                for (String output : outputList) {
                    //将用例的log输出报告中
                    test.debug(output);
                }
                if (result.getThrowable() != null) {
                    test.log(status, result.getThrowable());
                } else {
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                }

                test.getModel().setStartTime(getTime(result.getStartMillis()));
                test.getModel().setEndTime(getTime(result.getEndMillis()));
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    /**
     * 获得yml里面的配置
     *
     * @throws
     * @return: com.lvmoney.frame.test.selenium.vo.ReporterVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/3/24 19:46
     */
    private ReporterVo getReporterConfig() {
        try {
            Map map = YmlUtil.readYml();
            LinkedHashMap frame = (LinkedHashMap) map.get(YML_FRAME);
            Map testing = (LinkedHashMap) frame.get(YML_TESTING);
            String output = (String) testing.get(YML_TESTING_OUTPUT);
            String filename = (String) testing.get(YML_TESTING_FILENAME);
            String title = (String) testing.get(YML_TESTING_TITLE);
            String name = (String) testing.get(YML_TESTING_NAME);
            return new ReporterVo(output, name, title, filename);
        } catch (Exception e) {
            return getDefault();
        }
    }

    /**
     * 设置默认值
     *
     * @throws
     * @return: com.lvmoney.frame.test.selenium.vo.ReporterVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/3/24 19:46
     */
    private ReporterVo getDefault() {
        return new ReporterVo(OUTPUT, NAME, TITLE, FILE_NAME);
    }


}