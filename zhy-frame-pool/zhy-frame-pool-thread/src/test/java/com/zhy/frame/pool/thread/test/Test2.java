package com.zhy.frame.pool.thread.test;/**
 * 描述:
 * 包名:com.zhy.frame.pool.thread.test
 * 版本信息: 版本1.0
 * 日期:2020/5/28
 * Copyright XXXXXX科技有限公司
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/28 17:31
 */
public class Test2 {
    private static void writeFile(int N, String fileName) {
        if (N < 1) {
            System.out.println("writeFile please input one integer greater than 0");
            return;
        }

        System.out.println("writeFile start >>>");
        RandomAccessFile file = null;
        try {
            System.out.println("Will write " + N + "GB data ...");
            File f = new File(fileName);
            if (f.exists()) {
                f.delete();
            }
            file = new RandomAccessFile(fileName, "rw");
            final int ONE_G_LENGTH = 1073741824; // 1G
            byte[] b = new byte[ONE_G_LENGTH]; // 1 GB
            b[0] = '0';
            b[b.length - 1] = '1';
            for (int i = 0; i < N; ++i) {
                file.write(b);
                file.seek(file.length());
                System.out.println("write " + (i + 1) + "GB data");
            }
            file.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("writeFile end <<<");
    }

    public static void main(String[] args) {
        Path path = Paths.get("D:\\data\\temp\\t1.txt");
//        byte[] output = new byte[1];
//        output = "AAAACCCC".getBytes();
//        ByteBuffer bf = ByteBuffer.wrap(output);
//        try (FileChannel fc = FileChannel.open(path, WRITE, CREATE)) {
//            fc.position((3L << 30) - 1);
//            fc.write(bf);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String fileName = "D:\\data\\temp\\t1.txt";
        writeFile(1, fileName);
    }
}
