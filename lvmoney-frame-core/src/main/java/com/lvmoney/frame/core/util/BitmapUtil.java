package com.lvmoney.frame.core.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.core.util
 * 版本信息: 版本1.0
 * 日期:2020/10/23
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.base.core.exception.CommonException;
import org.apache.commons.codec.binary.Base64;
import org.roaringbitmap.RoaringBitmap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/10/23 13:31
 */
public class BitmapUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(BitmapUtil.class);

    /**
     * RoaringBitmap 序列化
     *
     * @param bitmap:
     * @throws
     * @return: byte[]
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/10/23 13:54
     */

    public static byte[] raringBitmap2Bytes(RoaringBitmap bitmap) {
        //实例化字节数组输出流
        ByteBuffer buffer = ByteBuffer.allocate(bitmap.serializedSizeInBytes());
        bitmap.serialize(buffer);
        return buffer.array();
    }

    /**
     * RoaringBitmap 反序列化
     *
     * @param bytes:
     * @throws
     * @return: byte[]
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/10/23 13:54
     */
    public static RoaringBitmap bytes2RoaringBitmap(byte[] bytes) {
        ByteBuffer buf = ByteBuffer.wrap(bytes);
        RoaringBitmap bitmap = new RoaringBitmap();
        try {
            bitmap.deserialize(buf);
        } catch (IOException e) {
            LOGGER.error("RoaringBitmap反序列化报错{}", e);
            throw new BusinessException(CommonException.Proxy.ROARING_BITMAP_DESERIALIZE_ERROR);
        }
        return bitmap;
    }


    /**
     * RoaringBitmap 转化成base64
     *
     * @param bitmap:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/10/26 9:54
     */

    public static String roaringBitmap2Base64String(RoaringBitmap bitmap) {
        return Base64.encodeBase64String(BitmapUtil.raringBitmap2Bytes(bitmap));
    }

    /**
     * base64转化成RoaringBitmap
     *
     * @param base64String:
     * @throws
     * @return: org.roaringbitmap.RoaringBitmap
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/10/26 9:54
     */

    public static RoaringBitmap base64String2RoaringBitmap(String base64String) {
        try {
            return BitmapUtil.bytes2RoaringBitmap(Base64.decodeBase64(base64String));
        } catch (Exception e) {
            LOGGER.error("{}转换成RoaringBitmap报错{}", base64String, e);
            throw new BusinessException(CommonException.Proxy.BASE64_STRING_2_ROARING_BITMAP_ERROR);
        }
    }

    public static void main(String[] args) {
        String te = "test";
        base64String2RoaringBitmap(te);
    }

}
