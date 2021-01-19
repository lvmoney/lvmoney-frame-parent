package com.lvmoney.frame.core.util;

import java.io.*;
import java.util.BitSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
public class BloomFilterUtil implements Serializable {
    private static final long serialVersionUID = -5221305273707291280L;
    private final int[] seeds;
    private final int size;
    private final BitSet notebook;
    private final MisjudgmentRate rate;
    private final AtomicInteger useCount = new AtomicInteger(0);
    private final Double autoClearRate;

    /**
     * 默认中等程序的误判率：MisjudgmentRate.MIDDLE 以及不自动清空数据（性能会有少许提升）
     *
     * @param dataCount 预期处理的数据规模，如预期用于处理1百万数据的查重，这里则填写1000000
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 17:30
     */
    public BloomFilterUtil(int dataCount) {
        this(MisjudgmentRate.MIDDLE, dataCount, null);
    }

    /**
     * BloomFilterUtil
     *
     * @param rate          一个枚举类型的误判率
     * @param dataCount     预期处理的数据规模，如预期用于处理1百万数据的查重，这里则填写1000000
     * @param autoClearRate 自动清空过滤器内部信息的使用比率，传null则表示不会自动清理，
     *                      当过滤器使用率达到100%时，则无论传入什么数据，都会认为在数据已经存在了
     *                      当希望过滤器使用率达到80%时自动清空重新使用，则传入0.8
     * @throws
     * @return: null
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 17:30
     */
    public BloomFilterUtil(MisjudgmentRate rate, int dataCount, Double autoClearRate) {
        long bitSize = rate.seeds.length * dataCount;
        if (bitSize < 0 || bitSize > Integer.MAX_VALUE) {
            throw new RuntimeException("位数太大溢出了，请降低误判率或者降低数据大小");
        }
        this.rate = rate;
        seeds = rate.seeds;
        size = (int) bitSize;
        notebook = new BitSet(size);
        this.autoClearRate = autoClearRate;
    }

    /**
     * add data
     *
     * @param data:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 17:31
     */
    public void add(String data) {
        checkNeedClear();

        for (int i = 0; i < seeds.length; i++) {
            int index = hash(data, seeds[i]);
            setTrue(index);
        }
    }

