package com.zhy.frame.cloud.base.vo.jyaml;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.vo.jyaml
 * 版本信息: 版本1.0
 * 日期:2019/8/26
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/26 10:49
 */
@Data
public class OutlierDetection implements Serializable {
    private static final long serialVersionUID = -2858536263243954706L;
    /**
     * 将实例从负载均衡池中剔除，需要连续的错误（HTTP 5XX或者TCP断开/超时）次数
     */
    private Integer consecutiveErrors;
    /**
     * 分析是否需要剔除的频率，多久分析一次
     */
    private String interval;
    /**
     * 实例被剔除后，至少多久不得返回负载均衡池
     */
    private String baseEjectionTime;
    /**
     * 负载均衡池中最多有多大比例被剔除
     */
    private Integer maxEjectionPercent;
}
