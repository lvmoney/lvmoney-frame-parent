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

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2020/5/7 14:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileVo implements Serializable {
    private static final long serialVersionUID = -5259675754838509378L;
    /**
     * 源文件
     */
    private String srcFile;
    /**
     * 新文件
     */
    private String newFile;
    /**
     * 密钥
     */
    private String secret;
}
