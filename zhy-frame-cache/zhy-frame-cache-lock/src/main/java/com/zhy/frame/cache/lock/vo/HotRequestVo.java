package com.zhy.frame.cache.lock.vo;/**
 * 描述:
 * 包名:com.zhy.frame.cache.caffeine.vo
 * 版本信息: 版本1.0
 * 日期:2020/5/16
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/16 10:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotRequestVo<Q, R> implements Serializable {
    /**
     * key
     */
    private String key;
    /**
     * 请求参数
     */
    private Q q;
    /**
     * 返回数据
     */
    private R r;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 开始时间
     */
    private Long start;
    /**
     * 访问计数
     */
    private Long counter;

}
