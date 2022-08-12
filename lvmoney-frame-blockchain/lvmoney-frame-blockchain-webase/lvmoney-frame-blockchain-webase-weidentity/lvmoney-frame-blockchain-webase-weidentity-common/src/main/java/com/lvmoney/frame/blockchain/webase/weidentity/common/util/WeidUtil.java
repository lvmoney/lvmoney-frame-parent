package com.lvmoney.frame.blockchain.webase.weidentity.common.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.weidentity.common.util
 * 版本信息: 版本1.0
 * 日期:2021/7/2
 * Copyright XXXXXX科技有限公司
 */


import org.fisco.bcos.web3j.crypto.Sign;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/7/2 15:19
 */
public class WeidUtil {
    public static byte[] simpleSignatureSerialization(Sign.SignatureData signatureData) {
        byte[] serializedSignatureData = new byte[65];
        serializedSignatureData[0] = signatureData.getV();
        System.arraycopy(signatureData.getR(), 0, serializedSignatureData, 1, 32);
        System.arraycopy(signatureData.getS(), 0, serializedSignatureData, 33, 32);
        return serializedSignatureData;
    }

    /**
     * 随机数
     *
     * @throws
     * @return: java.math.BigInteger
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/7/2 18:53
     */
    public static BigInteger getNonce() {
        Random r = new SecureRandom();
        BigInteger randomid = new BigInteger(250, r);
        return randomid;
    }
}
