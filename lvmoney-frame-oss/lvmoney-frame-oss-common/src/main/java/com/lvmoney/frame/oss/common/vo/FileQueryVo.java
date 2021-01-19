/**
 * 描述:
 * 包名:com.lvmoney.mongo.ro
 * 版本信息: 版本1.0
 * 日期:2019年1月10日  下午2:06:39
 * Copyright 成都三合力通科技有限公司
 */

package com.lvmoney.frame.oss.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /成都三合力通科技有限公司
 * @version:v1.0 2019年1月10日 下午2:06:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileQueryVo implements Serializable {

    /**
     *
     */


    private static final long serialVersionUID = 8408644038062965872L;

    private String fileId;
    private String name;


}
