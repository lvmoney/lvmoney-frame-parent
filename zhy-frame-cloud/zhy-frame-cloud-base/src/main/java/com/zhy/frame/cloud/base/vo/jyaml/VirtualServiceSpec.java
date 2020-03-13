package com.zhy.frame.cloud.base.vo.jyaml;/**
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
 * @version:v1.0 2019/8/19 9:34
 */
@Data
public class VirtualServiceSpec implements Serializable {
    private static final long serialVersionUID = -3866674392575304104L;
    private List<String> hosts;
    private List<String> gateways;
    private List<Http> http;
}
