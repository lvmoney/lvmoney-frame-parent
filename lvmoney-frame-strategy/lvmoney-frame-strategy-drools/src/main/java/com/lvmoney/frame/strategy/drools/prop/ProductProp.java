/**
 * 描述:
 * 包名:application
 * 版本信息: 版本1.0
 * 日期:2018年11月23日  下午3:30:30
 * Copyright 成都三合力通科技有限公司
 */

package com.lvmoney.frame.strategy.drools.prop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年11月23日 下午3:30:30
 */
@Component
public class ProductProp {
    @Value("${product.xc.profit}")
    private double xcProfit;
    @Value("${product.tb.profit}")
    private double tbProfit;
    @Value("${product.xc.charge}")
    private double xcCharge;
    @Value("${product.tb.charge}")
    private double tbCharge;

    public double getXcProfit() {
        return xcProfit;
    }

    public void setXcProfit(double xcProfit) {
        this.xcProfit = xcProfit;
    }

    public double getTbProfit() {
        return tbProfit;
    }

    public void setTbProfit(double tbProfit) {
        this.tbProfit = tbProfit;
    }

    public double getXcCharge() {
        return xcCharge;
    }

    public void setXcCharge(double xcCharge) {
        this.xcCharge = xcCharge;
    }

    public double getTbCharge() {
        return tbCharge;
    }

    public void setTbCharge(double tbCharge) {
        this.tbCharge = tbCharge;
    }

}
