package com.lvmoney.frame.ai.lstm.ro;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.lstm.ro
 * 版本信息: 版本1.0
 * 日期:2022/6/16
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.ai.lstm.enums.DataClassifyEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @describe：lstm 输入的数据
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/6/16 14:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LstmInputRo implements Serializable {
    /**
     * 数据分类
     */
    private DataClassifyEnum classification;
    /**
     * 生效时间
     */
    private Long expired;
    /**
     * 数据
     */
    private Map<String, List> data;
}
