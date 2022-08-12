package com.lvmoney.frame.oss.minio.dto;/**
 * 描述:
 * 包名:com.lvmoney.frame.oss.minio.dto
 * 版本信息: 版本1.0
 * 日期:2021/2/8
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/2/8 17:29
 */
@Data
@NoArgsConstructor
public class MinioDto implements Serializable {
    /**
     * 存储桶
     */
    private String bucketName;
}
