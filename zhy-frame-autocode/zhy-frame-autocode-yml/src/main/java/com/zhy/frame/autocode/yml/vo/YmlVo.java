package com.zhy.frame.autocode.yml.vo;/**
 * 描述:
 * 包名:com.zhy.frame.autocode.yml.vo
 * 版本信息: 版本1.0
 * 日期:2020/6/17
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/17 19:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class YmlVo implements Serializable {
    private static final long serialVersionUID = 7109680159453762846L;
    /**
     * 环境
     */
    private List<String> env;
    /**
     * 系统名称
     */
    private String sysName;
    /**
     * 系统端口
     */
    private Integer sysPort;
    /**
     * 激活的
     */
    private String activeProfile;
    /**
     * 是否覆盖
     */
    private boolean cover;
    /**
     * 路径
     */
    private String path;
    /**
     * gateway
     */
    private String gateway;
}
