package com.lvmoney.frame.cloud.base.vo.jyaml;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.vo.jyaml
 * 版本信息: 版本1.0
 * 日期:2019/8/24
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/24 16:26
 */
@Data
public class Retries implements Serializable {
    private static final long serialVersionUID = 3743926408271005671L;
    /**
     * 最多重试次数
     */
    private Integer attempts;
    /**
     * 每次重试的超时
     */
    private String perTryTimeout;
}
