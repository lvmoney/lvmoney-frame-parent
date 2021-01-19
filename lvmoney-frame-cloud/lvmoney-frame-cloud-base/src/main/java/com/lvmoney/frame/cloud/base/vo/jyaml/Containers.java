package com.lvmoney.frame.cloud.base.vo.jyaml;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.vo.jyaml
 * 版本信息: 版本1.0
 * 日期:2019/8/19
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/19 9:06
 */
@Data
public class Containers implements Serializable {
    private static final long serialVersionUID = -4425341558195520318L;
    private String name;
    private String image;
    private String imagePullPolicy;
    private Resources resources;
    private List<Ports> ports;
    private List<Env> env;
    private List<VolumeMounts> volumeMounts;
}
