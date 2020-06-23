package com.zhy.frame.core.util;/**
 * 描述:
 * 包名:com.zhy.frame.base.core.util
 * 版本信息: 版本1.0
 * 日期:2020/5/6
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.constant.BaseConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/6 14:23
 */
public class HashUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(HashUtil.class);


    /**
     * multihash 前缀
     */
    private static final String MULTI_HASH_PREFIX = "1220";

    /**
     * inputstream 长度
     */
    private static final int INPUT_STREAM_LENGTH = 1024;


    /**
     * 获得 ipfs 的
     *
     * @param str: MultiHash
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/6 14:34
     */
    public static String getMultiHash(String str) {
        return MULTI_HASH_PREFIX + getSha256(str);
    }


    public static String getSha256(String file) {
        String str = "";
        try {
            FileInputStream fis = new FileInputStream(file);
            str = getHashCode(fis, BaseConstant.SHA_256_SIGNATURE_TYPE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getHashCode(InputStream fis, String hashkeytype) {
        try {
            //拿到一个MD5转换器,如果想使用SHA-1或SHA-256，则传入SHA-1,SHA-256
            MessageDigest md = MessageDigest.getInstance(hashkeytype);
            //分多次将一个文件读入，对于大型文件而言，比较推荐这种方式，占用内存比较少。
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, INPUT_STREAM_LENGTH)) != -1) {
                md.update(buffer, 0, length);
            }
            fis.close();
            //转换并返回包含16个元素字节数组,返回数值范围为-128到127
            byte[] md5Bytes = md.digest();
            //1代表绝对值
            BigInteger bigInt = new BigInteger(1, md5Bytes);
            return bigInt.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 获得文件CRC32。当文件大小在100kb以内，获取速度还可以，文件大了后相当慢
     *
     * @param filePath:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/6 17:41
     */
    public static String getFileCRC32(String filePath) {
        CRC32 crc32 = new CRC32();
        FileInputStream inputStream = null;
        CheckedInputStream checkedinputstream = null;
        String crcStr = null;
        try {
            inputStream = new FileInputStream(new File(filePath));
            checkedinputstream = new CheckedInputStream(inputStream, crc32);
            while (checkedinputstream.read() != -1) {
            }
            crcStr = Long.toHexString(crc32.getValue()).toUpperCase();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            if (checkedinputstream != null) {
                try {
                    checkedinputstream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return crcStr;
    }

    /**
     * 获得文件的各种hash值。当文件达到700m左右，大概需要3000ms。文件数据量小的时候速度非常快
     *
     * @param filePath:
     * @param hashType:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/6 17:41
     */
    public static String getFileHash(String filePath, String hashType) {
        File file = new File(filePath);
        if (file == null || !file.exists()) {
            return null;
        }
        String result = null;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            MappedByteBuffer mbf = fis.getChannel().map(
                    FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md = MessageDigest.getInstance(hashType);
            md.update(mbf);
            BigInteger bi = new BigInteger(1, md.digest());
            result = bi.toString(16);
        } catch (Exception e) {
            return null;
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.getStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 获取字符串的各种hash值
     *
     * @param source:
     * @param hashType:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/6 17:42
     */
    public static String getStringHash(String source, String hashType) {
        // 返回值
        String result = null;

        // 是否是有效字符串
        if (source != null && source.length() > 0) {
            try {
                // SHA 加密开始
                // 创建加密对象 并傳入加密類型
                MessageDigest messageDigest = MessageDigest.getInstance(hashType);
                // 传入要加密的字符串
                messageDigest.update(source.getBytes());
                // 得到 byte 類型结果
                byte[] byteBuffer = messageDigest.digest();

                // 將 byte 轉換爲 string
                StringBuffer strHexString = new StringBuffer();
                // 遍歷 byte buffer
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 得到返回結果
                result = strHexString.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 获取字符串的CRC32
     *
     * @param source:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2019/11/6 17:42
     */
    public static String getStringCRC32(String source) {
        String result = "";
        CRC32 crc32 = new CRC32();
        crc32.update(source.getBytes());
        result = Long.toHexString(crc32.getValue());
        return result;
    }

    /**
     * 获取shaa1
     *
     * @param toHash:
     * @throws
     * @return: byte[]
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 17:00
     */
    public static byte[] sha1Hash(String toHash) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(BaseConstant.SHA_1_SIGNATURE_TYPE);
            md.update(toHash.getBytes());
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("获得{}SHA-1报错:{}", toHash, e);
            return null;
        }
    }

    public static byte[] HmacSha1Hash(String encryptKey, String encryptText) {
        try {
            Key signingKey = new SecretKeySpec(encryptKey.getBytes(), BaseConstant.HMAC_SHA1_SIGNATURE_TYPE);
            Mac mac = Mac.getInstance(BaseConstant.HMAC_SHA1_SIGNATURE_TYPE);
            mac.init(signingKey);
            return mac.doFinal(encryptText.getBytes());
        } catch (Exception e) {
            LOGGER.error("获得HmacSHA1值报错:{}", e);
            return null;
        }
    }


}
