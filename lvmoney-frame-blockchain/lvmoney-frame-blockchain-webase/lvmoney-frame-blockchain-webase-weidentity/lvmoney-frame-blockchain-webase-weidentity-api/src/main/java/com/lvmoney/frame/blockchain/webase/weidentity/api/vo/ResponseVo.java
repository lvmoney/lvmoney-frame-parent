package com.lvmoney.frame.blockchain.webase.weidentity.api.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.blockchain.webase.weidentity.common.api
 * 版本信息: 版本1.0
 * 日期:2021/6/26
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/6/26 14:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVo<T> implements Serializable {
    private static final long serialVersionUID = 1829396246850437043L;
    /**
     * 随调用SDK方法而变的输出值json字符串
     */
    private T respBody;
    /**
     * 错误码
     */
    private Integer ErrorCode;
    /**
     * 错误信息
     */
    private String ErrorMessage;

    private String loopback;
}
