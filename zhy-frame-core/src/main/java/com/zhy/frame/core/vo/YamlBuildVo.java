package com.zhy.frame.core.vo;/**
 * 描述:
 * 包名:com.zhy.k8s.base.vo.jyaml
 * 版本信息: 版本1.0
 * 日期:2019/8/19
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/19 10:27
 */
@Data
public class YamlBuildVo implements Serializable {
    private static final long serialVersionUID = -4715311414269450841L;
    private String path;
    private String name;
    private List<Object> data;
    private boolean cover;
}
