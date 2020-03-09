package com.zhy.frame.base.core.exception;

import org.springframework.util.StringUtils;

/**
 * @describe： * 业务异常,所有与具体业务有关的异常统一抛本类或其子类,使用ExceptionType来确定异常代码,提示信息
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年12月29日 上午11:18:14
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -6076037770876023101L;

    private Integer code;
    private String description;
    private ExceptionType type;

    public BusinessException(ExceptionType type) {
        this(type.getCode(), type.getDescription(), type);
    }

    public BusinessException(Integer code, String desc) {
        this.code = code;
        this.description = desc;
    }

    public BusinessException(ExceptionType type, Throwable cause) {
        this(type.getCode(), type.getDescription(), type, cause);
    }

    public BusinessException(ExceptionType type, String desc, Throwable cause) {
        this(type.getCode(), desc, type, cause);
    }

    public BusinessException(ExceptionType type, String desc) {
        this(type.getCode(), desc, type);
    }

    private BusinessException(Integer code, String description, ExceptionType type, Throwable cause) {
        super(description, cause);
        this.code = code;
        this.description = description;
        this.type = type;
    }

    private BusinessException(Integer code, String description, ExceptionType type) {
        super(description);
        this.code = code;
        this.description = description;
        this.type = type;
    }


    @Override
    public String getMessage() {
        return StringUtils.hasText(this.description) ? this.description
                : StringUtils.hasText(this.type.getDescription()) ? this.type.getDescription() : "";
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
        BusinessException other = (BusinessException) obj;
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
