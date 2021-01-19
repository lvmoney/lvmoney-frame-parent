/**
 * 描述:
 * 包名:com.lvmoney.elasticsearch.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月14日  下午3:18:44
 * Copyright 成都三合力通科技有限公司
 */

package com.lvmoney.frame.retrieval.elasticsearch.vo;

import java.util.Map;

/**
 * @param <T>
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2019年1月14日 下午3:18:44
 */

public class ElasticsearchDeleteVo<T> {
    private T data;

    Map<String, Object> fieldMap;

    String id;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, Object> getFieldMap() {
        return fieldMap;
    }

    public void setFieldMap(Map<String, Object> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
