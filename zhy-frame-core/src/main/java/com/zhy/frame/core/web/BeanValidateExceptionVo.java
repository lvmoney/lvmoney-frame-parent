/**
 * 描述:
 * 包名:com.zhy.pay.exception.validate
 * 版本信息: 版本1.0
 * 日期:2018年10月15日  上午9:58:40
 * Copyright 四川******科技有限公司
 */

package com.zhy.frame.core.web;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月15日 上午9:58:40
 */

public class BeanValidateExceptionVo implements Serializable {

    /**
     *
     */


    private static final long serialVersionUID = -733171260946390125L;
    /**
     *
     */
    private String field;
    /**
     *
     */
    private Object rejectedValue;
    /**
     *
     */
    private String defaultMessage;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

}