    /**
     * 校验
     *
     * @param data:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 17:31
     */
    public boolean check(String data) {
        for (int i = 0; i < seeds.length; i++) {
            int index = hash(data, seeds[i]);
            if (!notebook.get(index)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 如果不存在就进行记录并返回false，如果存在了就返回true
     *
     * @param data:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 17:31
     */
    public boolean addIfNotExist(String data) {
        checkNeedClear();

        int[] indexs = new int[seeds.length];
        // 先假定存在
        boolean exist = true;
        int index;

        for (int i = 0; i < seeds.length; i++) {
            indexs[i] = index = hash(data, seeds[i]);

            if (exist) {
                if (!notebook.get(index)) {
                    // 只要有一个不存在，就可以认为整个字符串都是第一次出现的
                    exist = false;
                    // 补充之前的信息
                    for (int j = 0; j <= i; j++) {
                        setTrue(indexs[j]);
                    }
                }
            } else {
                setTrue(index);
            }
        }

        return exist;

    }

    /**
     * clear
     *
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 17:32
     */
    private void checkNeedClear() {
        if (autoClearRate != null) {
            if (getUseRate() >= autoClearRate) {
                synchronized (this) {
                    if (getUseRate() >= autoClearRate) {
                        notebook.clear();
                        useCount.set(0);
                    }
                }
            }
        }
    }

    /**
     * 设置 ture
     *
     * @param index:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 17:32
     */
    public void setTrue(int index) {
        useCount.incrementAndGet();
        notebook.set(index, true);
    }

    /**
     * hash
     *
     * @param data:
     * @param seeds:
     * @throws
     * @return: int
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 17:32
     */
    private int hash(String data, int seeds) {
        char[] value = data.toCharArray();
        int hash = 0;
        if (value.length > 0) {

            for (int i = 0; i < value.length; i++) {
                hash = i * hash + value[i];
            }
        }

        hash = hash * seeds % size;
        // 防止溢出变成负数
        return Math.abs(hash);
    }

    /**
     * 获得使用率
     *
     * @throws
     * @return: double
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 17:32
     */
    public double getUseRate() {
        return (double) useCount.intValue() / (double) size;
    }

    /**
     * 存储 filter
     *
     * @param path:
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 17:33
     */
    public void saveFilterToFile(String path) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 从文件中读取 filter
     *
     * @param path:
     * @throws
     * @return: com.lvmoney.frame.core.util.BloomFilterUtil
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 17:33
     */
    public static BloomFilterUtil readFilterFromFile(String path) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (BloomFilterUtil) ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 清空过滤器中的记录信息
     *
     * @throws
     * @return: void
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/9 17:33
     */
    public void clear() {
        useCount.set(0);
        notebook.clear();
    }

    public MisjudgmentRate getRate() {
        return rate;
    }


    /**
     * @describe： *分配的位数越多，误判率越低但是越占内存
     * <p>
     * 4个位误判率大概是0.14689159766308
     * <p>
     * 8个位误判率大概是0.02157714146322
     * <p>
     * 16个位误判率大概是0.00046557303372
     * <p>
     * 32个位误判率大概是0.00000021167340
     * @author: lvmoney /成都三合力通科技有限公司
     * @version:v1.0 2018年10月30日 下午3:29:38
     */

    public enum MisjudgmentRate {
        /**
         * 每个字符串分配4个位,这里要选取质数，能很好的降低错误率
         */
        VERY_SMALL(new int[]{2, 3, 5, 7}),
        /**
         * 每个字符串分配8个位
         */
        SMALL(new int[]{2, 3, 5, 7, 11, 13, 17, 19}),
        /**
         * 每个字符串分配16个位
         */
        MIDDLE(new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53}),
        /**
         * 每个字符串分配32个位
         */
        HIGH(new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97,
                101, 103, 107, 109, 113, 127, 131});

        private int[] seeds;

        private MisjudgmentRate(int[] seeds) {
            this.seeds = seeds;
        }

        public int[] getSeeds() {
            return seeds;
        }

        public void setSeeds(int[] seeds) {
            this.seeds = seeds;
        }

    }

    public static void main(String[] args) {
//        BloomFilterUtil fileter = new BloomFilterUtil(100000000);
//        fileter.addIfNotExist("1212121212121212121212121212112");
//        String test = AsciiUtil.getSpecifiedLengthString(6);
//        System.out.println("rtest=" + test);
//        if (fileter.addIfNotExist(test + "1")) {
//            System.out.println("test=" + test);
//        }
//        fileter.saveFilterToFile("D:\\data\\temp\\test.txt");

/***测试的时候把这个分隔点的上下分别打开测试******/
        BloomFilterUtil fileter = readFilterFromFile("D:\\data\\temp\\test.txt");
        System.out.println(fileter.getUseRate());

        String test = "000000";
        System.out.println(fileter.addIfNotExist(test));
        System.out.println(fileter.addIfNotExist(test));
        fileter.saveFilterToFile("D:\\data\\temp\\test.txt");
        System.out.println(fileter.addIfNotExist("1111111111111"));
        System.out.println(fileter.addIfNotExist("222222222222222212121212121212121212121"));
        System.out.println(fileter.addIfNotExist("3333333333333333"));
        System.out.println(fileter.addIfNotExist("444444444444444"));
        System.out.println(fileter.addIfNotExist("5555555555555"));
        System.out.println(fileter.addIfNotExist("6666666666666"));
        System.out.println(fileter.addIfNotExist("1111111111111"));
        fileter.saveFilterToFile("D:\\data\\temp\\test.txt");
        fileter = readFilterFromFile("D:\\data\\temp\\test.txt");
        System.out.println(fileter.getUseRate());
        System.out.println(fileter.addIfNotExist("1111111"));
    }
}


