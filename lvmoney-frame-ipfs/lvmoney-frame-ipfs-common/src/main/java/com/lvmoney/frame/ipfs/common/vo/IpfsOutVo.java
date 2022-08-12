package com.lvmoney.frame.ipfs.common.vo;/**
 * 描述:
 * 包名:com.lvmoney.frame.ipfs.common.vo
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
 * @version:v1.0 2020/5/7 8:44
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IpfsOutVo implements Serializable {
    private static final long serialVersionUID = -5543139342092658661L;
    /**
     * 文件的hash值
     */
    private String hash;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 大小
     */
    private Long length;
}
