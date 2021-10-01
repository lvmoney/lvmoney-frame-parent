/**
 * 描述:
 * 包名:com.lvmoney.mongo.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月10日  下午1:20:15
 * Copyright XXXXXX科技有限公司
 */

package com.lvmoney.frame.oss.common.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /XXXXXX科技有限公司
 * @version:v1.0 2019年1月10日 下午1:20:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileBaseVo<T> implements Serializable {
    /**
     *
     */


    private static final long serialVersionUID = 6293937260761911341L;
    private String fileName;
    private MultipartFile file;
    /**
     * 各个中间件的特性实体
     */
    private T data;
    private Long maxSize;
}
