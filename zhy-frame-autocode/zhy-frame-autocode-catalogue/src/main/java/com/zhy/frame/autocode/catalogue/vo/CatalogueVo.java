package com.zhy.frame.autocode.catalogue.vo;/**
 * 描述:
 * 包名:com.zhy.frame.autocode.catalogue.vo
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
 * @version:v1.0 2020/6/17 14:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogueVo implements Serializable {
    private static final long serialVersionUID = -3051329011880978027L;
    /**
     * 项目路径
     */
    private String path;
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
     * 目录名称
     */
    private List<String> catalogues;
    /**
     * 是否覆盖
     */
    private boolean cover;
}
