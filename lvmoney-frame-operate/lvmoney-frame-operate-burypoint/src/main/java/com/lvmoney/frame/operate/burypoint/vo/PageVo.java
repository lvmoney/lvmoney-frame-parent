package com.lvmoney.frame.operate.burypoint.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.operate.burypoint.vo
 * 版本信息: 版本1.0
 * 日期:2020/7/14
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/7/14 14:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo implements Serializable {
    private static final long serialVersionUID = -5130495075644514009L;
    /**
     * 坑位id
     */
    private String pitPositionId;

    /**
     * 坑位的位置
     */
    private String pitPositionNum;


    /**
     * 来源店铺id
     */
    private String sourceStoreId;


    /**
     * 检索关键词
     */
    private String keyword;


    /**
     * 推荐的id或者url
     */
    private String recommend;


    /**
     * 商品id
     */
    private String prodId;


    /**
     * 商品单价
     */
    private BigDecimal price;


    /**
     * 店铺id
     */
    private String storeId;

}
