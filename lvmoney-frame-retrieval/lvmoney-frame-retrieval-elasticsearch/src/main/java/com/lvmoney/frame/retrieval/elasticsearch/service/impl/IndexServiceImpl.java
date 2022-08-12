package com.lvmoney.frame.retrieval.elasticsearch.service.impl;/**
 * 描述:
 * 包名:com.lvmoney.frame.retrieval.elasticsearch.service.impl
 * 版本信息: 版本1.0
 * 日期:2020/7/9
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.retrieval.common.exception.RetrievalException;
import com.lvmoney.frame.retrieval.elasticsearch.service.IndexService;
import com.lvmoney.frame.retrieval.elasticsearch.vo.ElasticsearchIndexVo;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/7/9 11:12
 */
@Service
public class IndexServiceImpl implements IndexService {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexServiceImpl.class);

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public boolean createDocument(ElasticsearchIndexVo elasticsearchIndexVo) {
        IndexRequest request = new IndexRequest(elasticsearchIndexVo.getIndexName());
        request.source(elasticsearchIndexVo.getJsonStr(), XContentType.JSON);
        IndexResponse indexResponse = null;
        try {
            indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            LOGGER.error("新增document报错:{},indexName:{}", e, elasticsearchIndexVo.getIndexName());
            throw new BusinessException(RetrievalException.Proxy.ES_CREATE_DOCUMENT_ERROR);
        }
        if (DocWriteResponse.Result.CREATED.equals(indexResponse.getResult())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateDocument(ElasticsearchIndexVo elasticsearchIndexVo) {
        UpdateRequest request = new UpdateRequest(elasticsearchIndexVo.getIndexName(), elasticsearchIndexVo.getDocId());
        request.doc(XContentType.JSON, elasticsearchIndexVo.getJsonStr());
        UpdateResponse updateResponse = null;
        try {
            updateResponse = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            LOGGER.error("更新document报错:{},indexName:{},docId:{}", e, elasticsearchIndexVo.getIndexName(), elasticsearchIndexVo.getDocId());
            throw new BusinessException(RetrievalException.Proxy.ES_UPDATE_DOCUMENT_ERROR);
        }
        if (DocWriteResponse.Result.UPDATED.equals(updateResponse.getResult())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteDocument(ElasticsearchIndexVo elasticsearchIndexVo) {
        DeleteRequest request = new DeleteRequest(elasticsearchIndexVo.getIndexName());
        request.id(elasticsearchIndexVo.getDocId());
        DeleteResponse deleteResponse = null;
        try {
            deleteResponse = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            LOGGER.error("删除document报错:{},indexName:{},docId：{}", e, elasticsearchIndexVo.getIndexName(), elasticsearchIndexVo.getDocId());
            throw new BusinessException(RetrievalException.Proxy.ES_DELETE_DOCUMENT_ERROR);
        }
        if (DocWriteResponse.Result.DELETED.equals(deleteResponse.getResult())) {
            return true;
        }
        return false;
    }
}
