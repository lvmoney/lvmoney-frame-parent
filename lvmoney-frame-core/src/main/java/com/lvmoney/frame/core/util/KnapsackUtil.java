package com.lvmoney.frame.core.util;/**
 * 描述:
 * 包名:com.lvmoney.frame.core.util
 * 版本信息: 版本1.0
 * 日期:2020/5/25
 * Copyright XXXXXX科技有限公司
 */


/**
 * @describe：背包相关算法
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/25 10:48
 */
public class KnapsackUtil {
    /**
     * 0-1背包问题 每类物品最多只能装一次,求解背包所含物品的最大值
     *
     * @param volume: 背包容量
     * @param kind:   物品种类
     * @param weight: 物品重量
     * @param value:  物品价值
     * @throws
     * @return: int
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/25 10:51
     */
    public static int zeroOneKnapsack(int volume, int kind, int[] weight, int[] value) {

        //初始化动态规划数组int
        int[][] dp = new int[kind + 1][volume + 1];
        //将dp[i][0]和dp[0][j]均置为0
        for (int i = 1; i < kind + 1; i++) {
            for (int j = 1; j < volume + 1; j++) {
                //由于weight和value数组下标都是从0开始,注意第i个物品的重量为weight[i-1],价值为value[i-1]
                if (weight[i - 1] > j) {
                    //如果第i件物品的重量大于背包容量j,则不装入背包
                    dp[i][j] = dp[i - 1][j];
                } else {
                    //判断
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);
                }
            }
        }
        return dp[kind][volume];
    }


    /**
     * 多重背包 每件物品有其对应的件数
     *
     * @param volume:
     * @param kind:
     * @param weight:
     * @param value:
     * @param num:
     * @throws
     * @return: int
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/25 10:52
     */
    public static int multipleKnapsack(int volume, int kind, int[] weight, int[] value, int[] num) {
        //初始化动态规划数组
        int[][] dp = new int[kind + 1][volume + 1];
        for (int i = 1; i < kind + 1; i++) {
            for (int j = 1; j < volume + 1; j++) {
                if (weight[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    //考虑物品的件数限制
                    int maxV = Math.min(num[i - 1], j / weight[i - 1]);
                    for (int k = 0; k < maxV + 1; k++) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - k * weight[i - 1]] + k * value[i - 1]);
                    }
                }
            }
        }
        return dp[kind][volume];
    }

    /**
     * 完全背包 每件物品有无限件
     * 思路分析：
     * 注意这里当考虑放入一个物品 i 时应当考虑还可能继续放入 i，
     * 因此这里是dp[i][j-weight[i]]+value[i], 而不是dp[i-1][j-weight[i]]+value[i]。
     * 放第i件物品。这里的处理和01背包有所不同，因为01背包的每个物品只能选择一个
     * *因此选择放第i件物品就意味着必须转移到dp[i-1][v-w[i]]这个状态；但是完全背包
     * 问题不同，完全背包如果选择放第i件物品之后并不是转移到dp[i-1][v-w[i]]这个状态，
     * 而是转移到dp[i][v-w[i]]，这是因为每种物品可以放任意件（注意有容量的限制，因此
     * 还是有限的），放了第i件物品后还可以继续放第i件物品，直到第二维的v-w[i]无法保
     * 持大于等于0为止。
     *
     * @param volume:
     * @param kind:
     * @param weight:
     * @param value:
     * @throws
     * @return: int
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/5/25 10:53
     */
    public static int completelyKnapsack(int volume, int kind, int[] weight, int[] value) {
        int[][] dp = new int[kind + 1][volume + 1];
        for (int i = 1; i < kind + 1; i++) {
            for (int j = 1; j < volume + 1; j++) {
                if (weight[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - weight[i - 1]] + value[i - 1]);
                }
            }
        }
        return dp[kind][volume];
    }

    public static void main(String[] args) {
        System.out.println(zeroOneKnapsack(100, 5, new int[]{1, 1, 1, 1, 2}, new int[]{10, 10, 10, 10, 20}));

        System.out.println(multipleKnapsack(100, 5, new int[]{1, 1, 1, 1, 2}, new int[]{10, 10, 10, 10, 20}, new int[]{1, 1, 1, 1, 2}));

        System.out.println(completelyKnapsack(100, 5, new int[]{1, 1, 1, 1, 2}, new int[]{10, 10, 10, 10, 20}));

    }
}
