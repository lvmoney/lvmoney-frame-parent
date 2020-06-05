package com.zhy.frame.sync.netty.socket.util;

import java.io.File;

/**
 * 删除无效的maven仓库数据
 */
public class DeleteUselessRepository {

    private static String MAVEN_PATH = "D:\\apache-maven-3.6.0\\repository";

    /**
     * 判断是否存在jar
     *
     * @param file
     * @return
     * @author xfcyzq
     * @version 1.0
     */
    private static boolean judge(File file) {
        boolean isHaveJar = false;
        File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            for (File _file : files) {
                if (_file.getName().endsWith(".jar")) {
                    isHaveJar = true;
                }
                if (_file.isDirectory()) {
                    boolean isNextHaveJar = judge(_file);
                    if (isNextHaveJar) {
                        isHaveJar = true;
                    }
                }
            }
        }
        if (!isHaveJar) {
            delete(file);
        }
        return isHaveJar;
    }

    /**
     * 删除操作
     *
     * @param file
     * @author xfcyzq
     * @version 1.0
     */
    public static void delete(File file) {
        File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            for (File f : files) {
                if (f.isDirectory()) {
                    delete(f);
                }
                f.delete();
                System.out.println("已删除：" + f.getAbsolutePath());
            }
        } else {
            file.delete();
            System.out.println("已删除：" + file.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
        File mavenRoot = new File(MAVEN_PATH);
        if (mavenRoot.exists()) {
            File[] files = mavenRoot.listFiles();
            if (files != null && files.length > 0) {
                for (File file : files) {
                    judge(file);
                }
            }
        }
    }
}
