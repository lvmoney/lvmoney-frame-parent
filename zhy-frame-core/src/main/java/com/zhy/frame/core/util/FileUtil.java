package com.zhy.frame.core.util;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/5
 * Copyright 四川******科技有限公司
 */


import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.base.core.exception.CommonException;
import com.zhy.frame.base.core.util.JsonUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.zhy.frame.base.core.constant.BaseConstant.CHARACTER_ENCODE_UTF8_LOWER;
import static com.zhy.frame.base.core.constant.BaseConstant.PLACEHOLDER_WARP_SPACE;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class FileUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
    private static final int FILE_BYTE_LENGTH = 32 * 1024;

    /**
     * @describe: multipart 转flle
     * @param: [file]
     * @return: void
     * @author： lvmoney /四川******科技有限公司
     * 2019/3/5
     */
    public static File multipartFile2File(MultipartFile file) {
        File toFile = null;
        if (("").equals(file) || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            try {
                ins = file.getInputStream();
            } catch (IOException e) {
                LOGGER.error("获取文件的inputStream报错:{}", e);
            }
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            try {
                ins.close();
            } catch (IOException e) {
                LOGGER.error("关闭文件的inputStream报错:{}", e);
            }
        }
        return toFile;
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
            byte[] buffer = new byte[FILE_BYTE_LENGTH];
            while ((bytesRead = ins.read(buffer, 0, FILE_BYTE_LENGTH)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            LOGGER.error("inputstream 转 file报错:{}", e);
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
            LOGGER.error("流转换成文件报错:{}", e);
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    LOGGER.error("流转换成文件报错:{}", e);
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    LOGGER.error("流转换成文件报错:{}", e);
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
            byte[] b = new byte[FILE_BYTE_LENGTH];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            LOGGER.error("文件转成成流报错:{}", e);
        } catch (IOException e) {
            LOGGER.error("文件转成成流报错:{}", e);
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
            LOGGER.error("流转换成文件报错:{}", e);
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    LOGGER.error("流转换成文件报错:{}", e);
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    LOGGER.error("流转换成文件报错:{}", e);
                }
            }
        }
    }


    /**
     * 大量写入数据.经过测试在resource大小为60M的时候，完成文件的读写在700ms内
     *
     * @param filename   文件路径/文件名称
     * @param resource   写入的内容
     * @param appendable 是否写入
     * @return
     * @throws IOException
     */
    public static boolean largeWrite(String filename, boolean appendable, String resource) {


        StringBuilder sb = new StringBuilder();
        sb.append(resource);
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(filename, "rw");
            byte[] bytes = sb.toString().getBytes();
            randomAccessFile.seek(randomAccessFile.length());
            if (appendable) {
                randomAccessFile.write(bytes);
            }
            randomAccessFile.close();
        } catch (Exception e) {
            LOGGER.error("写文件报错:{}", e);
            return false;
        }
        return true;
    }

    /**
     * 文件从srcPath复制到targetPath
     *
     * @param srcPath:
     * @param targetPath:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/26 9:39
     */
    public boolean fileCopy(String srcPath, String targetPath) {
        File file = new File(targetPath);
        if (!file.getParentFile().exists()) {
            file.mkdirs();
        }
        try {
            RandomAccessFile inRandomAccessFile = new RandomAccessFile(srcPath, "r");
            FileChannel inFileChannel = inRandomAccessFile.getChannel();
            MappedByteBuffer inMappedByteBuffer = inFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, inFileChannel.size());
            inRandomAccessFile.close();
            inFileChannel.close();
            RandomAccessFile outRandomAccessFile = new RandomAccessFile(targetPath, "rw");
            FileChannel outFileChannel = outRandomAccessFile.getChannel();
            MappedByteBuffer outMappedByteBuffer = outFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, inMappedByteBuffer.capacity());
            outMappedByteBuffer.put(inMappedByteBuffer);
            outMappedByteBuffer.force();
            outRandomAccessFile.close();
            outFileChannel.close();
            outMappedByteBuffer.flip();
        } catch (Exception e) {
            LOGGER.error("文件复制失败:{}", e);
            return false;
        }
        return true;

    }

    /**
     * 读文件
     *
     * @param fileName:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/26 15:51
     */
    public static Long readFile(String fileName) {
        File file = new File(fileName);
        long len = file.length();
        Long result = 0L;
        try {
            MappedByteBuffer mappedByteBuffer = new RandomAccessFile(file, "r")
                    .getChannel()
                    .map(FileChannel.MapMode.READ_ONLY, 0, len);
            byte[] dst = new byte[FILE_BYTE_LENGTH];

            for (int offset = 0; offset < mappedByteBuffer.capacity(); offset += FILE_BYTE_LENGTH) {
                if (mappedByteBuffer.capacity() - offset >= FILE_BYTE_LENGTH) {
                    for (int i = 0; i < FILE_BYTE_LENGTH; i++) {
                        dst[i] = mappedByteBuffer.get(offset + i);
                    }
                } else {
                    for (int i = 0; i < mappedByteBuffer.capacity() - offset; i++) {
                        dst[i] = mappedByteBuffer.get(offset + i);
                    }
                }
                int length = (mappedByteBuffer.capacity() % FILE_BYTE_LENGTH == 0) ? FILE_BYTE_LENGTH
                        : mappedByteBuffer.capacity() % FILE_BYTE_LENGTH;
//                System.out.println(new String(dst, 0, length));
                String temp = new String(dst, 0, length);
                result += getCharacterCount(temp, "A");
            }

        } catch (IOException e) {
            LOGGER.error("读取文件报错:{}", e);
            return 0L;
        }
        return result;

    }


    /**
     * 获取文件名的后缀名
     *
     * @param fileName:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/26 16:11
     */

    public static String getFileSuffix(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf(BaseConstant.DECIMAL_POINT));
        return suffix;
    }

    /**
     * 获得字符串中字符的数量
     *
     * @param res:
     * @throws
     * @return: java.util.Map
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/27 11:21
     */

    public static Map<String, Long> getCharacterCount(String res) {
        Map<String, Long> map = new HashMap(BaseConstant.MAP_DEFAULT_SIZE);
        //3.遍历字符串，获取每一个字符
        for (char c : res.toCharArray()) {
            //4.使用获取到的字符，去Map集合判断key是否存在
            if (map.containsKey(c)) {
                //key存在
                Long value = map.get(c);
                value++;
                map.put(String.valueOf(c), value);
            } else {
                //key不存在
                map.put(String.valueOf(c), 1L);
            }
        }
        return map;
    }

    /**
     * 获得某个字符在字符串中出现的次数
     *
     * @param res:
     * @param s:
     * @throws
     * @return: java.lang.Long
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/27 11:28
     */
    public static Long getCharacterCount(String res, String s) {
        Long count = 0L;
        for (char c : res.toCharArray()) {
            if (String.valueOf(c).equals(s)) {
                count += 1;
            }
        }
        return count;
    }


    public static void main(String[] args) {
//        for (int n = 0; n < 5; n++) {
//            for (int i = 0; i < 1; i++) {
//                largeWrite("D:\\data\\temp\\test" + i + ".txt", true, i + "AABBBAAAACCCCAAAAADDDDDAAAAAAFFFFFFAAAAAAAADDD" + i);
//            }
//        }

//        long start = System.currentTimeMillis();   //获取开始时间
//        Long text = 0L;
//        for (int i = 0; i < 1; i++) {
//            Long dd = FileUtil.readFile("D:\\data\\temp\\t" + 1 + ".txt");
//            text = text + dd;
//        }
//        System.out.println(text);
//        long end = System.currentTimeMillis(); //获取结束时间
//        System.out.println("程序运行时间： " + (end - start) + "ms");

        readFile("D:\\data\\temp\\test.txt", 15, 1700);
    }

    /**
     * 读取文件某一段数据
     *
     * @param file:
     * @param begin: 开始
     * @param end:   结束
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/29 10:08
     */
    public static Long readFile(String file, long begin, long end) {
        String a = file.split(FileUtil.getFileSuffix(file))[0];
        Long result = 0L;
        try {
            //申明文件切割后的文件磁盘
            RandomAccessFile in = new RandomAccessFile(new File(file), "r");
            //定义一个可读，可写的文件并且后缀名为的二进制文件
            SnowflakeIdFactoryUtil snowflakeIdFactoryUtil = new SnowflakeIdFactoryUtil(1, 2);
            String tempFile = a + BaseConstant.CONNECTOR_UNDERLINE + snowflakeIdFactoryUtil.nextId() + FileUtil.getFileSuffix(file);
            File delFile = new File(tempFile);
            //申明具体每一文件的字节数组

            int n = 0;
            //从指定位置读取文件字节流
            in.seek(begin);

            //判断文件流读取的边界
            Long seq = end - begin;
            if (seq <= FILE_BYTE_LENGTH) {
                byte[] w = new byte[Integer.parseInt(String.valueOf(seq))];
                n = in.read(w);
                result += getCharacterCount(new String(w, 0, n), "A");
            } else {
                byte[] b = new byte[FILE_BYTE_LENGTH];
                int loop = (int) (seq / FILE_BYTE_LENGTH);
                for (int i = 0; i < loop; i++) {
                    n = in.read(b);
                    String temp = new String(b, 0, n);
                    result += getCharacterCount(temp, "A");
                }
                if (seq % FILE_BYTE_LENGTH > 0) {
                    byte[] w = new byte[Integer.parseInt(String.valueOf(seq % FILE_BYTE_LENGTH))];
                    n = in.read(w);
                    String temp = new String(w, 0, n);
                    result += getCharacterCount(temp, "A");
                }

            }


            //关闭输入流
            in.close();
            if (delFile.exists()) {
                delFile.delete();
            }
        } catch (
                Exception e) {
            LOGGER.error("获得文件位置指针报错:{}", e);
        }
        return result;
    }

    /**
     * 通过输入流获得数据
     *
     * @param in:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/4 10:00
     */
    public static String getByInputStream(InputStream in) {
        BufferedReader reader = null;
        reader = new BufferedReader(new InputStreamReader(in, Charset.forName(CHARACTER_ENCODE_UTF8_LOWER)));

        StringBuffer buffer = new StringBuffer();
        String str = null;
        try {
            while ((str = reader.readLine()) != null) {
                buffer.append(str + PLACEHOLDER_WARP_SPACE);
            }
            reader.close();
        } catch (Exception ex) {
            LOGGER.error("从InputStream获得数据报错:{}", ex);
        }
        return buffer.toString();
    }


    /**
     * 创建文件，可选择是否覆盖
     *
     * @param path:
     * @param cover:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/17 16:36
     */
    public static boolean createFile(String path, boolean cover) {
        File file = new File(path);
        if (!file.exists()) {
            return file.mkdirs();
        } else if (cover) {
            file.delete();
            return file.mkdirs();
        }
        return true;
    }

    /**
     * 删除文件夹及其目录下文件
     *
     * @param file:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/18 10:39
     */
    public static boolean delFile(File file) {
        if (!file.exists()) {
            return false;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                delFile(f);
            }
        }
        return file.delete();
    }

    /**
     * 清空文件夹
     *
     * @param file:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/18 10:39
     */
    public static boolean clearFile(File file) {
        if (!file.exists()) {
            return false;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                delFile(f);
            }
        }
        return true;
    }

}
