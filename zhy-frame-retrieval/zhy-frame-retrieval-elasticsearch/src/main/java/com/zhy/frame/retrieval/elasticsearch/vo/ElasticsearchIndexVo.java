package com.zhy.frame.retrieval.elasticsearch.vo;/**
 * 描述:
 * 包名:com.zhy.frame.retrieval.elasticsearch.vo
 * 版本信息: 版本1.0
 * 日期:2020/7/9
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/7/9 13:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElasticsearchIndexVo implements Serializable {
    private static final long serialVersionUID = 1312285742354668725L;
    /**
     * 索引名称
     */
    private String indexName;
    /**
     * 文档id
     */
    private String docId;
    /**
     * 实体对象
     */
    private String jsonStr;
}
