package com.zhy.ipfs.node.test;/**
 * 描述:
 * 包名:com.zhy.ipfs.node.test
 * 版本信息: 版本1.0
 * 日期:2020/5/6
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.core.util.HashUtil;
import io.ipfs.multihash.Multihash;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/6 14:36
 */
public class HashTest {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        System.out.println(HashUtil.getSha256("D:\\sclt\\data\\input\\test.txt"));
        String temp = HashUtil.getMultiHash("D:\\sclt\\data\\input\\test.txt");
        System.out.println(temp);
        String result = Multihash.fromHex(temp).toBase58();
        System.out.println(result);
    }
}
