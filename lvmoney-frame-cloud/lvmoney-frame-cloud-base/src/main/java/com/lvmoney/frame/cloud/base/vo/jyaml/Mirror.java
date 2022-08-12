package com.lvmoney.frame.cloud.base.vo.jyaml;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.vo.jyaml
 * 版本信息: 版本1.0
 * 日期:2019/8/26
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：压力测试 回归测试
 * 线上问题重现
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/26 14:52
 */
@Data
public class Mirror implements Serializable {
    private static final long serialVersionUID = -4324827963352647471L;
    private String mirror;
    private String subset;
}
