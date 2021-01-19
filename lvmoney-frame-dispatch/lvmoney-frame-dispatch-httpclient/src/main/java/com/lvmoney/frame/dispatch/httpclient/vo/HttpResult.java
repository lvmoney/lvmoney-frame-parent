/**
 * 描述:
 * 包名:com.lvmoney.pay.httpclient
 * 版本信息: 版本1.0
 * 日期:2018年10月18日  下午3:39:04
 * Copyright 成都三合力通科技有限公司
 */

package com.lvmoney.frame.dispatch.httpclient.vo;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2018年10月18日 下午3:39:04
 */

public class HttpResult implements Serializable {
    /**
     * @describe:
     * @author: lvmoney /成都三合力通科技有限公司
     * 2018年10月18日下午3:41:10
     */
    public HttpResult(int code, String body) {
        super();
        this.code = code;
        this.body = body;
    }

    /**
     *
     */


    private static final long serialVersionUID = 8801377632466204103L;
    /**
     * 编号
     */
    private int code;
    /**
     * 消息体
     */
    private String body;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
