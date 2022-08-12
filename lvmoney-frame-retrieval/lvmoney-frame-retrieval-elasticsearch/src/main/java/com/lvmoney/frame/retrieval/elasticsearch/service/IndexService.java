package com.lvmoney.frame.retrieval.elasticsearch.service;/**
 * 描述:
 * 包名:com.lvmoney.frame.retrieval.elasticsearch.service
 * 版本信息: 版本1.0
 * 日期:2020/7/9
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.retrieval.elasticsearch.vo.ElasticsearchIndexVo;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/7/9 11:11
 */
public interface IndexService {
    /**
     * 创建文档
     *
     * @param elasticsearchIndexVo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/7/9 11:11
     */
    boolean createDocument(ElasticsearchIndexVo elasticsearchIndexVo);

    /**
     * 修改文档
     *
     * @param elasticsearchIndexVo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/7/9 11:12
     */
    boolean updateDocument(ElasticsearchIndexVo elasticsearchIndexVo);

    /**
     * 删除文档
     *
     * @param elasticsearchIndexVo:
     * @throws
     * @return: boolean
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/7/9 11:12
     */
    boolean deleteDocument(ElasticsearchIndexVo elasticsearchIndexVo);
}
