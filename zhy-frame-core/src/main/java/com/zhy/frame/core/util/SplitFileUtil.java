package com.zhy.frame.core.util;/**
 * 描述:
 * 包名:com.zhy.frame.core.util
 * 版本信息: 版本1.0
 * 日期:2020/5/26
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.core.vo.SplitFileVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/26 14:05
 */
public class SplitFileUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(SplitFileUtil.class);
    /**
     * 文件读取大小
     */
    private static final int FILE_BYTE_LENGTH = 32 * 1024;

    /**
     * 文件分割
     *
     * @param file:
     * @param count:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/27 10:05
     */
    public static boolean getSplitFile(String file, int count) {
        RandomAccessFile raf = null;
        try {
            //获取目标文件 预分配文件所占的空间 在磁盘中创建一个指定大小的文件   r 是只读
            raf = new RandomAccessFile(new File(file), "r");
            //文件的总长度
            long length = raf.length();
            //文件切片后的长度
            long maxSize = length / count;
            //初始化偏移量
            long offSet = 0L;
            for (int i = 0; i < count - 1; i++) {
                //最后一片单独处理
                long begin = offSet;
                long end = (i + 1) * maxSize;
                offSet = spiltWrite(file, i, begin, end);
            }
            if (length - offSet > 0) {
                spiltWrite(file, count - 1, offSet, length);
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("没有找到对应的文件:{}", file);
            return false;
        } catch (IOException e) {
            LOGGER.error("文件切割失败:{}，错误原因是：{}", file, e);
            return false;
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                LOGGER.error("文件切割失败:{}，错误原因是：{}", file, e);
            }
        }
        return true;
    }

    /**
     * 指定文件每一份的边界，写入不同文件中
     *
     * @param file:  源文件
     * @param index: 源文件的顺序标识
     * @param begin: 开始指针的位置
     * @param end:   结束指针的位置
     * @throws
     * @return: long
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/27 10:03
     */
    private static long spiltWrite(String file, int index, long begin, long end) {
        String a = file.split(FileUtil.getFileSuffix(file))[0];
        long endPointer = 0L;
        try {
            //申明文件切割后的文件磁盘
            RandomAccessFile in = new RandomAccessFile(new File(file), "r");
            //定义一个可读，可写的文件并且后缀名为的二进制文件
            RandomAccessFile out = new RandomAccessFile(new File(a + BaseConstant.CONNECTOR_UNDERLINE + index + FileUtil.getFileSuffix(file)), "rw");
            //申明具体每一文件的字节数组
            byte[] b = new byte[FILE_BYTE_LENGTH];
            int n = 0;
            //从指定位置读取文件字节流
            in.seek(begin);
            //判断文件流读取的边界
            while (in.getFilePointer() <= end && (n = in.read(b)) != -1) {
                //从指定每一份文件的范围，写入不同的文件
                out.write(b, 0, n);
            }
            //定义当前读取文件的指针
            endPointer = in.getFilePointer();
            //关闭输入流
            in.close();
            //关闭输出流
            out.close();
        } catch (Exception e) {
            LOGGER.error("获得文件位置指针报错:{}", e);
        }
        return endPointer;
    }

    /**
     * 文件合并
     *
     * @param file:      指定合并文件
     * @param tempFile:  分割前的文件名
     * @param tempCount: 文件个数
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/27 10:03
     */
    public static boolean merge(String file, String tempFile, int tempCount) {
        String a = tempFile.split(FileUtil.getFileSuffix(file))[0];
        RandomAccessFile raf = null;
        try {
            //申明随机读取文件RandomAccessFile
            raf = new RandomAccessFile(new File(file), "rw");
            //开始合并文件，对应切片的二进制文件
            for (int i = 0; i < tempCount; i++) {
                //读取切片文件
                RandomAccessFile reader = new RandomAccessFile(new File(a + "_" + i + ".tmp"), "r");
                byte[] b = new byte[8 * 1024];
                int n = 0;
                //先读后写
                while ((n = reader.read(b)) != -1) {
                    raf.write(b, 0, n);
                }
            }
        } catch (Exception e) {
            LOGGER.error("文件合并失败:{}", e);
            return false;
        } finally {
            try {
                raf.close();

            } catch (IOException e) {
                LOGGER.error("文件合并失败:{}", e);
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        getSplitFile("D:\\data\\temp\\test.txt", 100);
    }

    /**
     * 获得文件分段信息
     *
     * @param file:
     * @param count:
     * @throws
     * @return: java.util.List<com.zhy.frame.core.vo.SplitFileVo>
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/29 18:55
     */
    public static List<SplitFileVo> getSplit(String file, int count) {

        List<SplitFileVo> result = new ArrayList<>();
        RandomAccessFile raf = null;
        try {
            //获取目标文件 预分配文件所占的空间 在磁盘中创建一个指定大小的文件   r 是只读
            raf = new RandomAccessFile(new File(file), "r");
            //文件的总长度
            long length = raf.length();
            if (count > length) {
                SplitFileVo splitFileVo = new SplitFileVo();
                splitFileVo.setBegin(0);
                splitFileVo.setEnd(length);
                result.add(splitFileVo);
                return result;
            }
            //文件切片后的长度
            long maxSize = length / count;

            for (int i = 0; i < count; i++) {
                SplitFileVo splitFileVo = new SplitFileVo();
                //最后一片单独处理
                long begin = 0;
                if (i > 0) {
                    begin = result.get(i - 1).getEnd();
                }
                long end = begin + maxSize;
                splitFileVo.setBegin(begin);
                splitFileVo.setEnd(end);
                result.add(splitFileVo);
            }
            if (length % count > 0) {
                SplitFileVo splitFileVo = new SplitFileVo();
                splitFileVo.setBegin(result.get(count - 1).getEnd());
                splitFileVo.setEnd(length);
                result.add(splitFileVo);
            }
        } catch (FileNotFoundException e) {
            LOGGER.error("没有找到对应的文件:{}", file);
        } catch (IOException e) {
            LOGGER.error("文件切割失败:{}，错误原因是：{}", file, e);
        } finally {
            try {
                raf.close();
            } catch (IOException e) {
                LOGGER.error("文件切割失败:{}，错误原因是：{}", file, e);
            }
        }
        return result;
    }

}

