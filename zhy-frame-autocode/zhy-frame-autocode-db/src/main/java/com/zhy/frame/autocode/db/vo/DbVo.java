package com.zhy.frame.autocode.db.vo;/**
 * 描述:
 * 包名:com.zhy.frame.autocode.db.vo
 * 版本信息: 版本1.0
 * 日期:2020/6/18
 * Copyright XXXXXX科技有限公司
 */


import com.zhy.frame.autocode.db.enums.DbEnvEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/18 9:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DbVo implements Serializable {
    private static final long serialVersionUID = 4746626217967679850L;
    /**
     * 路径
     */
    private String path;
    /**
     * 作者
     */
    private String author;
    /**
     * 数据库用户名
     */
    private String dbUsername;
    /**
     * 数据库密码
     */
    private String dbPassword;
    /**
     * 数据库ip
     */
    private String dbIp;
    /**
     * 数据库端口
     */
    private Integer dbPort;
    /**
     * 数据库名称
     */
    private String dbName;
    /**
     * 是否覆盖
     */
    private boolean cover;

    /**
     * 组织名称
     */
    private String org;
    /**
     * artifact
     */
    private String artifact;
    /**
     * 系统名称
     */
    private String sys;
    /**
     * 环境
     */
    private DbEnvEnum env;
    /**
     * 表列表
     */
    private List<String> dbTable;
}
