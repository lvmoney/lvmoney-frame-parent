package com.lvmoney.demo.bitmap.test;

import com.lvmoney.frame.core.util.BitmapUtil;
import org.roaringbitmap.RoaringBitmap;

public class Basic {

    public static void main(String[] args) {
//        RoaringBitmap rr = RoaringBitmap.bitmapOf(1, 2, 3, 1000);
//        RoaringBitmap rr2 = new RoaringBitmap();
//        rr2.add(4000L, 4255L);
//        // 返回存储在这个位图中的第j个值。
//        // 提供的值需要比基数小，否则抛出IllegalArgumentException异常。
//        // 最小的值在索引0处。注意，这个函数与rank函数的约定不同，后者在对最小值排序时返回1。
//        // would return the third value or 1000
//        rr.select(3);
//        // Rank返回小于或等于x的整数的数目(Rank(∞)是GetCardinality())。
//        // 如果将最小的值作为参数提供，则该函数将返回1。
//        // 如果提供的值小于最小值，则返回0。
//        // would return the rank of 2, which is index 1
//        rr.rank(2);
//        // will return true
//        rr.contains(1000);
//        // will return false
//        rr.contains(7);
//        // 并集
//        // new bitmap
//        RoaringBitmap rror = RoaringBitmap.or(rr, rr2);
//        //
//        //in-place computation
//        rr.or(rr2);
//        // true
//        boolean equals = rror.equals(rr);
//        if (!equals) {
//            throw new RuntimeException("bug");
//        }
//        // number of values stored?
//        long cardinality = rr.getLongCardinality();
//        System.out.println(cardinality);
//        long size = 0 ;
//        // a "forEach" is faster than this loop, but a loop is possible:
//        for (int i : rr) {
//            System.out.println(i);
//            size++;
//        }
//        // 259
//        System.out.println(size);

        RoaringBitmap rr = new RoaringBitmap();
        RoaringBitmap rr2 = new RoaringBitmap();

        rr.add(0L, 100000000L);
        rr.add(1111111111);


        rr2.add(0L, 100000010L);
        System.out.println(rr2.serializedSizeInBytes());
        System.out.println(rr2.getSizeInBytes());

        BitmapUtil.raringBitmap2Bytes(rr2);
        RoaringBitmap rror = RoaringBitmap.andNot(rr,rr2);
        for (Integer i : rror) {
            System.out.println(i);
        }
    }
}
