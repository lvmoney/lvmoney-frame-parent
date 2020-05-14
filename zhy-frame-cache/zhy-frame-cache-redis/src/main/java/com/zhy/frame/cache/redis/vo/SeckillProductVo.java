package com.zhy.frame.cache.redis.vo;/**
 * 描述:
 * 包名:com.zhy.frame.cache.lock.vo.req
 * 版本信息: 版本1.0
 * 日期:2020/5/3
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/3 13:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeckillProductVo implements Serializable {
    /**
     * 商品编号
     */
    private String code;
    /**
     * 商品总数
     */
    private Integer count;
}
