package com.zhy.frame.core.util;/**
 * 描述:
 * 包名:com.zhy.frame.core.util
 * 版本信息: 版本1.0
 * 日期:2020/5/7
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.core.vo.ByteInputFileVo;
import com.zhy.frame.core.vo.ByteOutputFileVo;
import com.zhy.frame.core.vo.FileVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/7 10:55
 */
public class EncryptFile {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptFile.class);
    /**
     * 默认长度
     */
    private static final int BUFFER_LEN = 8 * 1024;

    /**
     * 对文件流进行加密。待测试
     *
     * @param byteInputFileVo:
     * @throws
     * @return: com.zhy.frame.core.vo.ByteOutputFileVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/7 14:58
     */
    public static ByteOutputFileVo encryptFile(ByteInputFileVo byteInputFileVo) {
        byte[] result = new byte[byteInputFileVo.getSrcByte().length];
        try {
            FileInputStream in = (FileInputStream) FileUtil.byte2Input(byteInputFileVo.getSrcByte());
            int c, pos, keyLen;
            pos = 0;
            byte[] secretByte = byteInputFileVo.getSecret().getBytes();
            keyLen = secretByte.length;
            byte[] buffer = new byte[BUFFER_LEN];
            while ((c = in.read(buffer)) != -1) {
                for (int i = 0; i < c; i++) {
                    buffer[i] ^= secretByte[pos];
                    result[i] = buffer[i];
                    pos++;
                    if (pos == keyLen) {
                        pos = 0;
                    }

                }
            }
            in.close();
        } catch (Exception ex) {
            LOGGER.error("文件加密报错{}", ex.getMessage());
        }
        return new ByteOutputFileVo(result);
    }

    /**
     * 对文件进行加密
     *
     * @param fileVo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/7 11:07
     */
    public static boolean encryptFile(FileVo fileVo) {
        boolean result = false;
        try {
            FileInputStream in = new FileInputStream(fileVo.getSrcFile());
            File file = new File(fileVo.getNewFile());
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(file);
            int c, pos, keyLen;
            pos = 0;
            byte[] secretByte = fileVo.getSecret().getBytes();
            keyLen = secretByte.length;
            byte[] buffer = new byte[BUFFER_LEN];
            while ((c = in.read(buffer)) != -1) {
                for (int i = 0; i < c; i++) {
                    buffer[i] ^= secretByte[pos];
                    out.write(buffer[i]);
                    pos++;
                    if (pos == keyLen) {
                        pos = 0;
                    }

                }
            }
            in.close();
            out.close();
            result = true;
        } catch (Exception ex) {
            LOGGER.error("文件加密报错{}", ex.getMessage());
        }
        return result;
    }


    /**
     * 对文件进行解密
     *
     * @param byteInputFileVo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/7 11:06
     */
    public static ByteOutputFileVo decryptFile(ByteInputFileVo byteInputFileVo) {
        byte[] result = new byte[byteInputFileVo.getSrcByte().length];
        try {
            FileInputStream in = (FileInputStream) FileUtil.byte2Input(byteInputFileVo.getSrcByte());
            int c, pos, keyLen;
            pos = 0;
            byte[] secretByte = byteInputFileVo.getSecret().getBytes();
            keyLen = secretByte.length;
            byte[] buffer = new byte[BUFFER_LEN];
            while ((c = in.read(buffer)) != -1) {
                for (int i = 0; i < c; i++) {
                    buffer[i] ^= secretByte[pos];
                    result[i] = buffer[i];
                    pos++;
                    if (pos == keyLen) {
                        pos = 0;
                    }
                }
            }
            in.close();
        } catch (Exception ex) {
            LOGGER.error("文件解密报错:{}", ex.getMessage());
        }
        return new ByteOutputFileVo(result);
    }


    /**
     * 对文件流进行解密，待测试
     *
     * @param fileVo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/7 11:06
     */
    public static boolean decryptFile(FileVo fileVo) {
        boolean result = false;
        try {
            FileInputStream in = new FileInputStream(fileVo.getSrcFile());
            File file = new File(fileVo.getNewFile());
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(file);
            int c, pos, keyLen;
            pos = 0;
            byte[] secretByte = fileVo.getSecret().getBytes();
            keyLen = secretByte.length;
            byte[] buffer = new byte[BUFFER_LEN];
            while ((c = in.read(buffer)) != -1) {
                for (int i = 0; i < c; i++) {
                    buffer[i] ^= secretByte[pos];
                    out.write(buffer[i]);
                    pos++;
                    if (pos == keyLen) {
                        pos = 0;
                    }
                }
            }
            in.close();
            out.close();
            result = true;
        } catch (Exception ex) {
            LOGGER.error("文件解密报错:{}", ex.getMessage());
        }
        return result;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            // debug =false ;

            String oldFile = new String("D:\\sclt\\data\\input\\test.txt");
            String newFile = new String("D:\\sclt\\data\\input\\test_en2.txt");
            //加密

            FileVo fileVo = new FileVo();
            fileVo.setNewFile("D:\\sclt\\data\\input\\test_en2.txt");
            fileVo.setSrcFile("D:\\sclt\\data\\input\\test.txt");
            fileVo.setSecret("xml");
            encryptFile(fileVo);

            System.out.println("完成加密");


//            String oldFile1 = "D:\\sclt\\data\\input\\test_en2.txt";
//            String newFile1 = "D:\\sclt\\data\\input\\test_77.txt";
//
//            decryptFile(oldFile1, newFile1);
//            System.out.println("完成解密");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
