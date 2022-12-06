package com.lvmoney.frame.ai.lgbm.ro.item;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.lstm.ro.item
 * 版本信息: 版本1.0
 * 日期:2022/6/21
 * Copyright XXXXXX科技有限公司
 */


import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/6/21 11:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LgbmModelBestParams implements Serializable {
    private static final long serialVersionUID = 6483761431983344289L;
    /**
     * batchSize
     */
    @JSONField(name = "batch_size")
    private Integer batchSize;
    /**
     * denseUnits
     */
    private Integer denseUnits;
    /**
     * epochs
     */
    private Integer epochs;
    /**
     * listUnits
     */
    private Integer listUnits;
    /**
     * lookBack
     */
    @JSONField(name = "look_back")
    private Integer lookBack;
    /**
     * loss
     */
    private String loss;
    /**
     * optimizer
     */
    private String optimizer;

    /**
     * dropout
     */
    private Float dropout;
    /**
     * dimension
     */
    private Integer dimension;

}
