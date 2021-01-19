package com.lvmoney.demo.bitmap.test;

import com.googlecode.javaewah.EWAHCompressedBitmap;

import java.io.*;

public class TestJavaEWAH {
    public static void main(String[] args) {
        EWAHCompressedBitmap ewahBitmap1 = EWAHCompressedBitmap.bitmapOf(0, 2, 55, 64, 1111111111);
        EWAHCompressedBitmap ewahBitmap2 = EWAHCompressedBitmap.bitmapOf(1, 3, 64,1111111111);
        //bitmap 1: {0,2,55,64,1073741824}
        System.out.println("bitmap 1: " + ewahBitmap1);
        //bitmap 2: {1,3,64,1073741824}
        System.out.println("bitmap 2: " + ewahBitmap2);

        //是否包含value=64，返回为true
        System.out.println(ewahBitmap1.get(64));

        //获取value的个数，个数为5
        System.out.println(ewahBitmap1.cardinality());

        //遍历所有value
        ewahBitmap1.forEach(integer -> {
            System.out.println(integer);
        });


        //进行位或运算
        EWAHCompressedBitmap orbitmap = ewahBitmap1.or(ewahBitmap2);
        //返回bitmap 1 OR bitmap 2: {0,1,2,3,55,64,1073741824}
        System.out.println("bitmap 1 OR bitmap 2: " + orbitmap);
        //memory usage: 40 bytes
        System.out.println("memory usage: " + orbitmap.sizeInBytes() + " bytes");

        //进行位与运算
        EWAHCompressedBitmap andbitmap = ewahBitmap1.and(ewahBitmap2);
        //返回bitmap 1 AND bitmap 2: {64,1073741824}
        System.out.println("bitmap 1 AND bitmap 2: " + andbitmap);
        //memory usage: 32 bytes
        System.out.println("memory usage: " + andbitmap.sizeInBytes() + " bytes");

        //序列化与反序列化
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ewahBitmap1.serialize(new DataOutputStream(bos));
            EWAHCompressedBitmap ewahBitmap1new = new EWAHCompressedBitmap();
            byte[] bout = bos.toByteArray();
            ewahBitmap1new.deserialize(new DataInputStream(new ByteArrayInputStream(bout)));
            System.out.println("bitmap 1 (recovered) : " + ewahBitmap1new);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
