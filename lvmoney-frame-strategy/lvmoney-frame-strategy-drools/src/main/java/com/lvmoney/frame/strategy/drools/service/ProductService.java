/**
 * 描述:
 * 包名:com.lvmoney.hotel.service
 * 版本信息: 版本1.0
 * 日期:2018年11月23日  上午10:05:04
 * Copyright 成都三合力通科技有限公司
 */

package com.lvmoney.frame.strategy.drools.service;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年11月23日 上午10:05:04
 */

public interface ProductService {
    /**
     * 判断产品是否能够投放
     *
     * @param price  价格
     * @param cost   成本
     * @param charge 手续费比例
     * @param profit 利润点
     * @return boolean
     * @data 2018年11月23日上午10:08:09
     * @author: lvmoney /成都三合力通科技有限公司
     */
    boolean isPassProduct(double price, double cost, double charge, double profit);

    /**
     * 通过高价获得最高投放价 计算公式：最高价-区间折扣。
     *
     * @param originalPrice 原始高价
     * @return double
     * @data 2018年11月23日上午10:12:39
     * @author: lvmoney /成都三合力通科技有限公司
     */
    double getProductPrice(double originalPrice);
}
