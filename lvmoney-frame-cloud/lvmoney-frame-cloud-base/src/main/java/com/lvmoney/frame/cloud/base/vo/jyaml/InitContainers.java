package com.lvmoney.frame.cloud.base.vo.jyaml;/**
 * 描述:
 * 包名:com.lvmoney.frame.cloud.base.vo.jyaml
 * 版本信息: 版本1.0
 * 日期:2021/1/11
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
 * @version:v1.0 2021/1/11 16:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InitContainers implements Serializable {
    private String name;
    private String image;
    private String imagePullPolicy;
    private List<String> command;
    private List<VolumeMounts> volumeMounts;
}
