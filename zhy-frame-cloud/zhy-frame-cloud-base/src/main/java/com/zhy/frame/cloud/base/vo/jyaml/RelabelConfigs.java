package com.zhy.frame.cloud.base.vo.jyaml;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.vo.jyaml
 * 版本信息: 版本1.0
 * 日期:2019/10/18
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/10/18 14:37
 */
@Data
public class RelabelConfigs implements Serializable {
    private String[] source_labels;
    private String action;
    private String regex;
    private String target_label;
    private String replacement;
}
