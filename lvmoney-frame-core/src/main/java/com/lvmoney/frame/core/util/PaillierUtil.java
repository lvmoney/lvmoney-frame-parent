package com.lvmoney.frame.core.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.core.util
 * 版本信息: 版本1.0
 * 日期:2020/5/25
 * Copyright XXXXXX科技有限公司
 */


import java.math.BigInteger;
import java.util.Random;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/25 15:18
 */
public class PaillierUtil {
    /**
     * 默认长度 16
     */
    private final static int DEFAULT_BIT_LENGTH = 32;
    /**
     * 默认certainty 64
     */
    private final static int DEFAULT_CERTAINTY = 64;
    /**
     * 选取两个较大的质数xPrime与yPrime，
     */
    private static BigInteger xPrime;
    /**
     * 选取两个较大的质数xPrime与yPrime，
     */
    private static BigInteger yPrime;
    /**
     * lambda是p-1与q-1的最小公倍数
     */
    private static BigInteger lambda;

    /**
     * n是p与q的乘积
     */
    public static BigInteger n;

    /**
     * n*n
     */
    public static BigInteger nSquare;
    private static BigInteger g;

    public PaillierUtil(int bitLengthVal, int certainty) {
        key(bitLengthVal, certainty);
    }

    public PaillierUtil() {
        key(DEFAULT_BIT_LENGTH, DEFAULT_CERTAINTY);
    }

    /**
     * 构造加密基础数据
     *
     * @param bitLength:
     * @param certainty:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/25 16:09
     */
    private void key(int bitLength, int certainty) {
        //随机构造两个大素数，详情参见API，BigInteger的构造方法
        xPrime = new BigInteger(bitLength / 2, certainty, new Random());
        yPrime = new BigInteger(bitLength / 2, certainty, new Random());

        //n=p*q;
        n = xPrime.multiply(yPrime);

        //nsquare=n*n;
        nSquare = n.multiply(n);
        g = new BigInteger("4");

        //求p-1与q-1的乘积除于p-1于q-1的最大公约数
        lambda = xPrime.subtract(BigInteger.ONE).multiply(yPrime.subtract(BigInteger.ONE))
                .divide(xPrime.subtract(BigInteger.ONE).gcd(yPrime.subtract(BigInteger.ONE)));

        //检测g是某满足要求
        if (g.modPow(lambda, nSquare).subtract(BigInteger.ONE).divide(n).gcd(n).intValue() != 1) {
            System.out.println("g的选取不合适!");
            System.exit(1);
        }
    }

    /**
     * 给定r的加密
     *
     * @param m:
     * @param r:
     * @throws
     * @return: java.math.BigInteger
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/25 16:06
     */
    public BigInteger encryption(BigInteger m, BigInteger r) {
        return g.modPow(m, nSquare).multiply(r.modPow(n, nSquare)).mod(nSquare);
    }

    /**
     * 随机生成r的加密
     *
     * @param m:
     * @throws
     * @return: java.math.BigInteger
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/25 16:06
     */
    public static BigInteger encryption(BigInteger m) {
        BigInteger r = new BigInteger(DEFAULT_BIT_LENGTH, new Random());
        return g.modPow(m, nSquare).multiply(r.modPow(n, nSquare)).mod(nSquare);
    }

    /**
     * 解密
     *
     * @param c:
     * @throws
     * @return: java.math.BigInteger
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/25 16:06
     */
    public static BigInteger decrypt(BigInteger c) {
        BigInteger u = g.modPow(lambda, nSquare).subtract(BigInteger.ONE).divide(n).modInverse(n);
        return c.modPow(lambda, nSquare).subtract(BigInteger.ONE).divide(n).multiply(u).mod(n);
    }

    /**
     * main function
     *
     * @param str intput string
     */
    public static void main(String[] str) {
        PaillierUtil paillier = new PaillierUtil();
        //创建两个大整数m1,m2:
        BigInteger m1 = new BigInteger("20");
        BigInteger m2 = new BigInteger("60");
        System.out.println("原文是:");
        System.out.println(m1 + "和" + m2);

        //将m1,m2加密得到em1,em2:
        BigInteger em1 = PaillierUtil.encryption(m1);
        BigInteger em2 = PaillierUtil.encryption(m2);

        //加密后的结果
        System.out.println("m1加密结果" + em1);
        System.out.println("m2加密结果" + em2);

        //解密后的结果
        System.out.println("m1解密结果" + PaillierUtil.decrypt(em1));
        System.out.println("m2解密结果" + PaillierUtil.decrypt(em2).toString());

        /**
         * paillier性质
         * */
        //加法同态
        // m1+m2,求明文数值的和
        System.out.println("**************************求和********************");
        BigInteger sum_m1m2 = m1.add(m2).mod(PaillierUtil.n);
        System.out.println("明文数值的和 : " + sum_m1m2.toString());
        System.out.println("测试" + m1.add(m2));

        // em1+em2，求密文数值的和
        BigInteger product_em1em2 = em1.multiply(em2).mod(PaillierUtil.nSquare);
        System.out.println("密文和: " + product_em1em2.toString());
        System.out.println("密文和解密: " + PaillierUtil.decrypt(product_em1em2).toString());


        // 数乘同态
        System.out.println("***************************数乘*********************");
        //做乘法，先将两个数相乘，然后对n求模
        BigInteger multiply_m1m2 = m1.multiply(m2).mod(PaillierUtil.n);
        System.out.println("两个大整数相乘: " + multiply_m1m2.toString());
        System.out.println("测试" + m1.multiply(m2));

        //数乘，密文数，乘上某个明文数C的密文值等于=密文数的C次方对n平方求模
        BigInteger multiply_em1em2 = em1.modPow(m2, PaillierUtil.nSquare);
        System.out.println("数乘密文值: " + multiply_em1em2.toString());
        System.out.println("数乘密文值解密: " + PaillierUtil.decrypt(multiply_em1em2).toString());

    }

}
