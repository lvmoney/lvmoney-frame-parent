/**
 * 描述:
 * 包名:com.lvmoney.hotel.ro
 * 版本信息: 版本1.0
 * 日期:2018年11月23日  上午11:12:45
 * Copyright XXXXXX科技有限公司
 */

package com.lvmoney.frame.strategy.drools.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年11月23日 上午11:12:45
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResMessage implements Serializable {
    private static final long serialVersionUID = -1608503615793600599L;
    private double originalPrice;
    private double cost;

}
