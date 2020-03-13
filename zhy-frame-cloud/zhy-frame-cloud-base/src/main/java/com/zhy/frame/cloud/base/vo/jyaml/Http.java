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
 * @version:v1.0 2019/8/19 9:37
 */
@Data
public class Http implements Serializable {
    private static final long serialVersionUID = 4290884800847765555L;
    private List<Match> prefix;
    private List<Route> route;
    private List<Paths> paths;
    private String timeout;
    private Retries retries;
    /**
     * 最大等待转发的、从应用容器发来的HTTP请求数
     */
    private Integer http1MaxPendingRequests;
    /**
     * 每个TCP连接可以被多少请求共享使用（重用），设置为1则禁用keep-alive
     */
    private Integer maxRequestsPerConnection;
    /**
     * HTTP2最大请求数
     */
    private Integer http2MaxRequests;

    private Integer maxRetries;
}
