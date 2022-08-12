package com.lvmoney.frame.ai.isolationforest.dto;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.isolationforest.dto
 * 版本信息: 版本1.0
 * 日期:2022/6/15
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/6/15 11:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IsolationForestResultDto implements Serializable {
    private static final long serialVersionUID = -434443658071011128L;
    /**
     * 全部数据
     */
    List<Map<String, String>> all;

    /**
     * 异常数据
     */
    List<Map<String, String>> abnormal;


    /**
     * 正常数据
     */
    List<Map<String, String>> normal;
}
