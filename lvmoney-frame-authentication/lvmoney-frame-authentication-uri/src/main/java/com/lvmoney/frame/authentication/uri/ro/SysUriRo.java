package com.lvmoney.frame.authentication.uri.ro;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotations
 * 版本信息: 版本1.0
 * 日期:2019/2/22
 * Copyright 四川******科技有限公司
 */


import com.lvmoney.frame.authentication.uri.vo.SysUriVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUriRo implements Serializable {
    private static final long serialVersionUID = 8570677474297343207L;
    private long expire;
    private Map<String, List<SysUriVo>> data;
}
