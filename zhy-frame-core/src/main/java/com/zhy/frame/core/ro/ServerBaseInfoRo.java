package com.zhy.frame.core.ro;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.ro
 * 版本信息: 版本1.0
 * 日期:2019/8/16
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.core.vo.ServerInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/16 15:18
 */
@Data
public class ServerBaseInfoRo implements Serializable {
    private static final long serialVersionUID = -8968720294584442739L;
    private Long expired;
    private Map<String, ServerInfo> data;
}
