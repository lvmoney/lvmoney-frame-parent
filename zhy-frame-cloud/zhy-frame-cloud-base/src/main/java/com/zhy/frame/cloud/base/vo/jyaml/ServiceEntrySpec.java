package com.zhy.frame.cloud.base.vo.jyaml;/**
 * 描述:
 * 包名:com.zhy.k8s.base.vo.jyaml
 * 版本信息: 版本1.0
 * 日期:2019/8/24
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/24 17:12
 */
@Data
public class ServiceEntrySpec implements Serializable {
    private static final long serialVersionUID = 2844209701452327861L;
    private List<String> hosts;
    Ports ports;
}
