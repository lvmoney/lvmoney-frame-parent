package com.zhy.frame.cloud.base.util;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.util
 * 版本信息: 版本1.0
 * 日期:2019/8/18
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/18 22:25
 */
@Data
public class ExecCmdResult implements Serializable {

    /**
     * 命令执行是否成功
     */
    private boolean success;
    /**
     * 输出结果
     */
    private String result;
}
