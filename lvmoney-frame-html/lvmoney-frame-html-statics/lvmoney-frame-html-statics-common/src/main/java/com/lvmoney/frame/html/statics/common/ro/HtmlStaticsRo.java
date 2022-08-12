package com.lvmoney.frame.html.statics.common.ro;/**
 * 描述:
 * 包名:com.lvmoney.frame.html.statics.ro
 * 版本信息: 版本1.0
 * 日期:2020/4/21
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.html.statics.common.vo.HtmlStaticsVo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/4/21 13:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HtmlStaticsRo implements Serializable {
    /**
     * 失效时间
     */
    private Long expired;
    /**
     * 数据
     */
    private Map<String, HtmlStaticsVo> data;
}
