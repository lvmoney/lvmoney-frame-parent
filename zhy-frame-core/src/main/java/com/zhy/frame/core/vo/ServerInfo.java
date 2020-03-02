package com.zhy.frame.core.vo;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.info
 * 版本信息: 版本1.0
 * 日期:2019/8/16
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/16 15:21
 */
@Data
public class ServerInfo implements Serializable {
    private static final long serialVersionUID = 3504451430923278739L;
    /**
     * 服务名称
     */
    private String serverName;
    /**
     * 端口
     */
    private int port;
    /**
     * ip
     */
    private String ip;
    /**
     * http协议
     */
    private String httpScheme;
    /**
     * 访问调用地址
     */
    private String uri;
    /**
     * 发布出来的接口，可被外部服务调用
     */
    private Set<String> releaseServer;
    /**
     * 服务类型,具体详见枚举InternalService
     */
    private String internalService;
}
