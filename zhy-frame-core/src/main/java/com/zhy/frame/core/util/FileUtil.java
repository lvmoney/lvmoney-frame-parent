package com.zhy.frame.core.util;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/5
 * Copyright 四川******科技有限公司
 */


import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.base.core.exception.CommonException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class FileUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
    private static final int FILE_BYRE_LENGTH = 8 * 1024;

    /**
     * @describe: multipart 转flle ,待验证
     * @param: [file]
     * @return: void
     * @author： lvmoney /四川******科技有限公司
     * 2019/3/5
     */
    public static void multipartFileToFile(MultipartFile file) throws Exception {

        File toFile = null;
        if ("".equals(file) || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }

    }

    /**
     * @describe: File 转 MultipartFile,待验证
     * @param: [file]
     * @return: void
     * @author： lvmoney /四川******科技有限公司
     * 2019/3/5
     */
    public static void fileToMultipartFile(File file) throws Exception {

        FileInputStream fileInput = new FileInputStream(file);
        MultipartFile toMultipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(fileInput));
        toMultipartFile.getInputStream();

    }

    /**
     * @describe: inputstream 转file,待验证
     * @param: [ins, file]
     * @return: void
     * @author： lvmoney /四川******科技有限公司
     * 2019/3/5
     */
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[FILE_BYRE_LENGTH];
            while ((bytesRead = ins.read(buffer, 0, FILE_BYRE_LENGTH)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            LOGGER.error("inputstream 转 file报错:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.FILE_INPUTSTREAM2FILE_ERROR);
        }
    }

    /**
     * @describe: byte[] 转 inputstream
     * @param: [buf]
     * @return: java.io.InputStream
     * @author： lvmoney /四川******科技有限公司
     * 2019/3/6
     */
    public static final InputStream byte2Input(byte[] buf) {
        return new ByteArrayInputStream(buf);
    }

    /**
     * @describe: OutputStream 转 InputStream,待验证
     * @param: [out]
     * @return: java.io.ByteArrayInputStream
     * @author： lvmoney /四川******科技有限公司
     * 2019/3/6
     */
    public static ByteArrayInputStream parse(OutputStream out) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos = (ByteArrayOutputStream) out;
        ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;
    }

    public static String file2Base64(byte[] file) {
        String imgString = Base64.encodeBase64String(file);
        return "data:image/JPEG;base64," + imgString;
    }

    /**
     * @describe:
     * @param: [buf, filePath, fileName]
     * @return: void
     * @author： lvmoney /四川******科技有限公司
     * 2019/3/16
     */
    public static void byte2File(byte[] buf, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            file = new File(fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        } catch (Exception e) {
            LOGGER.error("流转换成文件报错:{}", e.getMessage());
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    LOGGER.error("流转换成文件报错:{}", e.getMessage());
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    LOGGER.error("流转换成文件报错:{}", e.getMessage());
                }
            }
        }
    }


    public static byte[] file2byte(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[FILE_BYRE_LENGTH];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            LOGGER.error("文件转成成流报错:{}", e.getMessage());
        } catch (IOException e) {
            LOGGER.error("文件转成成流报错:{}", e.getMessage());
        }
        return buffer;
    }

    public static void byte2File(byte[] buf, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        } catch (Exception e) {
            LOGGER.error("流转换成文件报错:{}", e.getMessage());
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    LOGGER.error("流转换成文件报错:{}", e.getMessage());
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    LOGGER.error("流转换成文件报错:{}", e.getMessage());
                }
            }
        }
    }


    /**
     * 大量写入数据.经过测试在resource大小为60M的时候，完成文件的读写在700ms内
     *
     * @param filename   文件路径/文件名称
     * @param appendable 是否追加到文件中
     * @param resource   写入的内容
     * @return
     * @throws IOException
     */
    public static boolean largeWrite(String filename, boolean appendable, String resource) throws IOException {
        File file = new File(filename);
        FileOutputStream fos = null;
        boolean result = true;
        try {
            fos = new FileOutputStream(file, appendable);
            FileChannel fc = fos.getChannel();
            ByteBuffer bb = ByteBuffer.allocateDirect(FILE_BYRE_LENGTH);
            ByteBuffer bbf = ByteBuffer.wrap(resource.getBytes(StandardCharsets.UTF_8));
            bbf.put(resource.getBytes(StandardCharsets.UTF_8));
            bbf.flip();
            fc.write(bbf);
            fc.close();
            fos.flush();
            fos.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }
            }
        }
        return result;
    }


}
