package com.zhy.frame.base.core.api;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zhy.frame.base.core.constant.BaseConstant;
import com.zhy.frame.base.core.util.I18nUtil;
import com.zhy.frame.base.core.util.YmlUtil;

import java.io.Serializable;
import java.util.Date;

import static com.zhy.frame.base.core.constant.BaseConstant.*;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class ApiResult<T> implements Serializable {
    static {
        Object property = YmlUtil.getInstance().getProperty("frame.i18n.support");
        i18nSupport = property == null ? false : Boolean.valueOf(property.toString());
    }

    public static boolean i18nSupport;

    private static final long serialVersionUID = 1L;

    private Integer code;

    private boolean success;

    private String msg;

    private T data;
    @JSONField(format = API_RESULT_DATA_DATE_FORMAT)
    @JsonFormat(pattern = API_RESULT_DATA_DATE_FORMAT)
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ApiResult() {
        this.setCode(SUCCESS_DEFAULT_CODE);
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
        this(SUCCESS_DEFAULT_CODE, SUCCESS_DEFAULT_MSG, data);
    }

    public ApiResult(Integer code, String msg) {
        this(code, msg, null);
    }

    public ApiResult(Integer code, String msg, T data) {
        this.code = code;
        String i18nMsg = getI18nMsg(code, msg);
        this.msg = i18nMsg;
        this.data = data;
        date = new Date();
    }

    public static ApiResult success(Object object) {
        ApiResult result = new ApiResult();
        result.setSuccess(true);
        result.setCode(SUCCESS_DEFAULT_CODE);
        String i18nMsg = getI18nMsg(SUCCESS_DEFAULT_CODE, BaseConstant.SUCCESS_DEFAULT_MSG);
        result.setMsg(i18nMsg);
        result.setData(object);
        result.setDate(new Date());
        return result;
    }


    public static ApiResult success() {
        return success(null);
    }


    public static ApiResult error(Integer code, String msg) {
        ApiResult result = new ApiResult();
        result.setCode(code);
        result.setSuccess(false);
        String i18nMsg = getI18nMsg(code, msg);
        result.setMsg(i18nMsg);
        result.setDate(new Date());
        return result;
    }

    public static ApiResult error(Integer code, String msg, Object obj) {
        ApiResult result = new ApiResult();
        result.setCode(code);
        result.setSuccess(false);
        String i18nMsg = getI18nMsg(code, msg);
        result.setMsg(i18nMsg);
        result.setData(obj);
        result.setDate(new Date());
        return result;
    }

    /**
     * 获得国际化的结果
     *
     * @param code:
     * @param msg:
     * @throws
     * @return: java.lang.String
     * @author: lvmoney /XXXXXX科技有限公司
     * @date: 2020/6/15 14:38
     */
    private static String getI18nMsg(Integer code, String msg) {
        String i18nMsg = "";
        if (i18nSupport) {
            try {
                i18nMsg = I18nUtil.getMessage(code.toString());
                i18nMsg = i18nMsg.isEmpty() ? msg : i18nMsg;
            } catch (Exception e) {
                i18nMsg = msg;
            }
        } else {
            i18nMsg = msg;
        }
        return i18nMsg;
    }


}
