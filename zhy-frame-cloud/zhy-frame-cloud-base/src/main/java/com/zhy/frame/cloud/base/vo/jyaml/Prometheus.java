package com.zhy.frame.cloud.base.vo.jyaml;/**
 * 描述:
 * 包名:com.zhy.k8s.base.vo.jyaml
 * 版本信息: 版本1.0
 * 日期:2019/10/18
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/10/18 14:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prometheus implements Serializable {
    private List<PrometheusJob> scrape_configs;
}
