package com.lvmoney.frame.ai.pyod.ro;/**
 * 描述:
 * 包名:com.lvmoney.platform.smart.manager.ro
 * 版本信息: 版本1.0
 * 日期:2022/5/12
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.ai.pyod.ro.item.PyodParamItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/12 10:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PyodParamRo implements Serializable {
    private static final long serialVersionUID = -4862918625119123288L;
    /**
     * 生效时间
     */
    private Long expired;

    /**
     * 孤立森林的配置参数,针对不同的预测配置唯一的key
     */
    private Map<String, PyodParamItem> param;

}
