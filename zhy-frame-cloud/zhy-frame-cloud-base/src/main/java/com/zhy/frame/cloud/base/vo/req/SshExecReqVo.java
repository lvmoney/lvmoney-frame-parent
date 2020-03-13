package com.zhy.frame.cloud.base.vo.req;/**
 * 描述:
 * 包名:com.lvmoney.k8s.base.vo.req
 * 版本信息: 版本1.0
 * 日期:2019/8/21
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2019/8/21 16:04
 */
@Data
public class SshExecReqVo extends SshCommonVo {
    private String exec;
}
