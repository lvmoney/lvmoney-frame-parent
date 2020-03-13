package com.zhy.frame.cache.lock.vo.req;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/3/27
 * Copyright 四川******科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdLockStockReqVo implements Serializable {
    private static final long serialVersionUID = 3753756005445385676L;
    private String prodId;
    private Long expire;
    /**
     * 扣减数量
     */
    private Integer num;
}
