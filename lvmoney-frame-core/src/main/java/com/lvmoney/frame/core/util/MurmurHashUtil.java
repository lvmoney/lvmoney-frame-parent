package com.lvmoney.frame.core.util;/**
 * 描述:
 * 包名:com.lvmoney.common.util
 * 版本信息: 版本1.0
 * 日期:2019/11/15
 * Copyright XXXXXX科技有限公司
 */


import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/15 9:26
 */
public class MurmurHashUtil {
    public static void main(String[] args) throws Exception {
        System.out.println(MurmurHashUtil.hashUnsigned("chenshuo").toString());
        assertThat(MurmurHashUtil.hashUnsigned("chenshuo").toString()).isEqualTo("5016608279269930526");
        assertThat(MurmurHashUtil.hashUnsigned("shaoguoqing").toString()).isEqualTo("17121371936686143062");
        assertThat(MurmurHashUtil.hashUnsigned("baozenghui").toString()).isEqualTo("5451996895512824982");
        assertThat(MurmurHashUtil.hashUnsigned("05ff62ff6f7749ffff43ffff6673ff65").toString()).isEqualTo("10652549470333968609");
        assertThat(MurmurHashUtil.hashUnsigned("hahahaha").toString()).isEqualTo("15134676900169534748");
        assertThat(MurmurHashUtil.hashUnsigned("hahah1369531321aha5466sfdfaerttedddd56da").toString()).isEqualTo("6439159232526071817");
        assertThat(MurmurHashUtil.hashUnsigned("测试汉字").toString()).isEqualTo("1146745369200541601");
        assertThat(MurmurHashUtil.hashUnsigned("1234566大大21".getBytes("GBK")).toString()).isEqualTo("10129762727109086067");
        assertThat(MurmurHashUtil.hashUnsigned("12345665哦4哦3我的妈呀21".getBytes("GBK")).toString()).isEqualTo("5141842319936259217");
    }

    /**
     * murmur hash算法实现
     *
     * @param key:
     * @throws
     * @return: java.lang.Long
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 16:40
     */
    public static Long hash(byte[] key) {
        ByteBuffer buf = ByteBuffer.wrap(key);
        int seed = 0x1234ABCD;
        ByteOrder byteOrder = buf.order();
        buf.order(ByteOrder.LITTLE_ENDIAN);
        long m = 0xc6a4a7935bd1e995L;
        int r = 47;
        long h = seed ^ (buf.remaining() * m);
        long k;
        while (buf.remaining() >= 8) {
            k = buf.getLong();
            k *= m;
            k ^= k >>> r;
            k *= m;
            h ^= k;
            h *= m;
        }

        if (buf.remaining() > 0) {
            ByteBuffer finish = ByteBuffer.allocate(8).order(
                    ByteOrder.LITTLE_ENDIAN);
            // for big-endian version, do this first:
            // finish.position(8-buf.remaining());
            finish.put(buf).rewind();
            h ^= finish.getLong();
            h *= m;
        }

        h ^= h >>> r;
        h *= m;
        h ^= h >>> r;

        buf.order(byteOrder);
        return h;
    }

    /**
     * String 的 murmur hash
     *
     * @param key:
     * @throws
     * @return: java.lang.Long
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 16:41
     */
    public static Long hash(String key) {
        return hash(key.getBytes());
    }


    /**
     * Long转换成无符号长整型（C中数据类型）
     *
     * @param value:
     * @throws
     * @return: java.math.BigDecimal
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 16:40
     */
    public static BigDecimal readUnsignedLong(long value) {
        if (value >= 0) {
            return new BigDecimal(value);
        }
        long lowValue = value & 0x7fffffffffffffffL;
        return BigDecimal.valueOf(lowValue).add(BigDecimal.valueOf(Long.MAX_VALUE)).add(BigDecimal.valueOf(1));
    }

    /**
     * 返回无符号murmur hash值
     *
     * @param key:
     * @throws
     * @return: java.math.BigDecimal
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 16:40
     */
    public static BigDecimal hashUnsigned(String key) {
        return readUnsignedLong(hash(key));
    }

    /**
     * 字节组murmur hash
     *
     * @throws
     * @return: java.math.BigDecimal
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 16:41
     */
    public static BigDecimal hashUnsigned(byte[] key) {
        return readUnsignedLong(hash(key));
    }
}
