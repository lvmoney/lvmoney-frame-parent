package com.zhy.frame.pool.thread.service.impl;/**
 * 描述:
 * 包名:com.zhy.frame.pool.thread.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/4/1
 * Copyright XXXXXX科技有限公司
 */


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/1 20:38
 */
public class SumTask extends RecursiveTask<Integer> {

    private Integer start = 0;
    private Integer end = 0;

    public SumTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        if (end - start < 100) {
            //小于100时直接返回结果
            int sumResult = 0;
            for (int i = start; i <= end; i++) {
                sumResult += i;
            }
            return sumResult;
        } else {
            //大于一百时进行分割
            int middle = (end + start) / 2;
            SumTask leftSum = new SumTask(this.start, middle);
            SumTask rightSum = new SumTask(middle, this.end);
            leftSum.fork();
            rightSum.fork();
            return leftSum.join() + rightSum.join();
        }
    }

    public static void main(String[] args) {
        List<Integer> lists = new ArrayList<>();
        lists.add(2);
        lists.add(2);
        lists.add(3);
        Integer pro = lists.parallelStream().reduce(1, (a, b) -> a * (b * 2), (a, b) -> a * (b * 2));
        System.out.println(pro);
    }
}
