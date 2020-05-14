/**
 * 描述:
 * 包名:com.zhy.elasticsearch.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月14日  上午9:54:51
 * Copyright xxxx科技有限公司
 */

package com.zhy.frame.retrieval.elasticsearch.vo;

import java.io.Serializable;

/**
 * @param <T>
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2019年1月14日 上午9:54:51
 */

public class ElasticsearchSaveVo<T> implements Serializable {

    /**
     *
     */


    private static final long serialVersionUID = -1965288773284898443L;
    T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
