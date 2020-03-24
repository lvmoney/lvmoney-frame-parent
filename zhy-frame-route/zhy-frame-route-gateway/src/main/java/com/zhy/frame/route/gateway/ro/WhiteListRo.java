package com.zhy.frame.route.gateway.ro;/**
 * 描述:
 * 包名:com.zhy.k8s.gateway.ro
 * 版本信息: 版本1.0
 * 日期:2019/8/20
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.route.gateway.vo.WhiteListVo;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @describe：redis白名单对象
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/20 9:09
 */
@Data
public class WhiteListRo implements Serializable {
    private static final long serialVersionUID = -4028517377859292566L;
    private Long expired;
    private Map<String, WhiteListVo> data;

}
