/**
 * 描述:
 * 包名:com.lvmoney.elasticsearch.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月3日  下午2:00:55
 * Copyright XXXXXX科技有限公司
 */

package com.lvmoney.frame.retrieval.elasticsearch.vo;

import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Pageable;

/**
 * @param <T>
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2019年1月3日 下午2:00:55
 */

public class ElasticsearchQueryVo<T> {
    /**
     * elasticsearch 实体
     */
    private T data;
    /**
     * 分页相关
     */
    private Pageable pageable;
    /**
     * 需要查询的字段
     */
    private String field;
    /**
     * 多字段查询
     */
    private Map<String, Float> fields;
    /**
     * 查询字段匹配的值
     */
    private String context;

    private Set<String> contexts;

    private float minimumShouldMatch;
    private int slop;

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getSlop() {
        return slop;
    }

    public void setSlop(int slop) {
        this.slop = slop;
    }

    public Set<String> getContexts() {
        return contexts;
    }

    public void setContexts(Set<String> contexts) {
        this.contexts = contexts;
    }

    public Map<String, Float> getFields() {
        return fields;
    }

    public void setFields(Map<String, Float> fields) {
        this.fields = fields;
    }

    public float getMinimumShouldMatch() {
        return minimumShouldMatch;
    }

    public void setMinimumShouldMatch(float minimumShouldMatch) {
        this.minimumShouldMatch = minimumShouldMatch;
    }
}
