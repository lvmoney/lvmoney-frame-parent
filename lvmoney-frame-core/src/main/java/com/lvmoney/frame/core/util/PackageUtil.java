package com.lvmoney.frame.core.util;/**
 * 描述:
 * 包名:com.chdriver.frame.core.util
 * 版本信息: 版本1.0
 * 日期:2021/9/23
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.core.vo.PackageIndexVo;
import com.lvmoney.frame.core.vo.PackageVo;
import com.lvmoney.frame.core.vo.item.PackageVoItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @describe：问题描述： 一个背包的总容量为V, 现在有N类物品, 第i类物品的重量为weight[i], 价值为value[i]
 * 那么往该背包里装东西,怎样装才能使得最终包内物品的总价值最大。这里装物品主要由三种装法：
 * 1、0-1背包：每类物品最多只能装一次
 * 2、多重背包：每类物品都有个数限制，第i类物品最多可以装num[i]次
 * 3、完全背包：每类物品可以无限次装进包内
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/9/23 16:25
 */
public class PackageUtil {

    /**
     * 0-1背包问题
     *
     * @param v       背包容量
     * @param n       物品种类
     * @param weight  物品重量
     * @param value   物品价值
     * @param v:
     * @param n:
     * @param weight:
     * @param value:
     * @throws
     * @return: com.chdriver.frame.core.vo.PackageVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/9/24 13:05
     */
    public static PackageVo zeroOnePack(int v, int n, int[] weight, int[] value) {
        //初始化动态规划数组
        int[][] dp = new int[n + 1][v + 1];
        //为了便于理解,将dp[i][0]和dp[0][j]均置为0，从1开始计算
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < v + 1; j++) {
                //如果第i件物品的重量大于背包容量j,则不装入背包
                //由于weight和value数组下标都是从0开始,故注意第i个物品的重量为weight[i-1],价值为value[i-1]
                if (weight[i - 1] > j)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);
            }
        }
        //则容量为V的背包能够装入物品的最大值为
        int maxValue = dp[n][v];
        //逆推找出装入背包的所有商品的编号
        int j = v;
        List<PackageVoItem> data = new ArrayList<>();
        List<PackageIndexVo> item = new ArrayList<>();
        for (int i = n; i > 0; i--) {
            //若果dp[i][j]>dp[i-1][j],这说明第i件物品是放入背包的
            if (dp[i][j] > dp[i - 1][j]) {
                item.add(new PackageIndexVo(i));
                j = j - weight[i - 1];
            }
            if (j == 0)
                break;
        }
        Map<Integer, List<PackageIndexVo>> itemMap = item.stream().collect(Collectors.groupingBy(PackageIndexVo::getNum));
        itemMap.forEach((k, val) -> {
            int itemNum = val.size();
            PackageVoItem packageVoItem = new PackageVoItem();
            packageVoItem.setNumber(itemNum);
            packageVoItem.setWeight(weight[k - 1]);
            packageVoItem.setValue(value[k - 1]);
            data.add(packageVoItem);
        });
        PackageVo packageVo = new PackageVo();
        packageVo.setValue(maxValue);
        packageVo.setCount(itemMap.size());
        packageVo.setData(data);
        return packageVo;
    }

    /**
     * 多重背包
     * 每类物品都有个数限制，第i类物品最多可以装num[i]次
     *
     * @param v:
     * @param n:
     * @param weight:
     * @param value:
     * @param num:
     * @throws
     * @return: com.chdriver.frame.core.vo.PackageVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/9/24 13:05
     */
    public static PackageVo manyPack(int v, int n, int[] weight, int[] value, int[] num) {
        //初始化动态规划数组
        int[][] dp = new int[n + 1][v + 1];
        //为了便于理解,将dp[i][0]和dp[0][j]均置为0，从1开始计算
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < v + 1; j++) {
                //如果第i件物品的重量大于背包容量j,则不装入背包
                //由于weight和value数组下标都是从0开始,故注意第i个物品的重量为weight[i-1],价值为value[i-1]
                if (weight[i - 1] > j)
                    dp[i][j] = dp[i - 1][j];
                else {
                    //考虑物品的件数限制
                    int maxV = Math.min(num[i - 1], j / weight[i - 1]);
                    for (int k = 0; k < maxV + 1; k++) {
                        dp[i][j] = dp[i][j] > Math.max(dp[i - 1][j], dp[i - 1][j - k * weight[i - 1]] + k * value[i - 1]) ? dp[i][j] : Math.max(dp[i - 1][j], dp[i - 1][j - k * weight[i - 1]] + k * value[i - 1]);
                    }
                }
            }
        }
        //则容量为V的背包能够装入物品的最大值为
        int maxValue = dp[n][v];
        int j = v;
        List<PackageVoItem> data = new ArrayList<>();
        List<PackageIndexVo> item = new ArrayList<>();
        for (int i = n; i > 0; i--) {
            //若果dp[i][j]>dp[i-1][j],这说明第i件物品是放入背包的
            while (dp[i][j] > dp[i - 1][j]) {
                item.add(new PackageIndexVo(i));
                j = j - weight[i - 1];
            }
            if (j == 0)
                break;
        }
        Map<Integer, List<PackageIndexVo>> itemMap = item.stream().collect(Collectors.groupingBy(PackageIndexVo::getNum));
        itemMap.forEach((k, val) -> {
            int itemNum = val.size();
            PackageVoItem packageVoItem = new PackageVoItem();
            packageVoItem.setNumber(itemNum);
            packageVoItem.setWeight(weight[k - 1]);
            packageVoItem.setValue(value[k - 1]);
            data.add(packageVoItem);
        });
        PackageVo packageVo = new PackageVo();
        packageVo.setValue(maxValue);
        packageVo.setCount(itemMap.size());
        packageVo.setData(data);
        return packageVo;
    }

    /**
     * 第二类背包：完全背包
     * 思路分析：
     * 01背包问题是在前一个子问题（i-1种物品）的基础上来解决当前问题（i种物品），
     * 向i-1种物品时的背包添加第i种物品；而完全背包问题是在解决当前问题（i种物品）
     * 向i种物品时的背包添加第i种物品。
     * 推公式计算时，f[i][y] = max{f[i-1][y], (f[i][y-weight[i]]+value[i])}，
     * 注意这里当考虑放入一个物品 i 时应当考虑还可能继续放入 i，
     * 因此这里是f[i][y-weight[i]]+value[i], 而不是f[i-1][y-weight[i]]+value[i]。
     *
     * @param v
     * @param n
     * @param weight
     * @param value
     * @param v:
     * @param n:
     * @param weight:
     * @param value:
     * @throws
     * @return: com.chdriver.frame.core.vo.PackageVo
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2021/9/24 13:05
     */
    public static PackageVo completePack(int v, int n, int[] weight, int[] value) {

        //初始化动态规划数组
        int[][] dp = new int[n + 1][v + 1];
        //为了便于理解,将dp[i][0]和dp[0][j]均置为0，从1开始计算
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < v + 1; j++) {
                //如果第i件物品的重量大于背包容量j,则不装入背包
                //由于weight和value数组下标都是从0开始,故注意第i个物品的重量为weight[i-1],价值为value[i-1]
                if (weight[i - 1] > j)
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - weight[i - 1]] + value[i - 1]);
            }
        }
        //则容量为V的背包能够装入物品的最大值为
        int maxValue = dp[n][v];
        int j = v;
        List<PackageVoItem> data = new ArrayList<>();
        List<PackageIndexVo> item = new ArrayList<>();
        for (int i = n; i > 0; i--) {
            //若果dp[i][j]>dp[i-1][j],这说明第i件物品是放入背包的
            while (dp[i][j] > dp[i - 1][j]) {
                item.add(new PackageIndexVo(i));
                j = j - weight[i - 1];
            }
            if (j == 0)
                break;
        }
        Map<Integer, List<PackageIndexVo>> itemMap = item.stream().collect(Collectors.groupingBy(PackageIndexVo::getNum));
        itemMap.forEach((k, val) -> {
            int itemNum = val.size();
            PackageVoItem packageVoItem = new PackageVoItem();
            packageVoItem.setNumber(itemNum);
            packageVoItem.setWeight(weight[k - 1]);
            packageVoItem.setValue(value[k - 1]);
            data.add(packageVoItem);
        });
        PackageVo packageVo = new PackageVo();
        packageVo.setValue(maxValue);
        packageVo.setCount(itemMap.size());
        packageVo.setData(data);
        return packageVo;
    }


/*    public static void main(String[] args) {
        int[] weight = {6, 3, 4, 5, 22};
        int[] value = {6, 3, 4, 5, 22};
        int[] num = {3, 3, 3, 3, 3};
//        System.out.println(manyPack(100, 4, weight, value, num));

        System.out.println(completePack(100, 4, weight, value));

        System.out.println(zeroOnePack(100, 5, weight, value));
    }*/
}