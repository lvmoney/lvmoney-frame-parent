package com.lvmoney.frame.base.core.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.base.core.util
 * 版本信息: 版本1.0
 * 日期:2022/2/14
 * Copyright XXXXXX科技有限公司
 */


import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static com.lvmoney.frame.base.core.constant.BaseConstant.CHARACTER_ENCODE_UTF8_UPPER;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/2/14 17:19
 */
public class AesUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(com.lvmoney.frame.base.core.util.AesUtil.class);
    /**
     * ASCII
     */
    private static final String ASCII = "ASCII";
    /**
     * aes
     */
    private static final String AES = "aes";
    /**
     * 默认长度
     */
    private static final Integer DEFAULT_LENGTH = 16;

    /**
     * 数据库级别的aes
     *
     * @param password:
     * @param strKey:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/14 17:24
     */
    public static String encrypt(String password, String strKey) {
        try {
            SecretKey key = generateMySqlAesKey(strKey, ASCII);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] cleartext = password.getBytes(CHARACTER_ENCODE_UTF8_UPPER);
            byte[] cipherTextBytes = cipher.doFinal(cleartext);
            return new String(Hex.encodeHex(cipherTextBytes));

        } catch (UnsupportedEncodingException e) {
            LOGGER.error("mysql AES_ENCRYPT 方法 UnsupportedEncodingException:{}", e);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("mysql AES_ENCRYPT 方法 NoSuchAlgorithmException:{}", e);
        } catch (NoSuchPaddingException e) {
            LOGGER.error("mysql AES_ENCRYPT 方法 NoSuchPaddingException:{}", e);
        } catch (InvalidKeyException e) {
            LOGGER.error("mysql AES_ENCRYPT 方法 InvalidKeyException:{}", e);
        } catch (IllegalBlockSizeException e) {
            LOGGER.error("mysql AES_ENCRYPT 方法 IllegalBlockSizeException:{}", e);
        } catch (BadPaddingException e) {
            LOGGER.error("mysql AES_ENCRYPT 方法 BadPaddingException:{}", e);
        }
        return null;
    }

    /**
     * aes 解密
     *
     * @param content:
     * @param aesKey:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/14 17:25
     */
    public static String decrypt(String content, String aesKey) {
        try {
            SecretKey key = generateMySqlAesKey(aesKey, ASCII);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] cleartext = Hex.decodeHex(content.toCharArray());
            byte[] cipherTextBytes = cipher.doFinal(cleartext);
            return new String(cipherTextBytes, CHARACTER_ENCODE_UTF8_UPPER);

        } catch (UnsupportedEncodingException e) {
            LOGGER.error("mysql AES_ENCRYPT 解密 方法 UnsupportedEncodingException:{}", e);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("mysql AES_ENCRYPT 解密 方法 NoSuchAlgorithmException:{}", e);
        } catch (NoSuchPaddingException e) {
            LOGGER.error("mysql AES_ENCRYPT 解密 方法 NoSuchPaddingException:{}", e);
        } catch (InvalidKeyException e) {
            LOGGER.error("mysql AES_ENCRYPT 解密 方法 InvalidKeyException:{}", e);
        } catch (IllegalBlockSizeException e) {
            LOGGER.error("mysql AES_ENCRYPT 解密 方法 IllegalBlockSizeException:{}", e);
        } catch (BadPaddingException e) {
            LOGGER.error("mysql AES_ENCRYPT 解密 方法 BadPaddingException:{}", e);
        } catch (DecoderException e) {
            LOGGER.error("mysql AES_ENCRYPT 解密 方法 DecoderException:{}", e);
        }
        return null;
    }

    /**
     * 转换成mysql aes
     *
     * @param key:
     * @param encoding:
     * @throws
     * @return: javax.crypto.spec.SecretKeySpec
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2022/2/14 17:25
     */
    public static SecretKeySpec generateMySqlAesKey(final String key, final String encoding) {
        try {
            final byte[] finalKey = new byte[DEFAULT_LENGTH];
            int i = 0;
            for (byte b : key.getBytes(encoding)) {
                finalKey[i++ % DEFAULT_LENGTH] ^= b;
            }
            return new SecretKeySpec(finalKey, AES);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("将数据转换成mysql AES_ENCRYPT函数相同的数据报错:{}", e);
            return null;
        }
    }

    public static void main(String[] args) {
        String abc = "121212";
        String aeskey = "1212";
        String a1 = encrypt(abc, aeskey);
        System.out.println(a1);
        System.out.println(decrypt(a1, aeskey));
    }
}
