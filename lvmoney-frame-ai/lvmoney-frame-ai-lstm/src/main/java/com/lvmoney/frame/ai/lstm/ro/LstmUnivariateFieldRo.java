package com.lvmoney.frame.ai.lstm.ro;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.lstm.ro
 * 版本信息: 版本1.0
 * 日期:2022/6/30
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.ai.lstm.ro.item.LstmMultivariableField;
import com.lvmoney.frame.ai.lstm.ro.item.LstmUnivariateField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/6/30 11:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LstmUnivariateFieldRo implements Serializable {
    private static final long serialVersionUID = -6498871382476149866L;
    /**
     * 生效时间
     */
    private Long expired;

    /**
     * 孤立森林的字段,针对不同的预测配置唯一的key
     */
    private Map<String, LstmUnivariateField> param;
}
