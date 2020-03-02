package com.zhy.frame.base.core.api;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhy.frame.base.core.exception.BusinessException;
import com.zhy.frame.base.core.exception.ExceptionType;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.zhy.frame.base.core.constant.BaseConstant.API_RESULT_DATA_DATE_FORMART;
import static com.zhy.frame.base.core.constant.BaseConstant.SUCCESS_DEFAULT_CODE;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class ApiResult<T> implements Serializable {
    private static final int CODE_SUCCESS = SUCCESS_DEFAULT_CODE;
    private static final long serialVersionUID = 1L;

    private Integer code;

    private boolean success;

    private String msg;

    private T data;
    @JSONField(format = API_RESULT_DATA_DATE_FORMART)
    @JsonFormat(pattern = API_RESULT_DATA_DATE_FORMART)
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ApiResult() {
        this.setCode(CODE_SUCCESS
        );
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public ApiResult(T data) {
        this(CODE_SUCCESS, null, data);
    }

    public ApiResult(Integer code, String msg) {
        this(code, msg, null);
    }

    public ApiResult(String key, T value) {
        this.date = new Date();
        this.code = 0;
        Map<String, T> m = new HashMap<String, T>(1);
        m.put(key, value);
        data = (T) m;
    }

    public ApiResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        date = new Date();
    }

    public ApiResult(BusinessException ex) {
        ExceptionType type = ex.getType();
        String exmsg = ex.getDescription();
        String typeExmsg = type.getDescription();
        this.code = type.getCode();
        if (exmsg == null || "".equals(exmsg)) {
            this.msg = typeExmsg;
        } else {
            this.msg = exmsg;
        }
    }

    public static ApiResult success(Object object) {
        ApiResult result = new ApiResult();
        result.setSuccess(true);
        result.setMsg("success");
        result.setData(object);
        result.setDate(new Date());
        return result;
    }


    @SuppressWarnings({"rawtypes"})
    public static ApiResult success() {
        return success(null);
    }


    @SuppressWarnings("rawtypes")
    public static ApiResult error(Integer code, String msg) {
        ApiResult result = new ApiResult();
        result.setCode(code);
        result.setSuccess(false);
        result.setMsg(msg);
        result.setDate(new Date());
        return result;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static ApiResult error(Integer code, String msg, Object obj) {
        ApiResult result = new ApiResult();
        result.setCode(code);
        result.setSuccess(false);
        result.setMsg(msg);
        result.setData(obj);
        result.setDate(new Date());
        return result;
    }

    public static void main(String[] args) {
        ApiResult apiResult = new ApiResult();
        apiResult.setSuccess(true);
//        System.out.println(JsonUtil.t2JsonString(apiResult));
    }
}
