package com.zhy.demo.serialize.test;/**
 * 描述:
 * 包名:com.zhy.demo.serialize.test
 * 版本信息: 版本1.0
 * 日期:2020/6/17
 * Copyright XXXXXX科技有限公司
 */


import com.jfirer.fse.ByteArray;
import com.jfirer.fse.Fse;
import com.zhy.demo.serialize.vo.TestItem;
import com.zhy.demo.serialize.vo.TestVo;
import com.zhy.frame.base.core.util.JsonUtil;

import static org.testng.Assert.assertTrue;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/17 10:23
 */
public class Test {
    public static void main(String[] args) {
        Fse fse = new Fse();
        TestVo data = new TestVo();
        data.setName("test");
        data.setUid("test");
        TestItem testItem = new TestItem();
        testItem.setDate("now");
        testItem.setSex("女");
        data.setItem(testItem);

        String test4 = "{\"item\":{\"date\":\"now\",\"sex\":\"女\"},\"name\":\"test\",\"uid\":\"test\"}";
//创建一个二进制数组容器，用于容纳序列化后的输出。容器大小会在需要时自动扩大，入参仅决定初始化大小。
        ByteArray buf = ByteArray.allocate(100);
//执行序列化，会将序列化对象序列化到二进制数组容器之中。
        fse.serialize(data, buf);
//得到序列化后的二进制数组结果
        byte[] resultBytes = buf.toArray();
//清空容器内容，可以反复使用该容器
        buf.clear();
//填入数据，准备进行反序列化
        buf.put(resultBytes);
        TestVo result = (TestVo) fse.deSerialize(buf);
        System.out.println(JsonUtil.t2JsonString(result));
        assertTrue(result.equals(data));
    }
}
