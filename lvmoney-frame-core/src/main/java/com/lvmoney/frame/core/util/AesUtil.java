package com.lvmoney.frame.core.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.core.util
 * 版本信息: 版本1.0
 * 日期:2021/6/22
 * Copyright XXXXXX科技有限公司
 */


import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/22 17:20
 */
public class AesUtil {
    /**
     * aes 加密
     *
     * @param res:
     * @param pass:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/23 14:46
     */
    public static String encrypt(String res, String pass) {
        byte[] key = pass.getBytes();
        AES aes = SecureUtil.aes(key);
        String encryptHex = aes.encryptHex(res);
        return encryptHex;
    }

    /**
     * ads 解密
     *
     * @param res:
     * @param pass:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/6/23 14:46
     */
    public static String decrypt(String res, String pass) {
        byte[] key = pass.getBytes();
        AES aes = SecureUtil.aes(key);
        String decryptStr = aes.decryptStr(res);
        return decryptStr;
    }

}
