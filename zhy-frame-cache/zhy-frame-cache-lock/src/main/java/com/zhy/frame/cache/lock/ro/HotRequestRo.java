package com.zhy.frame.cache.lock.ro;/**
 * 描述:
 * 包名:com.zhy.frame.cache.lock.ro
 * 版本信息: 版本1.0
 * 日期:2020/5/18
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/18 16:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotRequestRo<Q, R> implements Serializable {
    private static final long serialVersionUID = 5284336712963666131L;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 开始时间
     */
    private long start;
    /**
     * 访问计数
     */
    private long counter;

    /**
     * 返回数据
     */
    private R r;

    /**
     * 请求参数
     */
    private Q q;
    /**
     * 失效时间
     */
    private Long expired;
}
