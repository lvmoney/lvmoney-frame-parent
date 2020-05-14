package com.zhy.frame.core.vo;/**
 * 描述:
 * 包名:com.zhy.frame.core.vo
 * 版本信息: 版本1.0
 * 日期:2020/5/7
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/7 14:36
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ByteInputFileVo implements Serializable {
    private static final long serialVersionUID = 3876228617190810432L;
    /**
     * 源文件流
     */
    private byte[] srcByte;
    /**
     * 密钥
     */
    private String secret;
}
