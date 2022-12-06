package com.lvmoney.frame.ai.pyod.ro;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.isolationforest.ro
 * 版本信息: 版本1.0
 * 日期:2022/5/12
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.ai.enums.DataClassificationEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/5/12 15:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PyodInputRo implements Serializable {
    private static final long serialVersionUID = 6663459515307292934L;
    /**
     * 数据分类
     */
    private DataClassificationEnum classification;
    /**
     * 生效时间
     */
    private Long expired;
    /**
     * 数据
     */
    private Map<String, List> data;
}
