package com.zhy.frame.cloud.base.vo.jyaml;/**
 * 描述:
 * 包名:com.zhy.k8s.base.vo.jyaml
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
 * @version:v1.0 2019/8/19 9:38
 */
@Data
public class Match implements Serializable {
    private static final long serialVersionUID = -3198120853969766969L;
    private Uri uri;
    private List<Route> route;
    private Fault fault;
    private Headers headers;
}
