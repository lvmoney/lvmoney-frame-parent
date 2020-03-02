package com.zhy.frame.route.gateway.vo;/**
 * 描述:
 * 包名:com.lvmoney.k8s.gateway.ro
 * 版本信息: 版本1.0
 * 日期:2019/8/20
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/20 9:10
 */
@Data
public class WhiteListVo implements Serializable {
    /**
     * 服务名称
     */
    private String serverName;
    /**
     * 白名单网段
     */
    private Set<String> networkSegment;


}
