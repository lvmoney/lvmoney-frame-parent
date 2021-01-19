package com.lvmoney.frame.cloud.base.vo.jyaml;/**
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
 * @version:v1.0 2019/8/26 10:45
 */
@Data
public class Tcp implements Serializable {
    private static final long serialVersionUID = -3743636470695924568L;
    /**
     * 最大TCP连接数
     */
    private Integer maxConnections;
    /**
     * 连接超时
     */
    private String connectTimeout;
}
