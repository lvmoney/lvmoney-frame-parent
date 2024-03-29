package com.lvmoney.frame.ai.adtk.ro;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.isolationforest.ro
 * 版本信息: 版本1.0
 * 日期:2022/5/12
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.ai.adtk.ro.item.AdtkFieldItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/12 13:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdtkFieldRo implements Serializable {

    private static final long serialVersionUID = 1698215751770626833L;
    /**
     * 生效时间
     */
    private Long expired;

    /**
     * 孤立森林的字段,针对不同的预测配置唯一的key
     */
    private Map<String, AdtkFieldItem> param;
}
