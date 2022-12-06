package com.lvmoney.frame.ai.seetaface.base.dto;/**
 * 描述:
 * 包名:com.lvmoney.frame.ai.seetaface.client.bo
 * 版本信息: 版本1.0
 * 日期:2022/2/11
 * Copyright XXXXXX科技有限公司
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney/XXXXXX科技有限公司
 * @version:v1.0 2022/2/11 13:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Upload2MinioDto<T> implements Serializable {

    private static final long serialVersionUID = -6754671731376400222L;
    /**
     *
     */


    private String fileName;
    private MultipartFile file;
    /**
     * 各个中间件的特性实体
     */
    private T data;
    private Long maxSize;
}
