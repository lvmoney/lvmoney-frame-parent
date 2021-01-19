package com.lvmoney.frame.test.jmh.test;/**
 * 描述:
 * 包名:com.lvmoney.frame.test.jmh.test
 * 版本信息: 版本1.0
 * 日期:2020/5/21
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/21 21:25
 */
public interface Calculator {
    /**
     * calculate sum of an integer array
     *
     * @param numbers
     * @return
     */
    public long sum(int[] numbers);

    /**
     * shutdown pool or reclaim any related resources
     */
    public void shutdown();

}
