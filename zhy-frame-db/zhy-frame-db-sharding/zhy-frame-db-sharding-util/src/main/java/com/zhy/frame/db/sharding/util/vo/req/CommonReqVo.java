package com.zhy.frame.db.sharding.util.vo.req;/**
 * 描述:
 * 包名:com.zhy.mysql.subdb.vo.req
 * 版本信息: 版本1.0
 * 日期:2019/11/15
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/11/15 15:25
 */
@Data
public class CommonReqVo implements Serializable {
    private static final long serialVersionUID = -7381610565612031766L;
    /**
     * 库名
     */
    private String dbName;
    /**
     * ip
     */
    private String ip;
    /**
     * 端口
     */
    private Integer port;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名
     */
    private String username;

    /**
     * 是否分表或者分库
     */
    private boolean seq;

}
