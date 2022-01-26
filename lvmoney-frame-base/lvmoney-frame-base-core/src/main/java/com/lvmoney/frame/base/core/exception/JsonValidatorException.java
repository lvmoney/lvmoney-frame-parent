package com.lvmoney.frame.base.core.exception;/**
 * 描述:
 * 包名:com.lvmoney.frame.base.core.exception
 * 版本信息: 版本1.0
 * 日期:2021/12/14
 * Copyright XXXXXX科技有限公司
 */


import com.lvmoney.frame.base.core.exception.ExceptionType;
import org.springframework.util.StringUtils;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/12/14 16:18
 */
public class JsonValidatorException extends RuntimeException {


    private static final long serialVersionUID = -6076037770876023101L;

    private Integer code;
    private String description;
    private com.lvmoney.frame.base.core.exception.ExceptionType type;
    private Object data;

    public JsonValidatorException(com.lvmoney.frame.base.core.exception.ExceptionType type) {
        this(type.getCode(), type.getDescription(), type);
    }


    public JsonValidatorException(com.lvmoney.frame.base.core.exception.ExceptionType type, Object data) {
        this(type);
        this.data = data;
    }


    public JsonValidatorException(Integer code, String desc) {
        this.code = code;
        this.description = desc;
    }

    public JsonValidatorException(com.lvmoney.frame.base.core.exception.ExceptionType type, Throwable cause) {
        this(type.getCode(), type.getDescription(), type, cause);
    }

    public JsonValidatorException(com.lvmoney.frame.base.core.exception.ExceptionType type, String desc, Throwable cause) {
        this(type.getCode(), desc, type, cause);
    }

    public JsonValidatorException(com.lvmoney.frame.base.core.exception.ExceptionType type, String desc) {
        this(type.getCode(), desc, type);
    }

    private JsonValidatorException(Integer code, String description, com.lvmoney.frame.base.core.exception.ExceptionType type, Throwable cause) {
        super(description, cause);
        this.code = code;
        this.description = description;
        this.type = type;
    }

    private JsonValidatorException(Integer code, String description, com.lvmoney.frame.base.core.exception.ExceptionType type) {
        super(description);
        this.code = code;
        this.description = description;
        this.type = type;
    }


    @Override
    public String getMessage() {
        if (StringUtils.hasText(this.description)) {
            return this.description;
        } else if (StringUtils.hasText(this.type.getDescription())) {
            return this.type.getDescription();
        } else {
            return null;
        }
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public ExceptionType getType() {
        return type;
    }

    public Object getData() {
        return data;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        JsonValidatorException other = (JsonValidatorException) obj;
        if (type == null) {
            if (other.type != null) {
                return false;
            }
        } else if (!type.equals(other.type)) {
            return false;
        }
        return true;
    }

}
