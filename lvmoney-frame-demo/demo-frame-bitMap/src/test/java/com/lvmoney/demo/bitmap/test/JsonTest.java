package com.lvmoney.demo.bitmap.test;/**
 * 描述:
 * 包名:com.lvmoney.demo.bitmap.test
 * 版本信息: 版本1.0
 * 日期:2020/10/23
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.lvmoney.frame.core.util.BitmapUtil;
import org.apache.commons.codec.binary.Base64;
import org.roaringbitmap.RoaringBitmap;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/10/23 11:19
 */
public class JsonTest {
    public static void main(String[] args) {
        RoaringBitmap bitmap = new RoaringBitmap();
        bitmap.add(1L, 500L);
        byte[] byte2 = BitmapUtil.raringBitmap2Bytes(bitmap);

        RoaringBitmap bitmap4 = BitmapUtil.bytes2RoaringBitmap(byte2);
        System.out.println(bitmap4.toString());
        String imgString = Base64.encodeBase64String(byte2);
        byte[] byte24 = Base64.decodeBase64(imgString);

        RoaringBitmap bitmap5 = BitmapUtil.bytes2RoaringBitmap(byte24);
        System.out.println(bitmap5.toString());

        RoaringBitmap bitmap9 = new RoaringBitmap();
        bitmap9.add(1L, 50000000L);
        System.out.println(bitmap9.getSizeInBytes());

    }
}
