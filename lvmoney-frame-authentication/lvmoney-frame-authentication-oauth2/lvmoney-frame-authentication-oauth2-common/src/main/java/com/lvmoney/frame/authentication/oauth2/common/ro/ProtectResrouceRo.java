package com.lvmoney.frame.authentication.oauth2.common.ro;/**
 * 描述:
 * 包名:com.lvmoney.frame.authentication.oauth2.resource.ro
 * 版本信息: 版本1.0
 * 日期:2019/12/12
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.authentication.oauth2.common.vo.ProtectResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/12/12 14:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProtectResrouceRo implements Serializable {
    /**
     * 失效时间
     */
    private Long expired;
    /**
     * 链接列表
     */
    List<ProtectResource> data;
}
