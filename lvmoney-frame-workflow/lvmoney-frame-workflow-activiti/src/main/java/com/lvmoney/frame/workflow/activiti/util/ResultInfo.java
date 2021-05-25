package com.lvmoney.frame.workflow.activiti.util;

/**
 * @describe：返回解开过信息类
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2018年9月30日 上午8:51:33
 */
public class ResultInfo {

    /**
     * 返回状态
     */
    private Integer code;
    /**
     * 状态描述信息
     */
    private String msg;
    /**
     * Info查询总数据量
     */
    private long count;
    /**
     * 返回数据列表
     */
    private Object info;

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


    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public ResultInfo() {
        super();
    }

}
