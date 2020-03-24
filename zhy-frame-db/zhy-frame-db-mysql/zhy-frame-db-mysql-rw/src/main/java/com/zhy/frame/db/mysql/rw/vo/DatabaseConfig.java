package com.zhy.frame.db.mysql.rw.vo;/**
 * 描述:
 * 包名:com.zhy.mysql.separate.vo
 * 版本信息: 版本1.0
 * 日期:2019/9/6
 * Copyright 四川******科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/四川******科技有限公司
 * @version:v1.0 2019/9/6 18:12
 */
@Data
public class DatabaseConfig implements Serializable {
    private static final long serialVersionUID = 2318330605641180472L;
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private String databaseName;
    private String type;
}
