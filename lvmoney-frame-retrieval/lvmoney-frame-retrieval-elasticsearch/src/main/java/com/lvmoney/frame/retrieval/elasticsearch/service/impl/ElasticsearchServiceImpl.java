/**
 * 描述:
 * 包名:com.lvmoney.elasticsearch.service.impl
 * 版本信息: 版本1.0
 * 日期:2019年1月14日  上午9:55:45
 * Copyright 成都三合力通科技有限公司
 */

package com.lvmoney.frame.retrieval.elasticsearch.service.impl;

import com.lvmoney.frame.base.core.exception.BusinessException;
import com.lvmoney.frame.core.vo.LocationVo;
import com.lvmoney.frame.retrieval.common.exception.RetrievalException;
import com.lvmoney.frame.retrieval.elasticsearch.service.ElasticsearchService;
import com.lvmoney.frame.retrieval.elasticsearch.vo.*;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.sort.GeoDistanceSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2019年1月14日 上午9:55:45
 */
@Service
public class ElasticsearchServiceImpl implements ElasticsearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchServiceImpl.class);
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;

    private static final float MIN_NUM_0 = 0f;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Object queryStringQuery(ElasticsearchQueryVo elasticsearchQueryVo) {
        Class clazz = elasticsearchQueryVo.getData().getClass();
        //使用queryStringQuery完成单字符串查询
        String content = elasticsearchQueryVo.getContext();
        Pageable pageable = elasticsearchQueryVo.getPageable();
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.queryStringQuery(content)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, clazz);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public Object matchQuery(ElasticsearchQueryVo elasticsearchQueryVo) {
        Class clazz = elasticsearchQueryVo.getData().getClass();
        String content = elasticsearchQueryVo.getContext();
        Pageable pageable = elasticsearchQueryVo.getPageable();
        String field = elasticsearchQueryVo.getField();
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery(field, content)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, clazz);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void save(ElasticsearchSaveVo elasticsearchSaveVo) {
        IndexQuery indexQuery = new IndexQueryBuilder().withObject(elasticsearchSaveVo.getData()).build();
        elasticsearchTemplate.index(indexQuery);
    }

    @Override
    public Object matchPhraseQuery(ElasticsearchQueryVo elasticsearchQueryVo) {
        Class clazz = elasticsearchQueryVo.getData().getClass();
        String content = elasticsearchQueryVo.getContext();
        Pageable pageable = elasticsearchQueryVo.getPageable();
        String field = elasticsearchQueryVo.getField();
        int slop = elasticsearchQueryVo.getSlop();
        if (slop > 0) {
            SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchPhraseQuery(field, content).slop(2)).withPageable(pageable).build();
            return elasticsearchTemplate.queryForList(searchQuery, clazz);
        }
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchPhraseQuery(field, content)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, clazz);
    }


    @Override
    public Object termQuery(ElasticsearchQueryVo elasticsearchQueryVo) {
        Class clazz = elasticsearchQueryVo.getData().getClass();
        String content = elasticsearchQueryVo.getContext();
        Pageable pageable = elasticsearchQueryVo.getPageable();
        String field = elasticsearchQueryVo.getField();
        //不对传来的值分词，去找完全匹配的
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.termQuery(field, content)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, clazz);
    }

    @Override
    public Object termsQuery(ElasticsearchQueryVo elasticsearchQueryVo) {
        Class clazz = elasticsearchQueryVo.getData().getClass();
        String field = elasticsearchQueryVo.getField();
        List<String> contents = new ArrayList<String>(elasticsearchQueryVo.getContexts());
        Pageable pageable = elasticsearchQueryVo.getPageable();
        // 不对传来的值分词，去找完全匹配的
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.termsQuery(field, contents))
                .withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, clazz);
    }

    @Override
    public Object multiMatchQuery(ElasticsearchQueryVo elasticsearchQueryVo) {
        Class clazz = elasticsearchQueryVo.getData().getClass();
        String content = elasticsearchQueryVo.getContext();
        Pageable pageable = elasticsearchQueryVo.getPageable();
        if (pageable == null) {
            throw new BusinessException(RetrievalException.Proxy.ES_QUERY_PAGEABLE_IS_REQUIRED);
        }
        Map<String, Float> fields = elasticsearchQueryVo.getFields();
        MultiMatchQueryBuilder builder = QueryBuilders.multiMatchQuery(content);
        builder.fields(fields);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.multiMatchQuery(content).fields(fields)).withPageable(pageable).build();
        return elasticsearchTemplate.queryForList(searchQuery, clazz);
    }

    @Override
    public Object containQuery(ElasticsearchQueryVo elasticsearchQueryVo) {
        Class clazz = elasticsearchQueryVo.getData().getClass();
        String content = elasticsearchQueryVo.getContext();
        String field = elasticsearchQueryVo.getField();
        float minimumShouldMatch = elasticsearchQueryVo.getMinimumShouldMatch();
        if (minimumShouldMatch > 1) {
            throw new BusinessException(RetrievalException.Proxy.ES_QUERY_PERCENT_IS_ERROR);
        }
        if (new BigDecimal(minimumShouldMatch).equals(new BigDecimal(MIN_NUM_0))) {
            String percent = float2Percent(minimumShouldMatch);
            SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery(field, content).operator(Operator.AND).minimumShouldMatch(percent)).build();
            return elasticsearchTemplate.queryForList(searchQuery, clazz);
        }
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchQuery(field, content).operator(Operator.AND)).build();
        return elasticsearchTemplate.queryForList(searchQuery, clazz);

    }

    private String float2Percent(float res) {
        DecimalFormat df = new DecimalFormat("0.00%");
        return df.format(res);
    }

    @Override
    public Object nearby(ElasticsearchNearbyVo elasticsearchNearbyVo) {
        Class clazz = elasticsearchNearbyVo.getData().getClass();
        LocationVo locationVo = elasticsearchNearbyVo.getLocationVo();
        double lat = Double.parseDouble(locationVo.getLat());
        double lon = Double.parseDouble(locationVo.getLon());
        String filed = elasticsearchNearbyVo.getField();
        double geoDistanceQueryBuilderDistance = elasticsearchNearbyVo.getGeoDistanceQueryBuilderDistance();
        DistanceUnit unit = elasticsearchNearbyVo.getUnit();
        String sortField = elasticsearchNearbyVo.getSortField();
        GeoDistanceQueryBuilder geoDistanceQueryBuilder = QueryBuilders.geoDistanceQuery(filed).point(lat, lon)
                .distance(geoDistanceQueryBuilderDistance, unit);
        SortOrder geoDistanceSortBuilderOrder = elasticsearchNearbyVo.getGeoDistanceSortBuilderOrder();
        GeoDistanceSortBuilder sortBuilder = SortBuilders.geoDistanceSort(sortField, lat, lon)
                .unit(unit)
                .order(geoDistanceSortBuilderOrder);
        Pageable pageable = elasticsearchNearbyVo.getPageable();
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder().withFilter(geoDistanceQueryBuilder).withSort(sortBuilder).withPageable(pageable);
        SearchQuery searchQuery = builder.build();
        return elasticsearchTemplate.queryForList(searchQuery, clazz);


    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public boolean deleteById(ElasticsearchDeleteVo elasticsearchDeleteVo) {
        String id = elasticsearchDeleteVo.getId();
        try {
            elasticsearchTemplate.delete(elasticsearchDeleteVo.getData().getClass(), id);
            return true;
        } catch (Exception e) {
            LOGGER.error("es通过id删除数据报错:{}", e);
            throw new BusinessException(RetrievalException.Proxy.ES_QUERY_PERCENT_IS_ERROR);
        }
    }

    @Override
    public boolean deleteByBoolMatchQuery(ElasticsearchDeleteVo elasticsearchDeleteVo) {
        Map<String, Object> fieldMap = elasticsearchDeleteVo.getFieldMap();
        try {
            DeleteQuery dq = new DeleteQuery();
            BoolQueryBuilder qb = QueryBuilders.boolQuery();
            //字段查询
            for (String key : fieldMap.keySet()) {
                qb.must(QueryBuilders.matchQuery(key, fieldMap.get(key)));
            }
            dq.setQuery(qb);
            elasticsearchTemplate.delete(dq, elasticsearchDeleteVo.getData().getClass());
            return true;
        } catch (Exception e) {
            LOGGER.error("es删除数据报错:{}", e);
            return false;
        }
    }

    @Override
    public boolean deleteByIndex(ElasticsearchDeleteVo elasticsearchDeleteVo) {
        return elasticsearchTemplate.deleteIndex(elasticsearchDeleteVo.getData().getClass());
    }

    @Override
    public long getVersion(GetVersionVo getVersionVo) {
        GetRequest getRequest = new GetRequest(getVersionVo.getIndex(), getVersionVo.getId());

        return elasticsearchTemplate.getClient().get(getRequest).actionGet().getVersion();
    }


}
