package com.lvmoney.frame.retrieval.elasticsearch.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.retrieval.elasticsearch.vo
 * 版本信息: 版本1.0
 * 日期:2020/6/9
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/9 17:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetVersionVo implements Serializable {
    private String index;
    private String id;
}
