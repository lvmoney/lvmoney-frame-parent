package com.lvmoney.autocode.code.vo;/**
 * 描述:
 * 包名:com.lvmoney.autocode.code.vo
 * 版本信息: 版本1.0
 * 日期:2020/6/19
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/6/19 9:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeVo implements Serializable {
    /**
     * 名称
     */
    private String name;
    /**
     * 是否覆盖
     */
    private boolean cover;

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
     * 作者
     */
    private String author;

    /**
     * 公司
     */
    private String company;

}
