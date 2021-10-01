package com.lvmoney.frame.base.core.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.base.core.util
 * 版本信息: 版本1.0
 * 日期:2021/8/31
 * Copyright XXXXXX科技有限公司
 */


import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import com.lvmoney.frame.base.core.vo.Sm2CipherVo;
import org.bouncycastle.util.encoders.Hex;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/8/31 8:53
 */
public class Sm2Util {
    /**
     * 私钥长度
     */
    private static final String SM2 = "SM2";

    /**
     * 获得SM2公钥和私钥
     *
     * @throws
     * @return: com.lvmoney.frame.base.core.vo.Sm2CipherVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/8/31 9:11
     */
    public static Sm2CipherVo getSm2Cipher() {
        KeyPair pair = SecureUtil.generateKeyPair(SM2);
        byte[] privateKeyArray = pair.getPrivate().getEncoded();
        byte[] publicKeyArray = pair.getPublic().getEncoded();
        String privateKeyHex = Hex.toHexString(privateKeyArray);
        String publicKeyHex = Hex.toHexString(publicKeyArray);
        Sm2CipherVo sm2CipherVo = new Sm2CipherVo();
        sm2CipherVo.setPrivateKeyHex(privateKeyHex);
        sm2CipherVo.setPublicKeyHex(publicKeyHex);
        return sm2CipherVo;
    }

    /**
     * sm2解密
     *
     * @param res:
     * @param privateKey:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/8/31 10:10
     */
    public static String decrypt(String res, String privateKey) {
        SM2 sm2 = new SM2();
        PrivateKey ecPrivateKey = SecureUtil.generatePrivateKey(SM2, Hex.decode(privateKey));
        sm2.setPrivateKey(ecPrivateKey);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(res, KeyType.PrivateKey));
        return decryptStr;
    }

    /**
     * 公钥加密
     *
     * @param res:
     * @param publicKey:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/8/31 10:57
     */
    public static String encrypt(String res, String publicKey) {
        SM2 sm2 = new SM2();
        PublicKey ecPublicKey = SecureUtil.generatePublicKey(SM2, Hex.decode(publicKey));
        sm2.setPublicKey(ecPublicKey);
        String encryptStr = sm2.encryptBcd(res, KeyType.PublicKey);
        return encryptStr;
    }
}
