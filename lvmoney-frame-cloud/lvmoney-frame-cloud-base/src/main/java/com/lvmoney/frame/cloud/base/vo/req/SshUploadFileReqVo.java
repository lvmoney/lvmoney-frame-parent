package com.lvmoney.frame.cloud.base.vo.req;/**
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
 * @version:v1.0 2019/8/21 15:58
 */
@Data
public class SshUploadFileReqVo extends SshCommonVo {
    private static final long serialVersionUID = 53397072727235473L;
    private String fromPath;
    private String fromName;
    private String toPath;
}
