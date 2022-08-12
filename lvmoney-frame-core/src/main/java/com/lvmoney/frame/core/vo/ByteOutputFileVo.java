package com.lvmoney.frame.core.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.core.vo
 * 版本信息: 版本1.0
 * 日期:2020/5/7
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/7 14:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ByteOutputFileVo implements Serializable {
    /**
     * 新的流
     */
    private byte[] newByte;
}
