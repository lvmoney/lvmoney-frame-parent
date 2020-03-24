package com.zhy.frame.cloud.base.vo.jyaml;/**
 * 描述:
 * 包名:com.zhy.k8s.base.vo.Yaml
 * 版本信息: 版本1.0
 * 日期:2019/8/18
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/18 20:12
 */
@Data
public class Metadata implements Serializable {
    private static final long serialVersionUID = 3441996091083275706L;
    private String name;
    private Labels labels;
    private String namespace;
    private Map<String, Object> annotations;
}
