package com.zhy.frame.office.word.vo;/**
 * 描述:
 * 包名:com.scltzhy.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/2/27
 * Copyright 四川******科技有限公司
 */


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
public class WordStringVo implements Serializable {
    private static final long serialVersionUID = 6430928620025915853L;
    /**
     * 字符串key，对应模板中{{gender}}
     */
    private String key;
    /**
     * 字符串填充内容
     */
    private String value;
}

