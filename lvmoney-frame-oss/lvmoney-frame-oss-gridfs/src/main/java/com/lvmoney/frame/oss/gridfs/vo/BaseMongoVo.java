/**
 * 描述:
 * 包名:com.lvmoney.mongo.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月10日  上午9:24:54
 * Copyright XXXXXX科技有限公司
 */

package com.lvmoney.frame.oss.gridfs.vo;

import java.io.Serializable;

/**
 * @param <T>
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2019年1月10日 上午9:24:54
 */

public class BaseMongoVo<T> implements Serializable {

    /**
     *
     */


    private static final long serialVersionUID = -3824901230939298794L;
    /**
     * mongo mo
     */
    private T data;
    /**
     * 集合名
     */
    private String collectionName;
    /**
     * 键
     */
    private String key;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

}
