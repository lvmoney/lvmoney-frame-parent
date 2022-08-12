package com.lvmoney.frame.oss.minio.prop;/**
 * 描述:
 * 包名:com.lvmoney.frame.oss.minio.prop
 * 版本信息: 版本1.0
 * 日期:2021/2/8
 * Copyright XXXXXX科技有限公司
 */


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2021/2/8 17:06
 */
@Data
@ConfigurationProperties(prefix = "oss.minio")
public class MinioProperties {
    private String endpoint;
    private int port;
    private String accessKey;
    private String secretKey;
    private Boolean secure;
}