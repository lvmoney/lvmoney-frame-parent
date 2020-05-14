/**
 * 描述:
 * 包名:com.zhy.mongo.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月10日  上午9:42:01
 * Copyright xxxx科技有限公司
 */

package com.zhy.frame.oss.gridfs.vo;

import java.io.Serializable;

/**
 * @param <T>
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月10日 上午9:42:01
 */

public class BaseMongoCollective<T> implements Serializable {

    /**
     *
     */


    private static final long serialVersionUID = -6416949928657421085L;

    private T data;
    private String key;
    private String[] keys;
    private T[] datas;
    private String collectionName;
    private String sort;
    private String sortType = "desc";

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String[] getKeys() {
        return keys;
    }

    public void setKeys(String[] keys) {
        this.keys = keys;
    }

    public T[] getDatas() {
        return datas;
    }

    public void setDatas(T[] datas) {
        this.datas = datas;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

}
