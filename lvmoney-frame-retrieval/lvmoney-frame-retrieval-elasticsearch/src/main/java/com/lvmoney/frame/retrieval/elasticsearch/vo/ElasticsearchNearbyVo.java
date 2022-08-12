/**
 * 描述:
 * 包名:com.lvmoney.elasticsearch.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月14日  下午3:22:27
 * Copyright XXXXXX科技有限公司
 */

package com.lvmoney.frame.retrieval.elasticsearch.vo;

import com.lvmoney.frame.core.vo.LocationVo;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.Pageable;

/**
 * @param <T>
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2019年1月14日 下午3:22:27
 */

public class ElasticsearchNearbyVo<T> {
    private LocationVo locationVo;
    private T data;
    /**
     * elasticsearch 特性 该字段使用格式为：30.657500,104.079278
     */
    private String field;
    private String sortField;
    private Pageable pageable;
    private DistanceUnit unit;
    private double geoDistanceQueryBuilderDistance;
    private SortOrder geoDistanceSortBuilderOrder;

    public LocationVo getLocationVo() {
        return locationVo;
    }

    public void setLocationVo(LocationVo locationVo) {
        this.locationVo = locationVo;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    public DistanceUnit getUnit() {
        return unit;
    }

    public void setUnit(DistanceUnit unit) {
        this.unit = unit;
    }

    public SortOrder getGeoDistanceSortBuilderOrder() {
        return geoDistanceSortBuilderOrder;
    }

    public void setGeoDistanceSortBuilderOrder(SortOrder geoDistanceSortBuilderOrder) {
        this.geoDistanceSortBuilderOrder = geoDistanceSortBuilderOrder;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public double getGeoDistanceQueryBuilderDistance() {
        return geoDistanceQueryBuilderDistance;
    }

    public void setGeoDistanceQueryBuilderDistance(double geoDistanceQueryBuilderDistance) {
        this.geoDistanceQueryBuilderDistance = geoDistanceQueryBuilderDistance;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }
}
