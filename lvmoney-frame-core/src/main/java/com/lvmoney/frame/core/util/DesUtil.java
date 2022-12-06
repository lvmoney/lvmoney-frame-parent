package com.lvmoney.frame.core.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.client.core.util
 * 版本信息: 版本1.0
 * 日期:2021/8/27
 * Copyright XXXXXX科技有限公司
 */

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2021/8/27 10:59
 */
public class DesUtil {

    private final static String DES = "DES";
    private final static String ENCODE = "UTF-8";
    private static final Logger LOGGER = LoggerFactory.getLogger(DesUtil.class);


    /**
     * Description 根据键值进行加密
     *
     * @param data:
     * @param key:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/8/27 11:28
     */
    public static String encrypt(String data, String key) {
        byte[] bt = new byte[0];
        try {
            bt = encrypt(data.getBytes(ENCODE), key.getBytes(ENCODE));
        } catch (Exception e) {
            LOGGER.error("des加密报错：{}", e);
        }
        String result = Base64.encodeBase64String(bt);
        return result;
    }


    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key  加密键byte数组
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/8/27 11:29
     */
    public static String decrypt(String data, String key) {
        if (data == null) {
            return null;
        }
        byte[] buf = new byte[0];
        try {
            buf = Base64.decodeBase64(data);
            byte[] bt = decrypt(buf, key.getBytes(ENCODE));
            return new String(bt, ENCODE);
        } catch (IOException e) {
            LOGGER.error("des解密报错：{}", e);
        } catch (Exception e) {
            LOGGER.error("des解密报错：{}", e);
        }
        return "";

    }

    /**
     * Description 根据键值进行加密
     *
     * @param data
     * @param key  加密键byte数组
     * @throws
     * @return: byte[]
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/8/27 11:29
     */
    private static byte[] encrypt(byte[] data, byte[] key) {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = null;
        try {
            dks = new DESKeySpec(key);
        } catch (InvalidKeyException e) {
            LOGGER.error("获得DESKeySpec报错：{}", e);
        }

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = null;
        try {
            keyFactory = SecretKeyFactory.getInstance(DES);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("获得SecretKeyFactory报错：{}", e);
        }
        SecretKey securekey = null;
        try {
            securekey = keyFactory.generateSecret(dks);
        } catch (InvalidKeySpecException e) {
            LOGGER.error("获得SecretKey报错：{}", e);
        }

        // Cipher对象实际完成加密操作
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(DES);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("获得Cipher报错：{}", e);
        } catch (NoSuchPaddingException e) {
            LOGGER.error("获得Cipher报错：{}", e);
        }

        // 用密钥初始化Cipher对象
        try {
            cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        } catch (InvalidKeyException e) {
            LOGGER.error("Cipher init报错：{}", e);
        }

        try {
            return cipher.doFinal(data);
        } catch (IllegalBlockSizeException e) {
            LOGGER.error("Cipher doFinal报错：{}", e);
        } catch (BadPaddingException e) {
            LOGGER.error("Cipher doFinal报错：{}", e);
        }
        return null;
    }

    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key  加密键byte数组
     * @throws
     * @return: byte[]
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/8/27 11:30
     */
    private static byte[] decrypt(byte[] data, byte[] key) {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = null;
        try {
            dks = new DESKeySpec(key);
        } catch (InvalidKeyException e) {
            LOGGER.error("获得DESKeySpec报错：{}", e);
        }

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = null;
        try {
            keyFactory = SecretKeyFactory.getInstance(DES);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("获得SecretKeyFactory报错：{}", e);
        }
        SecretKey secretKey = null;
        try {
            secretKey = keyFactory.generateSecret(dks);
        } catch (InvalidKeySpecException e) {
            LOGGER.error("获得SecretKey报错：{}", e);
        }

        // Cipher对象实际完成解密操作
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(DES);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("获得Cipher报错：{}", e);
        } catch (NoSuchPaddingException e) {
            LOGGER.error("获得Cipher报错：{}", e);
        }

        // 用密钥初始化Cipher对象
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey, sr);
        } catch (InvalidKeyException e) {
            LOGGER.error("Cipher init报错：{}", e);
        }

        try {
            return cipher.doFinal(data);
        } catch (IllegalBlockSizeException e) {
            LOGGER.error("Cipher doFinal报错：{}", e);
        } catch (BadPaddingException e) {
            LOGGER.error("Cipher doFinal报错：{}", e);
        }
        return null;
    }


}
