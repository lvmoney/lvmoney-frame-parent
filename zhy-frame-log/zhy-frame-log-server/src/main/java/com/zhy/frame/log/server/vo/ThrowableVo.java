package com.zhy.frame.log.server.vo;/**
 * 描述:
 * 包名:com.zhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/1
 * Copyright 四川******科技有限公司
 */


import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @describe：
 * @author: lvmoney /四川******科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThrowableVo implements Serializable {
    private Integer code;
    private String description;
    private String message;
    private String type;
    @JSONField(name = "@type")
    private String eType;
}
