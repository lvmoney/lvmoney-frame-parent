package com.lvmoney.frame.cloud.base.vo.jyaml;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.vo.jyaml
 * 版本信息: 版本1.0
 * 日期:2019/8/19
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/19 10:20
 */
@Data
public class DestinationRuleSpec implements Serializable {
    private static final long serialVersionUID = -4439153165548462613L;
    private String host;
    private TrafficPolicy trafficPolicy;
    private List<Subsets> subsets;
}
